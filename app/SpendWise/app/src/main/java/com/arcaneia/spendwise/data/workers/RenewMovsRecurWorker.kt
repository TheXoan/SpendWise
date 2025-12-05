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
import com.arcaneia.spendwise.data.di.ServiceLocator
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.datastore.PermissionsDataStore
import kotlinx.coroutines.flow.first

/**
 * Worker encargado de procesar la renovación automática de movimientos recurrentes
 * y emitir una notificación independiente por cada movimiento generado.
 *
 * Este Worker se ejecuta en segundo plano mediante WorkManager y realiza las
 * siguientes operaciones:
 *
 * 1. Lee desde DataStore si el usuario otorgó permiso interno para recibir notificaciones.
 * 2. Llama al repositorio [MovRecurRepository] para procesar las renovaciones pendientes,
 *    generando nuevos movimientos ([Mov]) según corresponda.
 * 3. Si se generaron movimientos y el permiso del sistema para notificaciones está concedido
 *    (en Android 13+), crea y muestra una notificación por cada movimiento renovado.
 *
 * A diferencia de versiones anteriores, cada notificación utiliza un ID único generado
 * dinámicamente, evitando que las notificaciones se sobreescriban y garantizando que todas
 * se muestren de manera independiente.
 *
 * Se utiliza `CoroutineWorker` para permitir la ejecución de funciones suspendidas sin
 * bloquear el hilo de trabajo.
 *
 * @param appContext Contexto global de la aplicación.
 * @param params Parámetros suministrados por WorkManager.
 */
class RenewMovsRecurWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    /**
     * Ejecuta el proceso principal del Worker.
     *
     * - Verifica si el usuario otorgó permiso interno para notificaciones.
     * - Procesa las renovaciones de movimientos recurrentes.
     * - Notifica individualmente cada movimiento generado si el sistema lo permite.
     *
     * No retorna [Result.retry] ni [Result.failure], ya que el repositorio gestiona
     * internamente sus excepciones.
     *
     * @return [Result.success] si el proceso se ejecutó correctamente, incluso si no hubo renovaciones.
     */
    override suspend fun doWork(): Result {
        val permissionsStore = PermissionsDataStore(applicationContext)

        // Permiso interno almacenado en DataStore
        val grantedInDataStore = permissionsStore.isNotificationGranted.first()

        // Procesa renovaciones y obtiene lista de nuevos movimientos creados
        val repo = ServiceLocator.getMovRecurRepository(applicationContext)
        val createdMovs: List<Mov> = repo.processRenewals()

        // No hay movimientos generados → no se envían notificaciones
        if (createdMovs.isEmpty()) {
            return Result.success()
        }

        if (grantedInDataStore) {

            // Verificación del permiso del sistema para notificaciones (solo Android 13+)
            val systemPermissionGranted =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    ContextCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                } else true

            // Si ambos permisos están concedidos → se muestran notificaciones
            if (systemPermissionGranted) {
                showNotifications(createdMovs)
            }
        }

        return Result.success()
    }

    /**
     * Genera y muestra una notificación para cada movimiento renovado.
     *
     * Componentes clave:
     * - Crea un canal de notificaciones en Android O+ si aún no existe.
     * - Construye una notificación con título, descripción y valor del movimiento.
     * - Genera un ID único para cada notificación mediante `System.currentTimeMillis()`,
     *   evitando sobrescribir notificaciones previas.
     *
     * @param movs Lista de movimientos renovados que deben ser notificados.
     */
    @SuppressLint("MissingPermission", "DefaultLocale")
    private fun showNotifications(movs: List<Mov>) {

        val channelId = "mov_recur_channel"

        // Crea el canal en Android O+
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

            val title = applicationContext.getString(R.string.new_renewal)
            val content = String.format(
                "%s - %.2f€",
                mov.descricion ?: applicationContext.getString(R.string.activity),
                mov.importe
            )

            // Genera un ID único para cada notificación
            val notificationId = System.currentTimeMillis().toInt()

            val notification = NotificationCompat.Builder(applicationContext, channelId)
                .setSmallIcon(R.mipmap.ic_launcher_spendwise)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .build()

            manager.notify(notificationId, notification)
        }
    }
}