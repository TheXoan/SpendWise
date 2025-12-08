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
 * Repositorio encargado de sincronizar los movimientos simples entre la base de datos local
 * (Room) y PocketBase.
 *
 * Se encarga de la subida de movimientos locales al servidor, la descarga de movimientos
 * remotos y la fusión de cambios, y la eliminación de movimientos borrados remotamente.
 *
 * Requiere acceso a los DAOs de [Categoria] y [MovRecur] para resolver el mapeo entre
 * IDs locales y remotos para las relaciones, garantizando la integridad referencial.
 *
 * @property local DAO (Data Access Object) para la entidad [Mov].
 * @property remote Fuente de datos remota para la entidad [Mov] (API de PocketBase).
 * @property categoriaDao DAO para la entidad [Categoria], usado para mapear IDs locales a remotos y viceversa.
 * @property movRecurDao DAO para la entidad [MovRecur], usado para mapear IDs locales a remotos.
 * @property context Contexto de la aplicación, usado para acceder a [TokenDataStore].
 */
class MovSyncRepository(
    private val local: MovDao,
    private val remote: MovRemoteDataSource,
    private val categoriaDao: CategoriaDao,
    private val movRecurDao: MovRecurDao,
    private val context: Context
) {

    /**
     * Obtiene todos los movimientos locales que aún no fueron subidos al servidor
     * (es decir, aquellos cuyo `remote_id` es nulo o vacío).
     *
     * @return Una lista de movimientos [Mov] pendientes de subir.
     */
    suspend fun getPendingToUpload(): List<Mov> = local.getPendingToUpload()

    /**
     * Busca un movimiento local usando su ID remoto.
     *
     * @param remoteId Identificador único asignado por PocketBase.
     * @return El movimiento [Mov] local que coincide con el ID remoto, o `null` si no existe.
     */
    suspend fun getByRemoteId(remoteId: String): Mov? = local.getByRemoteId(remoteId)

    /**
     * Obtiene todos los movimientos locales que ya tienen un ID remoto asignado.
     * Estos movimientos son candidatos para ser comparados con los registros remotos
     * para detectar borrados.
     *
     * @return Una lista de movimientos [Mov] con un `remote_id` válido.
     */
    suspend fun getAllWithRemoteId(): List<Mov> = local.getAllWithRemoteId()

    /**
     * Asigna un identificador remoto (`remote_id`) a un movimiento local existente,
     * marcándolo como sincronizado.
     *
     * @param localId ID de Room del movimiento.
     * @param remoteId ID asignado por PocketBase tras la creación.
     */
    suspend fun attachRemoteId(localId: Int, remoteId: String) = local.attachRemoteId(localId, remoteId)

    // ---------------------------------------------------------------------------------

    /**
     * Ejecuta la lógica principal de sincronización:
     * 1. **Subida:** Sube todos los movimientos pendientes locales al servidor.
     * 2. **Descarga:** Descarga todos los movimientos remotos asociados al usuario.
     * 3. **Fusión (Merge):** Inserta nuevos movimientos remotos en local o actualiza los existentes.
     * 4. **Borrados:** Elimina movimientos locales que ya no existen en el servidor.
     *
     * El proceso de descarga garantiza que el movimiento remoto solo se fusione si la
     * categoría asociada ya ha sido sincronizada y existe localmente.
     */
    suspend fun sync() {
        // 1. Obtención Segura del UserId
        val userId = TokenDataStore.getUserId(context).first()
        if (userId.isNullOrBlank()) {
            return
        }

        // 1) SUBIR movimientos locales sin remote_id
        val pendingLocal = getPendingToUpload()
        for (mov in pendingLocal) {
            // A) Mapear ID de Categoría local a ID de PocketBase (categoriaPBId)
            val catLocal = categoriaDao.getById(mov.categoria_id)
            val categoriaPBId = catLocal?.remote_id

            // Si la categoría no tiene ID remoto, saltamos la subida de este Mov.
            if (categoriaPBId.isNullOrBlank()) {
                continue
            }

            // B) Mapear ID de MovRecur local a ID de PocketBase (movRecurPBId)
            var movRecurPBId: String? = null
            if (mov.mov_recur_id != null) {
                val movRecurLocal = movRecurDao.getById(mov.mov_recur_id)
                movRecurPBId = movRecurLocal?.remote_id
            }

            try {
                val created = remote.create(userId, mov, categoriaPBId, movRecurPBId)
                attachRemoteId(mov.id, created.id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // 2) DESCARGAR movimientos remotos
        val remoteMovs = remote.fetchAll(userId)

        // 3) Sincronizar remoto → local (merge)
        for (rem in remoteMovs) {

            // A) Mapear ID de PocketBase de Categoría a ID de Room (categoriaLocalId)
            val catLocal = categoriaDao.getByRemoteId(rem.categoria_id)

            // Si la categoría remota no se ha sincronizado localmente, se salta el movimiento.
            if (catLocal == null) {
                continue
            }
            val categoriaLocalId = catLocal.id // ID local (Int)

            // B) Mapear ID de PocketBase de MovRecur a ID de Room (movRecurLocalId)
            var movRecurLocalId: Int? = null
            if (rem.mov_recur_id != null) {
                movRecurLocalId = movRecurDao.getByRemoteId(rem.mov_recur_id)?.id
            }

            // Convierte el tipo (INGRESO/GASTO), forzando mayúsculas.
            val tipoEnum = TypeMov.valueOf((rem.tipo ?: "ERR").uppercase())

            // Ver si existe localmente por remote_id
            val localMatch = getByRemoteId(rem.id)

            if (localMatch == null) {
                // Insertar nuevo movimiento en local
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
                // Actualizar si es necesario
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

        // 4) BORRADOS remotos
        val remoteIds = remoteMovs.map { it.id }.toSet()
        val localWithRemote = getAllWithRemoteId()

        for (mov in localWithRemote) {
            if (mov.remote_id !in remoteIds) {
                // Si el registro existe localmente pero no viene del servidor, se borró remotamente.
                local.delete(mov)
            }
        }
    }
}