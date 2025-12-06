package com.arcaneia.spendwise

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.arcaneia.spendwise.data.workers.RenewMovsRecurWorker
import com.arcaneia.spendwise.data.workers.SyncWorker
import java.util.concurrent.TimeUnit

/**
 * Clase principal de la aplicación.
 *
 * Se ejecuta antes que cualquier Activity y es ideal para inicializar
 * componentes globales como:
 * - Servicios de WorkManager.
 * - Instancias singleton.
 * - Configuración de librerías globales.
 *
 * En este caso inicializa dos Workers periódicos:
 * 1. [RenewMovsRecurWorker] → encargado de renovar movimientos recurrentes.
 * 2. [SyncWorker] → encargado de sincronizar datos locales con el servidor.
 */
class SpendWiseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val workManager = WorkManager.getInstance(this)

        /**
         * WorkRequest periódico encargado de ejecutar [RenewMovsRecurWorker]
         * cada 15 minutos (intervalo mínimo permitido por Android para trabajos periódicos).
         *
         * Este worker:
         * - Revisa movimientos recurrentes.
         * - Genera nuevos movimientos cuando corresponde.
         */
        val periodicWork = PeriodicWorkRequestBuilder<RenewMovsRecurWorker>(
            15,
            TimeUnit.MINUTES
        ).build()

        /**
         * Encola un único Worker usando un nombre fijo.
         *
         * `ExistingPeriodicWorkPolicy.KEEP` asegura que:
         * - Si ya existe un worker con ese nombre, NO será reemplazado.
         * - Evita duplicados, garantizando un único proceso periódico.
         */
        workManager.enqueueUniquePeriodicWork(
            "renew_movs_recur_work",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWork
        )

        /**
         * WorkRequest periódico encargado de ejecutar [SyncWorker].
         *
         * Su responsabilidad es:
         * - Mantener sincronizados los datos locales y remotos.
         * - Ejecutarse cada 15 minutos automáticamente.
         */
        val syncWork = PeriodicWorkRequestBuilder<SyncWorker>(
            15,
            TimeUnit.MINUTES
        ).build()

        /**
         * Encola un único worker de sincronización con la misma política de conservación.
         */
        workManager.enqueueUniquePeriodicWork(
            "sync_worker",
            ExistingPeriodicWorkPolicy.KEEP,
            syncWork
        )
    }
}