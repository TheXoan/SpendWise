package com.arcaneia.spendwise

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy // 👈 Nuevo Import
import androidx.work.OneTimeWorkRequestBuilder // 👈 Nuevo Import
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.arcaneia.spendwise.data.workers.RenewMovsRecurWorker
import com.arcaneia.spendwise.data.workers.SyncWorker
import java.util.concurrent.TimeUnit

class SpendWiseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val workManager = WorkManager.getInstance(this)


        val renewMovsRecurWork = PeriodicWorkRequestBuilder<RenewMovsRecurWorker>(
            15,
            TimeUnit.MINUTES
        ).build()

        workManager.enqueueUniquePeriodicWork(
            "renew_movs_recur_work",
            ExistingPeriodicWorkPolicy.KEEP,
            renewMovsRecurWork
        )

        val syncPeriodicWork = PeriodicWorkRequestBuilder<SyncWorker>(
            1,
            TimeUnit.MINUTES
        ).build()

        workManager.enqueueUniquePeriodicWork(
            "sync_worker_periodic",
            ExistingPeriodicWorkPolicy.KEEP,
            syncPeriodicWork
        )


        val syncOneTimeWork = OneTimeWorkRequestBuilder<SyncWorker>().build()

        workManager.enqueueUniqueWork(
            "sync_worker_on_start",
            ExistingWorkPolicy.REPLACE,
            syncOneTimeWork
        )
    }
}