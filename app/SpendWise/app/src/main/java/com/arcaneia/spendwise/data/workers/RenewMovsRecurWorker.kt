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

class RenewMovsRecurWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {

        val permissionsStore = PermissionsDataStore(applicationContext)

        // Leemos permiso guardado
        val grantedInDataStore = permissionsStore.isNotificationGranted.first()

        // Se generan los movimientos
        val repo = ServiceLocator.getMovRecurRepository(applicationContext)
        val createdMovs: List<Mov> = repo.processRenewals()

        // Si no hay nuevos movimientos → terminar
        if (createdMovs.isEmpty()) {
            return Result.success()
        }

        if (grantedInDataStore) {

            val systemPermissionGranted =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    ContextCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                } else true

            if (systemPermissionGranted) {
                showNotifications(createdMovs)
            }
        }

        return Result.success()
    }


    private fun showNotifications(movs: List<Mov>) {

        val channelId = "mov_recur_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Movimientos recurrentes"
            val descriptionText = "Notificaciones de renovaciones de movimientos recurrentes"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(
                channelId,
                name,
                importance
            ).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val manager = NotificationManagerCompat.from(applicationContext)

        movs.forEach { mov ->
            val title = "Movimiento recurrente renovado"
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