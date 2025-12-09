package com.arcaneia.spendwise.apis.data.model

import android.content.Context
import com.arcaneia.spendwise.data.dao.CategoriaDao
import com.arcaneia.spendwise.data.datastore.TokenDataStore
import com.arcaneia.spendwise.data.entity.Categoria
import kotlinx.coroutines.flow.first

/**
 * Repositorio responsable de sincronizar las categorías entre la base de datos local (Room)
 * y la base de datos remota (PocketBase).
 *
 * Este repositorio implementa una estrategia híbrida que garantiza:
 *
 * - La asignación correcta del `remote_id` a las categorías locales.
 * - La detección y vinculación especial de la categoría **"Recurrente"**, evitando duplicaciones.
 * - La subida de categorías locales que aún no han sido sincronizadas.
 * - La descarga y fusión de cambios realizados en el servidor.
 * - La eliminación local de categorías que fueron borradas en el servidor
 *   (exceptuando "Recurrente").
 *
 * La sincronización mantiene la coherencia entre dispositivos y evita la creación
 * de categorías duplicadas durante el proceso de login en múltiples terminales.
 *
 * @property local Instancia de [CategoriaDao] utilizada para ejecutar operaciones
 * de lectura/escritura en la base de datos local.
 *
 * @property remote Fuente de datos remota encargada de realizar las operaciones
 * de red contra la colección de categorías en PocketBase.
 *
 * @property context Contexto de la aplicación, necesario para acceder a [TokenDataStore]
 * y recuperar el identificador del usuario autenticado.
 */
class CategoriaSyncRepository(
    private val local: CategoriaDao,
    private val remote: CategoriaRemoteDataSource,
    private val context: Context
) {

    /**
     * Ejecuta el proceso completo de sincronización entre la base de datos local y remota.
     *
     * La sincronización se ejecuta en **cuatro fases**:
     *
     * ### 1) Descarga inicial desde PocketBase
     * - Se obtienen todas las categorías remotas del usuario.
     * - Se detecta la existencia de la categoría especial `"Recurrente"`.
     * - Si existe en remoto, se vincula al registro local de ID fijo `1` (creado por el callback de Room).
     *
     * ### 2) Subida de categorías locales pendientes
     * - Se obtienen todas las categorías locales con `remote_id = null`.
     * - Se suben al servidor una por una.
     * - **Excepción:** si la categoría se llama `"Recurrente"` y ya existe en remoto, no se sube.
     *
     * ### 3) Fusión de categorías remotas → locales
     * - Si una categoría remota no existe localmente, se inserta.
     * - Si existe pero cambió algún campo, se actualiza.
     * - `"Recurrente"` se omite aquí para evitar duplicados.
     *
     * ### 4) Eliminación de categorías locales borradas en el servidor
     * - Se borran localmente las categorías cuyo `remote_id` ya no existe en remoto.
     * - **Excepción:** `"Recurrente"` nunca se elimina localmente.
     *
     * Esta función garantiza la integridad de datos y evita duplicaciones incluso cuando
     * se inicia sesión en múltiples dispositivos.
     */
    suspend fun sync() {
        val userId = TokenDataStore.getUserId(context).first()!!

        // 1) Descargar remoto primero (lo necesitamos para detectar Recurrente remota)
        val remoteCats = remote.fetchAll(userId)
        val remoteIds = remoteCats.map { it.id }.toSet()
        val remoteRecurrente = remoteCats.find { it.nome == "Recurrente" }

        // Si remoto tiene "Recurrente", enlazar local ID=1
        if (remoteRecurrente != null) {
            val localRec = local.getById(1)
            if (localRec != null) {
                local.attachRemoteId(1, remoteRecurrente.id)
            }
        }

        // 2) Subir locales sin remote_id (EXCEPTO Recurrente si ya existe remoto)
        val pendingLocal = local.getPendingToUpload()

        for (cat in pendingLocal) {

            // --- NO SUBIR RECURRENTE SI YA EXISTE EN PB ---
            if (cat.nome == "Recurrente" && remoteRecurrente != null) {
                continue
            }

            val created = remote.create(userId, cat)
            local.attachRemoteId(cat.id, created.id)
        }

        // 3) MERGE remoto → local
        for (rem in remoteCats) {

            // Si es Recurrente → ya se enlazó arriba, evitar duplicar
            if (rem.nome == "Recurrente") continue

            val localMatch = local.getByRemoteId(rem.id)

            if (localMatch == null) {
                local.insert(
                    Categoria(
                        nome = rem.nome,
                        tipo = rem.tipo,
                        remote_id = rem.id
                    )
                )
            } else {
                // update si cambió
                val needsUpdate =
                    localMatch.nome != rem.nome || localMatch.tipo != rem.tipo

                if (needsUpdate) {
                    local.update(
                        localMatch.copy(
                            nome = rem.nome,
                            tipo = rem.tipo
                        )
                    )
                }
            }
        }

        // 4) BORRADOS remotos → NO borrar Recurrente
        val localWithRemote = local.getAllWithRemoteId()

        for (cat in localWithRemote) {

            if (cat.nome == "Recurrente") continue

            if (cat.remote_id !in remoteIds) {
                local.delete(cat)
            }
        }
    }
}