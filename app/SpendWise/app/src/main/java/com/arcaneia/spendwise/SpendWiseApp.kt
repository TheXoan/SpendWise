package com.arcaneia.spendwise

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.arcaneia.spendwise.data.workers.RenewMovsRecurWorker
import java.util.concurrent.TimeUnit


class SpendWiseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val workManager = WorkManager.getInstance(this)

        val periodicWork = PeriodicWorkRequestBuilder<RenewMovsRecurWorker>(
            15, TimeUnit.MINUTES).build()

        workManager.enqueueUniquePeriodicWork(
            "renew_movs_recur_work",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWork
        )
    }
}