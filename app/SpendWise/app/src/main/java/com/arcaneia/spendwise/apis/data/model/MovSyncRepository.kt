package com.arcaneia.spendwise.apis.data.model

import android.content.Context
import com.arcaneia.spendwise.data.dao.CategoriaDao
import com.arcaneia.spendwise.data.dao.MovDao
import com.arcaneia.spendwise.data.dao.MovRecurDao
import com.arcaneia.spendwise.data.datastore.TokenDataStore
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.model.TypeMov
import kotlinx.coroutines.flow.first

/**
 * Repositorio encargado de sincronizar los **movimientos simples** (`mov`) entre la base de datos
 * local (Room) y el backend PocketBase.
 *
 * Este repositorio implementa un proceso completo de sincronización bidireccional,
 * permitiendo mantener coherencia entre los datos locales y los datos remotos.
 *
 * ## Funciones principales
 *
 * ### 1. **Subida de movimientos locales pendientes**
 * Sube todos los movimientos cuyo `remote_id` es `null`, creando su equivalente en PocketBase
 * y vinculando el ID remoto resultante.
 *
 * ### 2. **Descarga de movimientos remotos**
 * Descarga todos los movimientos pertenecientes al usuario autenticado, mapeando sus relaciones
 * a IDs locales.
 *
 * ### 3. **Merge remoto → local**
 * - Si el movimiento remoto no existe localmente → se **inserta**.
 * - Si ya existe → se **actualiza**, conservando el `remote_id`.
 *
 * ### 4. **Detección de borrados**
 * Todo movimiento que exista en local pero no en el servidor se elimina localmente.
 *
 * ## Dependencias requeridas
 *
 * - **MovDao** para CRUD local y funciones auxiliares de sincronización.
 * - **CategoriaDao** para mapear `categoria_id` remoto ↔ local.
 * - **MovRecurDao** para mapear `mov_recur_id` remoto ↔ local.
 * - **MovRemoteDataSource** para realizar peticiones a PocketBase.
 *
 * Este repositorio garantiza la integridad referencial durante todo el proceso,
 * asegurando que los IDs remotos y locales de categorías y movimientos recurrentes
 * estén correctamente enlazados.
 *
 * @property local DAO para movimientos simples ([Mov]).
 * @property remote Data source remoto para la colección de movimientos en PocketBase.
 * @property categoriaDao DAO para categorías, necesario para resolver referencias remotas.
 * @property movRecurDao DAO para movimientos recurrentes, necesario para mapear relaciones.
 * @property context Contexto para obtener el token del usuario mediante [TokenDataStore].
 */
class MovSyncRepository(
    private val local: MovDao,
    private val remote: MovRemoteDataSource,
    private val categoriaDao: CategoriaDao,
    private val movRecurDao: MovRecurDao,
    private val context: Context
) {

    /**
     * Obtiene todos los movimientos locales que aún no han sido subidos al servidor,
     * es decir, aquellos cuyo `remote_id` es `null`.
     *
     * @return Lista de movimientos pendientes.
     */
    suspend fun getPendingToUpload(): List<Mov> =
        local.getPendingToUpload()

    /**
     * Busca un movimiento local usando su ID remoto.
     *
     * @param remoteId ID remoto del movimiento en PocketBase.
     * @return Movimiento local correspondiente o `null` si no existe.
     */
    suspend fun getByRemoteId(
        remoteId: String
    ): Mov? =
        local.getByRemoteId(remoteId)

    /**
     * Obtiene todos los movimientos locales que se encuentran sincronizados,
     * es decir, aquellos con un `remote_id` no nulo.
     *
     * @return Lista de movimientos sincronizados.
     */
    suspend fun getAllWithRemoteId(): List<Mov> =
        local.getAllWithRemoteId()

    /**
     * Asocia un ID remoto a un movimiento local tras su creación en PocketBase.
     *
     * @param localId ID interno de Room.
     * @param remoteId ID generado por PocketBase.
     */
    suspend fun attachRemoteId(
        localId: Int,
        remoteId: String
    ) =
        local.attachRemoteId(localId, remoteId)

    // ---------------------------------------------------------------------------------

    /**
     * Ejecuta el proceso completo de sincronización de movimientos simples.
     *
     * ### Flujo de sincronización
     * 1. Obtiene el `userId` actual.
     * 2. Sube todos los movimientos locales sin `remote_id`.
     * 3. Descarga todos los movimientos remotos del usuario.
     * 4. Fusiona cada movimiento remoto en la base de datos local.
     * 5. Elimina localmente los movimientos que fueron borrados en el servidor.
     *
     * El proceso asegura la consistencia total entre local y remoto manteniendo
     * la integridad referencial de categorías y movimientos recurrentes.
     */
    suspend fun sync() {
        // 1) Obtener userId
        val userId =
            TokenDataStore.getUserId(context).first()
        if (userId.isNullOrBlank()) return

        // ----------------------------
        // 2) SUBIR movimientos locales sin remote_id
        // ----------------------------
        val pendingLocal = getPendingToUpload()
        for (mov in pendingLocal) {

            // Mapear categoría local → remoto
            val catLocal = categoriaDao.getById(mov.categoria_id)
            val categoriaPBId = catLocal?.remote_id ?: continue

            // Mapear movimiento recurrente local → remoto
            var movRecurPBId: String? = null
            if (mov.mov_recur_id != null) {
                val movRecurLocal = movRecurDao.getById(mov.mov_recur_id)
                movRecurPBId = movRecurLocal?.remote_id
            }

            try {
                val created = remote.create(
                    userId,
                    mov,
                    categoriaPBId,
                    movRecurPBId
                )
                attachRemoteId(mov.id, created.id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // ----------------------------
        // 3) DESCARGAR movimientos remotos
        // ----------------------------
        val remoteMovs = remote.fetchAll(userId)

        // ----------------------------
        // 4) MERGE remoto → local
        // ----------------------------
        for (rem in remoteMovs) {

            // Mapear categoría remota → local
            val catLocal = categoriaDao.getByRemoteId(rem.categoria_id) ?: continue
            val categoriaLocalId = catLocal.id

            // Mapear movimiento recurrente remoto → local
            var movRecurLocalId: Int? = null
            if (rem.mov_recur_id != null) {
                movRecurLocalId =
                    movRecurDao.getByRemoteId(rem.mov_recur_id)?.id
            }

            // Convertir tipo de movimiento
            val tipoEnum =
                TypeMov.valueOf((rem.tipo ?: "ERR").uppercase())

            // Verificar si existe localmente
            val localMatch = getByRemoteId(rem.id)

            if (localMatch == null) {
                // Insertar nuevo
                val nuevo = Mov(
                    tipo = tipoEnum,
                    importe = rem.importe,
                    data_mov = rem.data_mov,
                    descricion = rem.descricion,
                    categoria_id = categoriaLocalId,
                    mov_recur_id = movRecurLocalId,
                    remote_id = rem.id
                )
                local.insert(nuevo)
            } else {
                // Actualizar existente
                val actualizado = localMatch.copy(
                    tipo = tipoEnum,
                    importe = rem.importe,
                    data_mov = rem.data_mov,
                    descricion = rem.descricion,
                    categoria_id = categoriaLocalId,
                    mov_recur_id = movRecurLocalId
                )
                local.update(actualizado)
            }
        }

        // ----------------------------
        // 5) BORRADOS remotos → eliminación local
        // ----------------------------

        val remoteIds = remoteMovs.map { it.id }.toSet()
        val localWithRemote = getAllWithRemoteId()

        for (mov in localWithRemote) {

            if (mov.remote_id !in remoteIds) {

                mov.remote_id?.let {
                    local.deleteByRemoteId(it)
                }
            }
        }
    }
}