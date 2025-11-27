package com.arcaneia.spendwise.data.workers

import android.Manifest
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
import com.arcaneia.spendwise.data.di.ServiceLocator
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.database.PermissionsDataStore
import kotlinx.coroutines.flow.first

/**
 * Worker encargado de procesar la renovación automática de movimientos recurrentes
 * y emitir notificaciones en caso de que se hayan generado nuevos movimientos.
 *
 * Este Worker se ejecuta en segundo plano utilizando WorkManager y realiza las siguientes tareas:
 *
 * 1. Consulta el DataStore para verificar si el usuario otorgó permiso interno
 *    para recibir notificaciones.
 * 2. Procesa las renovaciones pendientes mediante [MovRecurRepository], generando nuevos
 *    movimientos ([Mov]) cuando corresponda.
 * 3. Si se generaron nuevos movimientos y el permiso del sistema está otorgado,
 *    muestra una notificación por cada movimiento renovado.
 *
 * Se utiliza `CoroutineWorker` para poder ejecutar código suspendido dentro del Worker.
 *
 * @param appContext Contexto de la aplicación.
 * @param params Parámetros proporcionados por WorkManager.
 */
class RenewMovsRecurWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    /**
     * Ejecuta el trabajo principal del Worker.
     *
     * @return [Result.success] si el proceso finaliza correctamente.
     *         Nunca retorna [Result.retry] ni [Result.failure] en este caso, ya que los errores
     *         internos son manejados dentro del repositorio.
     */
    override suspend fun doWork(): Result {

        val permissionsStore = PermissionsDataStore(applicationContext)

        // Lee permiso guardado en DataStore (permiso propio de la app, no del sistema)
        val grantedInDataStore = permissionsStore.isNotificationGranted.first()

        // Se generan movimientos recurrentes convertidos a movimientos normales
        val repo = ServiceLocator.getMovRecurRepository(applicationContext)
        val createdMovs: List<Mov> = repo.processRenewals()

        // Si no hay renovaciones, termina el Worker
        if (createdMovs.isEmpty()) {
            return Result.success()
        }

        if (grantedInDataStore) {

            // Comprueba el permiso del sistema para enviar notificaciones (Android 13+)
            val systemPermissionGranted =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    ContextCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                } else true

            // Muestra notificaciones solo si ambos permisos están concedidos
            if (systemPermissionGranted) {
                showNotifications(createdMovs)
            }
        }

        return Result.success()
    }

    /**
     * Muestra una notificación por cada movimiento renovado.
     *
     * Crea un canal de notificaciones si la versión del sistema operativo lo requiere (Android O+).
     *
     * @param movs Lista de movimientos renovados que deben ser notificados.
     */
    private fun showNotifications(movs: List<Mov>) {

        val channelId = "mov_recur_channel"

        // Crear canal de notificación en Android O+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Movimientos recurrentes",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notificaciones de renovaciones de movimientos recurrentes"
            }
            val notificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val manager = NotificationManagerCompat.from(applicationContext)

        // Verificación del permiso POST_NOTIFICATIONS en Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        // Enviar una notificación por cada movimiento renovado
        movs.forEach { mov ->
            val title = "Nueva renovación"
            val content = "${mov.descricion ?: "Movimiento"} - %.2f€".format(mov.importe)

            val notification = NotificationCompat.Builder(applicationContext, channelId)
                .setSmallIcon(R.mipmap.ic_launcher_spendwise)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .build()

            manager.notify(mov.id, notification)
        }
    }
}