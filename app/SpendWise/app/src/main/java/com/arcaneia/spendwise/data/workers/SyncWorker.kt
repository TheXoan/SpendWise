package com.arcaneia.spendwise.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.arcaneia.spendwise.apis.data.model.CategoriaRemoteDataSource
import com.arcaneia.spendwise.apis.data.model.CategoriaSyncRepository
import com.arcaneia.spendwise.apis.data.model.MovRecurRemoteDataSource
import com.arcaneia.spendwise.apis.data.model.MovRecurSyncRepository
import com.arcaneia.spendwise.apis.data.model.MovRemoteDataSource
import com.arcaneia.spendwise.apis.data.model.MovSyncRepository
import com.arcaneia.spendwise.data.database.AppDatabase

/**
 * Worker encargado de realizar la **sincronización completa** de la aplicación con PocketBase.
 *
 * Su misión es mantener la base de datos local perfectamente alineada con el servidor,
 * siguiendo un orden estricto para evitar inconsistencias y errores de integridad.
 *
 * ---
 * ## 🔄 Flujo completo de sincronización
 *
 * La sincronización se ejecuta siempre en el siguiente orden:
 *
 * ### **1. Categorías**
 * Es obligatorio empezar por aquí, ya que:
 * - Los movimientos simples y recurrentes dependen de las categorías.
 * - Se asegura que las claves foráneas apunten a registros válidos.
 *
 * ### **2. Movimientos Recurrentes**
 * - Descarga y actualiza movimientos recurrentes desde PocketBase.
 * - Subida de cambios locales pendientes.
 * - Refleja eliminaciones remotas.
 *
 * ### **3. Movimientos Simples**
 * - Sincroniza todos los movimientos normales.
 * - Soporta duplicados mediante `renew_hash`.
 * - Mantiene relación con categorías y mov_recur correctamente.
 *
 * ---
 * ## 🔔 Lanzar renovaciones automáticas
 *
 * Una vez finalizada la sincronización principal, el Worker:
 *
 * 1. **Ejecuta `RenewMovsRecurWorker` en segundo plano**, que se encarga de:
 *    - Detectar renovaciones pendientes
 *    - Crear movimientos recurrentes (si toca)
 *    - Subirlos al servidor
 *    - Notificar al usuario
 *    - Marcar como notificados
 *
 * 2. Esto garantiza que **cada dispositivo** reciba sus notificaciones locales,
 * incluso si las renovaciones fueron creadas en otro móvil.
 *
 * ---
 * ## ✔ Garantías del SyncWorker
 *
 * - Mantiene el orden correcto entre colecciones dependientes.
 * - Evita fallos de claves foráneas.
 * - Asegura paridad entre local y remoto.
 * - Mantiene lógica de negocio aislada en repositorios de sincronización.
 * - Tras sincronizar, dispara el Worker de renovaciones sin bloquear la UI.
 *
 * ---
 *
 * @constructor Recibe el contexto y parámetros del Worker.
 * @see RenewMovsRecurWorker Worker que procesa renovaciones y notificaciones.
 */
class SyncWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    /**
     * Ejecuta la sincronización completa con PocketBase.
     *
     * Si ocurre un error en cualquier punto, se retorna `Result.retry()` para que
     * WorkManager vuelva a intentarlo más tarde de manera automática.
     */
    override suspend fun doWork(): Result {
        return try {
            val db = AppDatabase.getDatabase(context)

            // --- Dependencias de Categoría ---
            val categoriaDao = db.categoriaDao()
            val remoteCategoria = CategoriaRemoteDataSource(context)
            val categoriaSyncRepository = CategoriaSyncRepository(
                local = categoriaDao,
                remote = remoteCategoria,
                context = context
            )

            // --- Dependencias Mov. Recurrentes ---
            val movRecurDao = db.movRecurDao()
            val remoteMovRecur = MovRecurRemoteDataSource(context)
            val movRecurSyncRepository = MovRecurSyncRepository(
                local = movRecurDao,
                remote = remoteMovRecur,
                context = context
            )

            // --- Dependencias Mov. Simples ---
            val movDao = db.movDao()
            val remoteMov = MovRemoteDataSource(context)
            val movSyncRepository = MovSyncRepository(
                local = movDao,
                remote = remoteMov,
                categoriaDao = categoriaDao,
                movRecurDao = movRecurDao,
                context = context
            )

            // ======================================================
            // =============== EJECUCIÓN DE SYNC ====================
            // ======================================================

            // A) Categorías primero (necesaria para todo lo demás)
            categoriaSyncRepository.sync()

            // B) Movimientos recurrentes
            movRecurSyncRepository.sync()

            // C) Movimientos simples
            movSyncRepository.sync()


            // ======================================================
            // =========== EJECUTAR AUTO-RENOVACIÓN =================
            // ======================================================
            // Este Worker procesará:
            //   - Renovaciones rezagadas
            //   - Notificaciones en ESTE dispositivo
            //
            // Garantiza que:
            //   ✔ No se dupliquen renovaciones (el Worker verifica fechas)
            //   ✔ Todos los dispositivos notifiquen localmente
            //   ✔ Las renovaciones creadas en otro móvil se notifiquen aquí también
            // ======================================================

            val renewWorker =
                OneTimeWorkRequestBuilder<RenewMovsRecurWorker>().build()

            WorkManager.getInstance(context)
                .enqueue(renewWorker)

            Result.success()

        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}