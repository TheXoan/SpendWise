package com.arcaneia.spendwise.apis.data.model

import android.content.Context
import com.arcaneia.spendwise.data.dao.CategoriaDao
import com.arcaneia.spendwise.data.datastore.TokenDataStore
import com.arcaneia.spendwise.data.entity.Categoria
import kotlinx.coroutines.flow.first

/**
 * Repositorio encargado de sincronizar las categorías entre la base de datos local
 * y el origen de datos remoto.
 *
 * Este repositorio aplica una estrategia de sincronización en cuatro fases:
 * 1. Subida de categorías locales sin `remote_id`.
 * 2. Descarga de categorías remotas del servidor.
 * 3. Proceso de merge remoto → local.
 * 4. Eliminación de categorías que fueron borradas en el servidor.
 *
 * @property local Acceso DAO para la base de datos local.
 * @property remote Fuente de datos remota utilizada para operaciones CRUD.
 * @property context Contexto necesario para obtener credenciales del usuario.
 */
class CategoriaSyncRepository(
    private val local: CategoriaDao,
    private val remote: CategoriaRemoteDataSource,
    private val context: Context
) {

    /**
     * Ejecuta la sincronización completa entre los datos locales y remotos.
     *
     * Este proceso contempla:
     * - Envío de categorías locales sin sincronizar al servidor.
     * - Descarga de todas las categorías remotas asociadas al usuario.
     * - Inserción o actualización de las categorías locales según los datos remotos.
     * - Eliminación de categorías que existan localmente pero ya no estén en el servidor.
     *
     * La categoría especial "Recurrente" se excluye de la sincronización tanto
     * en subida como en descarga o actualización.
     *
     * @throws Exception Si ocurre algún error durante la comunicación remota.
     */
    suspend fun sync() {
        val userId = TokenDataStore.getUserId(context).first()!!

        // 1) SUBIR categorías locales sin remote_id
        val pendingLocal = local.getPendingToUpload()
        for (cat in pendingLocal) {

            if (cat.id == 1 || cat.nome == "Recurrente") continue

            val created = remote.create(userId, cat)
            local.attachRemoteId(cat.id, created.id)
        }

        // 2) DESCARGAR categorías remotas
        val remoteCats = remote.fetchAll(userId)

        // 3) Sincronizar remoto → local (merge)
        for (rem in remoteCats) {

            // EXCLUIR RECURRENTE del servidor
            if (rem.nome == "Recurrente") continue

            // Ver si existe localmente por remote_id
            val localMatch = local.getByRemoteId(rem.id)

            if (localMatch == null) {
                // insertar nueva categoría en local
                val nueva =
                    Categoria(
                        nome = rem.nome,
                        tipo = rem.tipo,
                        remote_id = rem.id
                    )
                local.insert(nueva)
            } else {
                // actualizar si es necesario
                val actualizado = localMatch.copy(
                    nome = rem.nome,
                    tipo = rem.tipo
                )
                local.update(actualizado)
            }
        }

        // 4) BORRADOS remotos (categorías borradas en PB)
        val remoteIds = remoteCats.map { it.id }.toSet()
        val localWithRemote = local.getAllWithRemoteId()

        for (cat in localWithRemote) {
            if (cat.remote_id !in remoteIds) {

                try {
                    remote.delete(cat.remote_id!!)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                local.delete(cat)
            }
        }
    }
}