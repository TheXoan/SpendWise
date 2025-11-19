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

        val work = PeriodicWorkRequestBuilder<RenewMovsRecurWorker>(
            1, TimeUnit.HOURS
        ).build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "renewMovsRecur",
                ExistingPeriodicWorkPolicy.KEEP,
                work
            )
    }
}