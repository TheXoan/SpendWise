package com.arcaneia.spendwise.data.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.arcaneia.spendwise.data.di.ServiceLocator

class RenewMovsRecurWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        Log.i("WORKER", "RenewMovsRecurWorker ejecutado")

        val repo = ServiceLocator.getMovRecurRepository(applicationContext)

        val count = repo.getPendingRenewalsCount()
        Log.i("WORKER", "Movimientos que deberían renovarse: $count")

        repo.processRenewals()

        Log.i("WORKER", "Renovación completada")

        return Result.success()
    }
}