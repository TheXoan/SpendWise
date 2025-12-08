package com.arcaneia.spendwise.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.arcaneia.spendwise.apis.data.model.CategoriaRemoteDataSource
import com.arcaneia.spendwise.data.database.AppDatabase
import com.arcaneia.spendwise.apis.data.model.CategoriaSyncRepository
import com.arcaneia.spendwise.apis.data.model.MovRemoteDataSource
import com.arcaneia.spendwise.apis.data.model.MovSyncRepository

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

            // Dependencias de Categoría
            val categoriaDao = db.categoriaDao()
            val remoteCategoria = CategoriaRemoteDataSource(context)
            val categoriaSyncRepository = CategoriaSyncRepository(
                local = categoriaDao,
                remote = remoteCategoria,
                context = context
            )

            // Dependencias de Movimiento Simple
            val movDao = db.movDao() // Suponiendo que ya lo definiste en AppDatabase
            val remoteMov =
                MovRemoteDataSource(
                    context
                )

            // Requerimos el DAO de MovRecur para mapear si el Movimiento es recurrente
            val movRecurDao = db.movRecurDao() // Suponiendo que ya lo definiste en AppDatabase

            val movSyncRepository =
                MovSyncRepository(
                    local = movDao,
                    remote = remoteMov,
                    categoriaDao = categoriaDao,
                    movRecurDao = movRecurDao,
                    context = context
                )


            // Sincronización de categorías (debe ir primero para tener los IDs de categoría listos)
            categoriaSyncRepository.sync()

            // Sincronización de movimientos simples
            movSyncRepository.sync()

            // Aquí más adelante añadirás:
            // movRecurSyncRepository.sync()

            Result.success()

        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}