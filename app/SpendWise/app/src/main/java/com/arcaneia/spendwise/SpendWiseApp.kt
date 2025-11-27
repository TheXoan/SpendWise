package com.arcaneia.spendwise

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.arcaneia.spendwise.data.workers.RenewMovsRecurWorker
import java.util.concurrent.TimeUnit

/**
 * Clase principal de la aplicación.
 *
 * Se ejecuta antes que cualquier Activity y es ideal para inicializar
 * componentes globales como:
 * - Servicios de WorkManager
 * - Instancias singleton
 * - Configuración de librerías
 *
 * En este caso inicializa un Worker periódico encargado de procesar
 * las renovaciones de movimientos recurrentes.
 */
class SpendWiseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val workManager = WorkManager.getInstance(this)

        /**
         * WorkRequest periódico:
         * - Ejecuta RenewMovsRecurWorker cada 15 minutos (mínimo permitido por Android).
         * - Revisa si existen movimientos recurrentes que deben renovarse.
         */
        val periodicWork = PeriodicWorkRequestBuilder<RenewMovsRecurWorker>(
            15,
            TimeUnit.MINUTES
        ).build()

        /**
         * Encolamos un único Worker con nombre fijo.
         *
         * ExistingPeriodicWorkPolicy.KEEP:
         *  - Si ya existe un worker con este nombre, NO lo reemplaza.
         *  - Garantiza que no se creen múltiples workers duplicados.
         */
        workManager.enqueueUniquePeriodicWork(
            "renew_movs_recur_work",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWork
        )
    }
}