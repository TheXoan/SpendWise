package com.arcaneia.spendwise.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.arcaneia.spendwise.apis.data.model.CategoriaRemoteDataSource
import com.arcaneia.spendwise.data.database.AppDatabase
import com.arcaneia.spendwise.apis.data.model.CategoriaSyncRepository

/**
 * Worker encargado de sincronizar datos locales con PocketBase.
 *
 * Por ahora solo sincroniza categorías, pero luego añadiremos:
 *  - MovSyncRepository
 *  - MovRecurSyncRepository
 */
class SyncWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val db = AppDatabase.getDatabase(context)

            val categoriaDao = db.categoriaDao()
            val remoteCategoria = CategoriaRemoteDataSource(context)

            val categoriaSyncRepository = CategoriaSyncRepository(
                local = categoriaDao,
                remote = remoteCategoria,
                context = context
            )

            // Sincronización de categorías
            categoriaSyncRepository.sync()

            // Aquí más adelante añadirás:
            // movSyncRepository.sync()
            // movRecurSyncRepository.sync()

            Result.success()

        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}