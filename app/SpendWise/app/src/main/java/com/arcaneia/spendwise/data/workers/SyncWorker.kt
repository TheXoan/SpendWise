package com.arcaneia.spendwise.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.arcaneia.spendwise.apis.data.model.CategoriaRemoteDataSource
import com.arcaneia.spendwise.data.database.AppDatabase
import com.arcaneia.spendwise.apis.data.model.CategoriaSyncRepository
import com.arcaneia.spendwise.apis.data.model.MovRemoteDataSource
import com.arcaneia.spendwise.apis.data.model.MovSyncRepository
import com.arcaneia.spendwise.apis.data.model.MovRecurRemoteDataSource
import com.arcaneia.spendwise.apis.data.model.MovRecurSyncRepository

/**
 * Worker encargado de ejecutar el proceso completo de sincronización entre la base de datos local
 * (Room) y el backend PocketBase.
 *
 * Este worker se ejecuta en segundo plano mediante WorkManager y realiza la sincronización en el
 * orden correcto para mantener la integridad referencial:
 *
 * ### Orden de sincronización:
 * 1. **Categorías**
 *    Deben sincronizarse primero ya que los movimientos simples y recurrentes dependen de ellas.
 *
 * 2. **Movimientos Recurrentes**
 *    No dependen de categorías sincronizadas pero sí deben estar listas antes de los movimientos simples,
 *    ya que estos pueden enlazar con movimientos recurrentes mediante `mov_recur_id`.
 *
 * 3. **Movimientos Simples**
 *    Requieren que tanto las categorías como los movimientos recurrentes ya estén sincronizados,
 *    puesto que deben convertir IDs locales en IDs remotos.
 *
 * Cada fase utiliza su respectivo repositorio de sincronización, que encapsula la lógica de:
 * - Subida de registros locales sin `remote_id`.
 * - Descarga y fusión de registros remotos.
 * - Eliminación de registros locales obsoletos.
 *
 * Si ocurre un error durante el proceso, el worker devuelve `Result.retry()` para que WorkManager
 * reintente automáticamente según la política configurada.
 *
 * @property context Contexto de la aplicación.
 * @property workerParams Parámetros proporcionados por WorkManager.
 */
class SyncWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    /**
     * Ejecuta el proceso completo de sincronización dentro de un entorno basado en corrutinas.
     *
     * @return [Result.success] si la sincronización finaliza correctamente,
     *         [Result.retry] si ocurre una excepción durante el proceso.
     */
    override suspend fun doWork(): Result {
        return try {
            val db = AppDatabase.getDatabase(context)

            // 1. Dependencias de Categoría
            val categoriaDao = db.categoriaDao()
            val remoteCategoria = CategoriaRemoteDataSource(context)
            val categoriaSyncRepository = CategoriaSyncRepository(
                local = categoriaDao,
                remote = remoteCategoria,
                context = context
            )

            // 2. Dependencias de Movimiento Recurrente
            val movRecurDao = db.movRecurDao()
            val remoteMovRecur = MovRecurRemoteDataSource(context)
            val movRecurSyncRepository = MovRecurSyncRepository(
                local = movRecurDao,
                remote = remoteMovRecur,
                context = context
            )

            // 3. Dependencias de Movimiento Simple
            val movDao = db.movDao()
            val remoteMov = MovRemoteDataSource(context)
            val movSyncRepository =
                MovSyncRepository(
                    local = movDao,
                    remote = remoteMov,
                    categoriaDao = categoriaDao,
                    movRecurDao = movRecurDao,
                    context = context
                )

            // --- EJECUCIÓN SECUENCIAL DE LA SINCRONIZACIÓN ---

            // A. Sincronización de categorías (DEBE ir primero)
            categoriaSyncRepository.sync()

            // B. Sincronización de movimientos recurrentes
            movRecurSyncRepository.sync()

            // C. Sincronización de movimientos simples (depende de categorías y recurrentes)
            movSyncRepository.sync()

            Result.success()

        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}