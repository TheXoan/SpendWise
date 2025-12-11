package com.arcaneia.spendwise

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.arcaneia.spendwise.data.workers.RenewMovsRecurWorker
import com.arcaneia.spendwise.data.workers.SyncWorker
import java.util.concurrent.TimeUnit

/**
 * Clase base de la aplicación.
 *
 * Esta clase se ejecuta antes que cualquier Activity y es responsable de:
 *
 * ### 🔧 Inicializar WorkManager con los procesos automáticos:
 *
 * ---
 * ## 1️⃣ **RenewMovsRecurWorker** (ejecución periódica cada 15 minutos)
 *
 * Este Worker:
 * - Procesa renovaciones de movimientos recurrentes.
 * - Genera nuevos movimientos si corresponde.
 * - Sincroniza con el servidor.
 * - Envía notificaciones a este dispositivo.
 *
 * Se programa como **trabajo periódico único**, lo que garantiza que:
 * - No haya múltiples instancias repetidas.
 * - WorkManager respete un mínimo de 15 minutos entre ejecuciones (límite Android).
 *
 * ---
 * ## 2️⃣ **SyncWorker** (ejecución periódica cada 15 minutos)
 *
 * Se encarga de:
 * - Sincronizar categorías, movimientos recurrentes y movimientos simples.
 * - Detectar borrados remotos.
 * - Mantener la BD local coherente con PocketBase.
 *
 * También se programa como tarea periódica única.
 *
 * ---
 * ## 3️⃣ **SyncWorker lanzado al iniciar la app**
 *
 * Además de la tarea periódica, se ejecuta una sincronización inmediata al arrancar:
 *
 * - Asegura que el usuario vea datos actualizados desde el primer momento.
 * - Repara estados intermedios o inconsistencias generadas mientras la app estaba cerrada.
 * - No interfiere con la tarea periódica, ya que se programa como trabajo *OneTime*.
 *
 * ---
 * ## ✔ Garantías de este setup
 *
 * - Ningún worker se duplica innecesariamente (`KEEP`).
 * - La app siempre sincroniza datos *on start* (`REPLACE` para asegurar que se ejecute).
 * - Se cumplen los intervalos mínimos exigidos por Android.
 * - El usuario recibe notificaciones de movimientos recurrentes sin retrasos excesivos.
 *
 */
class SpendWiseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val workManager = WorkManager.getInstance(this)

        // ---------------------------------------------------------
        // 1) Trabajo periódico para renovaciones de movimientos
        // ---------------------------------------------------------
        val renewMovsRecurWork = PeriodicWorkRequestBuilder<RenewMovsRecurWorker>(
            15,
            TimeUnit.MINUTES
        ).build()

        workManager.enqueueUniquePeriodicWork(
            "renew_movs_recur_work",
            ExistingPeriodicWorkPolicy.KEEP,
            renewMovsRecurWork
        )

        // ---------------------------------------------------------
        // 2) Trabajo periódico de sincronización completa
        // ---------------------------------------------------------
        val syncPeriodicWork = PeriodicWorkRequestBuilder<SyncWorker>(
            15,
            TimeUnit.MINUTES
        ).build()

        workManager.enqueueUniquePeriodicWork(
            "sync_worker_periodic",
            ExistingPeriodicWorkPolicy.KEEP,
            syncPeriodicWork
        )

        // ---------------------------------------------------------
        // 3) Trabajo inmediato de sincronización al arrancar
        // ---------------------------------------------------------
        val syncOneTimeWork = OneTimeWorkRequestBuilder<SyncWorker>().build()

        workManager.enqueueUniqueWork(
            "sync_worker_on_start",
            ExistingWorkPolicy.REPLACE,
            syncOneTimeWork
        )
    }
}