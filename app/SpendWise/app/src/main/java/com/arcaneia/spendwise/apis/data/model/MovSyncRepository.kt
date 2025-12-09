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
 * Repositorio encargado de sincronizar la colección de **movimientos simples (mov)** entre
 * la base de datos local (Room) y la base de datos remota en PocketBase.
 *
 * Esta clase constituye el núcleo del sistema de sincronización de movimientos, manteniendo
 * la integridad y consistencia entre múltiples dispositivos y evitando duplicados mediante
 * el campo `renew_hash`, utilizado especialmente para movimientos generados desde
 * renovaciones recurrentes.
 *
 * ---
 *
 * ## 🔄 Funciones principales del sincronizador
 *
 * La función [sync] implementa el flujo completo de sincronización:
 *
 * ### **1. Subida de movimientos locales pendientes**
 * Todos los movimientos cuyo `remote_id` es `null` se consideran pendientes de subir.
 * Estos incluyen:
 * - Movimientos creados manualmente por el usuario.
 * - Movimientos generados automáticamente por renovaciones recurrentes.
 *
 * Para cada uno:
 * - Se mapean sus relaciones (categoría y movimiento recurrente) a IDs remotos.
 * - Se envía el movimiento a PocketBase vía [MovRemoteDataSource.create].
 * - Se adjunta el `remote_id` recibido del servidor mediante `attachRemoteId`.
 *
 * ---
 *
 * ### **2. Descarga de movimientos remotos**
 * Se recuperan todos los registros remotos pertenecientes al usuario autenticado.
 *
 * ---
 *
 * ### **3. Merge remoto → local**
 * Por cada movimiento remoto se realiza:
 *
 * - Registro del ID remoto en la lista `remoteIds`.
 * - Si el movimiento tiene `renew_hash`:
 *   - Se detecta si ya existe el movimiento local correspondiente.
 *   - Se evita crear duplicados.
 * - Si existe localmente → se actualiza.
 * - Si no existe → se inserta un nuevo registro local.
 *
 * Todas las relaciones remotas (`categoria_id`, `mov_recur_id`) se convierten
 * a sus IDs locales mediante `CategoriaDao` y `MovRecurDao`.
 *
 * ---
 *
 * ### **4. Eliminación de movimientos locales borrados en PocketBase**
 * Cualquier movimiento local que posea un `remote_id` que ya no existe en PocketBase
 * será eliminado automáticamente del dispositivo.
 *
 * Esto garantiza la coherencia entre dispositivos y respeta eliminaciones remotas.
 *
 * ---
 *
 * ## 🧩 Consideraciones importantes
 *
 * - Esta clase no interactúa directamente con la capa de UI.
 * - No procesa renovaciones recurrentes (esa responsabilidad pertenece a `MovRecurRepository`).
 * - No muestra notificaciones (esto lo realiza `RenewMovsRecurWorker`).
 * - Supone que todos los DAOs están correctamente configurados para soportar sincronización.
 *
 * ---
 *
 * @property local DAO de Room para acceder y modificar movimientos locales.
 * @property remote Fuente de datos remota para interactuar con PocketBase.
 * @property categoriaDao DAO usado para mapear IDs remotos/locales de categorías.
 * @property movRecurDao DAO usado para mapear IDs remotos/locales de movimientos recurrentes.
 * @property context Contexto para acceder a DataStore y recuperar el `userId`.
 */
class MovSyncRepository(
    private val local: MovDao,
    private val remote: MovRemoteDataSource,
    private val categoriaDao: CategoriaDao,
    private val movRecurDao: MovRecurDao,
    private val context: Context
) {

    /**
     * Ejecuta la sincronización completa de movimientos simples.
     *
     * El proceso se realiza secuencialmente:
     *
     * 1. **Subida local → remoto**
     *    Se envían todos los movimientos pendientes (`remote_id == null`).
     *
     * 2. **Descarga remoto → dispositivo**
     *    Se obtienen todos los movimientos del usuario desde PocketBase.
     *
     * 3. **Fusión remoto/local (merge)**
     *    - Se actualizan registros existentes.
     *    - Se insertan los nuevos.
     *    - Se detectan duplicados mediante `renew_hash`.
     *
     * 4. **Eliminación local**
     *    Cualquier movimiento local cuyo `remote_id` ya no exista en PocketBase
     *    se elimina del dispositivo.
     *
     * Esta operación garantiza consistencia total entre dispositivos sin duplicar
     * movimientos generados automáticamente por renovaciones recurrentes.
     */
    suspend fun sync() {
        val userId = TokenDataStore.getUserId(context).first() ?: return

        // ---------- 1. Subir movimientos locales ----------
        val pending = local.getPendingToUpload()
        for (mov in pending) {

            val catPB = categoriaDao.getById(mov.categoria_id)?.remote_id ?: continue
            val recurPB = mov.mov_recur_id?.let { movRecurDao.getById(it)?.remote_id }

            val created = remote.create(
                userId = userId,
                mov = mov,
                categoriaPBId = catPB,
                movRecurPBId = recurPB
            )

            local.attachRemoteId(mov.id, created.id)
        }

        // ---------- 2. Descargar movimientos ----------
        val remoteMovs = remote.fetchAll(userId)

        val remoteIds = mutableSetOf<String>()

        for (rem in remoteMovs) {
            remoteIds.add(rem.id)

            // Detectar duplicados por renew_hash
            rem.renew_hash?.let { hash ->
                val localDup = local.getByRenewHash(hash)
                if (localDup != null) {
                    // Actualizar remote_id si no estaba
                    if (localDup.remote_id == null)
                        local.attachRemoteId(localDup.id, rem.id)

                    continue
                }
            }

            val localExisting = local.getByRemoteId(rem.id)

            val tipoEnum = TypeMov.valueOf(rem.tipo!!.uppercase())
            val catLocal = categoriaDao.getByRemoteId(rem.categoria_id) ?: continue
            val recurLocal = rem.mov_recur_id?.let { movRecurDao.getByRemoteId(it) }?.id

            if (localExisting == null) {
                local.insert(
                    Mov(
                        tipo = tipoEnum,
                        importe = rem.importe,
                        data_mov = rem.data_mov,
                        descricion = rem.descricion,
                        categoria_id = catLocal.id,
                        mov_recur_id = recurLocal,
                        renew_hash = rem.renew_hash,
                        remote_id = rem.id
                    )
                )
            } else {
                local.update(
                    localExisting.copy(
                        tipo = tipoEnum,
                        importe = rem.importe,
                        data_mov = rem.data_mov,
                        descricion = rem.descricion,
                        categoria_id = catLocal.id,
                        mov_recur_id = recurLocal,
                        renew_hash = rem.renew_hash
                    )
                )
            }
        }

        // ---------- 3. Eliminar movimientos borrados ----------
        val localWithRemote = local.getAllWithRemoteId()
        for (mov in localWithRemote) {
            if (mov.remote_id !in remoteIds) {
                local.deleteByRemoteId(mov.remote_id!!)
            }
        }
    }

}