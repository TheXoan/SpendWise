package com.arcaneia.spendwise.data.workers

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.arcaneia.spendwise.R
import com.arcaneia.spendwise.apis.data.model.MovRemoteDataSource
import com.arcaneia.spendwise.apis.data.model.MovSyncRepository
import com.arcaneia.spendwise.data.database.AppDatabase
import com.arcaneia.spendwise.data.di.ServiceLocator
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.datastore.PermissionsDataStore
import kotlinx.coroutines.flow.first

/**
 * Worker responsable de procesar renovaciones de movimientos recurrentes
 * y gestionar sus notificaciones locales.
 *
 * Este Worker garantiza un flujo completo y seguro:
 *
 * ---
 * ### 🔄 **1. Procesar renovaciones (MovRecur → Mov)**
 *
 * Ejecuta `MovRecurRepository.processRenewals()`:
 * - Genera movimientos simples ([Mov]) cuando corresponde según `data_rnv`.
 * - Cada movimiento creado incluye un `renew_hash` determinista para evitar duplicados.
 * - Devuelve los movimientos generados en esta ejecución.
 *
 * ---
 * ### ☁ **2. Sube inmediatamente los movimientos generados a PocketBase**
 *
 * Utiliza [MovSyncRepository] para:
 * - Subir los nuevos movimientos
 * - Resolver duplicados mediante `renew_hash`
 * - Traer cambios remotos
 * - Mantener el estado local consistente con PocketBase
 *
 * Esto asegura que:
 * - Otros dispositivos verán las renovaciones
 * - Ningún dispositivo genere la misma renovación dos veces
 *
 * ---
 * ### 🔔 **3. Obtiene todos los movimientos con `notificado = 0`**
 *
 * El repositorio no marca como “notificado” durante su creación.
 * Lo hace exclusivamente este Worker, permitiendo:
 * - Mostrar notificaciones locales
 * - No repetirlas nunca más
 * - Notificar renovaciones creadas en *otro* dispositivo al sincronizar
 *
 * ---
 * ### 📳 **4. Genera notificaciones locales**
 *
 * Solo se envían si:
 * - El usuario dio permiso interno desde ajustes de la app
 * - Y el sistema tiene permiso para mostrar notificaciones (Android 13+)
 *
 * Una notificación por cada movimiento pendiente.
 *
 * ---
 * ### 🏁 **5. Marca todos los pendientes como notificados**
 *
 * Esto evita repetición infinita de notificaciones,
 * incluso si el Worker se vuelve a ejecutar varias veces.
 *
 * ---
 * ### ✔ Garantías del sistema
 *
 * - ❌ No duplica renovaciones
 *   (gracias al `renew_hash` determinista)
 *
 * - ❌ No duplica notificaciones
 *   (se notifican solo los `notificado = 0`)
 *
 * - ✔ Funciona en todos los dispositivos sincronizados
 *   (cada móvil genera notificaciones de los Mov que existan en *su* base local)
 *
 * ---
 * @constructor Recibe el contexto y parámetros del Worker.
 */
class RenewMovsRecurWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    /**
     * Ejecuta el flujo completo:
     * 1. Renovar movimientos recurrentes
     * 2. Subir renovaciones generadas
     * 3. Obtener pendientes de notificar
     * 4. Notificar (si procede)
     * 5. Marcar como notificados
     */
    override suspend fun doWork(): Result {

        val permissionsStore = PermissionsDataStore(applicationContext)
        val grantedInDataStore = permissionsStore.isNotificationGranted.first()

        // DAOs
        val db = AppDatabase.getDatabase(applicationContext)
        val movDao = db.movDao()
        val movRecurDao = db.movRecurDao()
        val categoriaDao = db.categoriaDao()

        // 1) Generar renovaciones locales
        val repo = ServiceLocator.getMovRecurRepository(applicationContext)
        val createdMovs = repo.processRenewals()

        // Si no ha generado nada → terminar
        if (createdMovs.isEmpty()) return Result.success()

        // 2) Subir las renovaciones a PocketBase
        val movSyncRepository =
            MovSyncRepository(
                local = movDao,
                remote = MovRemoteDataSource(applicationContext),
                categoriaDao = categoriaDao,
                movRecurDao = movRecurDao,
                context = applicationContext
            )
        movSyncRepository.sync()

        // 3) Obtener movimientos aún no notificados
        val pending = movDao.getPendingNotifications()

        if (pending.isEmpty()) return Result.success()

        // 4) Notificar solo si está permitido
        if (grantedInDataStore) {

            val systemPermissionGranted =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    ContextCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                } else true

            if (systemPermissionGranted) {
                showNotifications(pending)

                // 5) Marcar todos como notificados
                pending.forEach { movDao.markAsNotified(it.id) }
            }
        }

        return Result.success()
    }

    /**
     * Muestra una notificación local para cada movimiento de la lista.
     *
     * - Crea el canal si es necesario (Android O+)
     * - Genera un ID único por notificación mediante timestamp
     * - Formatea adecuadamente el importe
     *
     * @param movs Lista de movimientos a notificar.
     */
    @SuppressLint("DefaultLocale")
    private fun showNotifications(movs: List<Mov>) {
        val channelId = "mov_recur_channel"

        // Crear canal en Android 8+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Movimientos recurrentes",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val manager = NotificationManagerCompat.from(applicationContext)

        movs.forEach { mov ->
            val notificationId = (System.currentTimeMillis() % Int.MAX_VALUE).toInt()

            val notification = NotificationCompat.Builder(applicationContext, channelId)
                .setSmallIcon(R.mipmap.ic_launcher_spendwise)
                .setContentTitle(applicationContext.getString(R.string.new_renewal))
                .setContentText("${mov.descricion} - ${String.format("%.2f", mov.importe)}€")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .build()

            manager.notify(notificationId, notification)
        }
    }
}