package com.arcaneia.spendwise.apis.data.model

import android.content.Context
import com.arcaneia.spendwise.data.dao.MovRecurDao
import com.arcaneia.spendwise.data.datastore.TokenDataStore
import com.arcaneia.spendwise.data.entity.MovRecur
import com.arcaneia.spendwise.data.model.Recurrence
import com.arcaneia.spendwise.data.model.TypeMov
import kotlinx.coroutines.flow.first

/**
 * Repositorio responsable de la **sincronización completa** de los movimientos recurrentes
 * (`mov_recur`) entre la base de datos local (Room) y el servidor PocketBase.
 *
 * Esta clase centraliza el flujo de sincronización, gestionando:
 *
 * 1. **Subida de movimientos recurrentes locales pendientes**
 *    (aquellos sin `remote_id`, aún no creados en PocketBase).
 *
 * 2. **Descarga de todos los movimientos recurrentes remotos**
 *    pertenecientes al usuario autenticado.
 *
 * 3. **Fusión remota → local (merge)**
 *    - Inserción de nuevos registros remotos.
 *    - Actualización de registros locales existentes.
 *    - Conversión segura de enums remotos (`Recurrence`, `TypeMov`).
 *
 * 4. **Eliminación local de movimientos recurrentes borrados remotamente.**
 *
 * Este repositorio forma parte del sistema de sincronización general de la aplicación,
 * y se ejecuta normalmente:
 * - Tras login,
 * - Tras cambios en red,
 * - O tras operaciones CRUD locales que afecten a datos con vinculación remota.
 *
 * @property local DAO local para la entidad [MovRecur], usado para leer/escribir en Room.
 * @property remote Fuente de datos remota encargada de la comunicación con PocketBase.
 * @property context Contexto de la aplicación necesario para acceder a [TokenDataStore].
 */
class MovRecurSyncRepository(
    private val local: MovRecurDao,
    private val remote: MovRecurRemoteDataSource,
    private val context: Context
) {

    /**
     * Ejecuta el proceso **completo de sincronización** entre local ↔ remoto.
     *
     * Este método orquesta todas las fases del proceso:
     *
     * 1. `uploadPendingMovRecur()` — Subida de registros locales nuevos.
     * 2. `downloadAndMergeMovRecur()` — Descarga y fusión de registros remotos.
     * 3. `deleteStaleMovRecur()` — Eliminación local de elementos removidos en el servidor.
     *
     * Si el usuario no tiene un `userId` válido, la sincronización se cancela silenciosamente.
     */
    suspend fun sync() {
        val userId = TokenDataStore.getUserId(context).first()
        if (userId.isNullOrBlank()) {
            return
        }

        // 1) SUBIR movimientos recurrentes locales sin remote_id
        uploadPendingMovRecur(userId)

        // 2) DESCARGAR y fusionar movimientos recurrentes remotos
        val remoteMovRecur = downloadAndMergeMovRecur(userId)

        // 3) BORRADOS remotos
        deleteStaleMovRecur(remoteMovRecur)
    }

    // -------------------------------------------------------------------------
    // FASE 1: Subida de movimientos recurrentes locales sin remote_id
    // -------------------------------------------------------------------------

    /**
     * Sube todos los movimientos recurrentes locales que aún no han sido enviados a PocketBase,
     * es decir, aquellos cuyo campo `remote_id` es `null`.
     *
     * Cada elemento se crea en el servidor mediante [MovRecurRemoteDataSource.create],
     * y posteriormente su `remote_id` se almacena en la base de datos local.
     *
     * @param userId Identificador remoto del usuario propietario en PocketBase.
     */
    private suspend fun uploadPendingMovRecur(userId: String) {
        val pendingLocal = local.getPendingToUpload()
        for (movRecur in pendingLocal) {
            try {
                val created = remote.create(userId, movRecur)
                local.attachRemoteId(movRecur.id, created.id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // -------------------------------------------------------------------------
    // FASE 2: Descarga y fusión remoto → local
    // -------------------------------------------------------------------------

    /**
     * Descarga todos los movimientos recurrentes remotos pertenecientes al usuario
     * e integra esos datos en la base de datos local.
     *
     * Para cada registro remoto:
     * - Si no existe localmente → **se inserta**.
     * - Si existe localmente → **se actualiza**.
     *
     * Además, convierte los enums remotos (`String`) a enums locales [Recurrence]
     * y [TypeMov], manejando casos inválidos de manera segura.
     *
     * @param userId Identificador remoto del usuario.
     * @return Lista completa de registros remotos descargados.
     */
    private suspend fun downloadAndMergeMovRecur(userId: String): List<MovRecurRecord> {

        val remoteMovRecur = remote.fetchAll(userId)

        for (rem in remoteMovRecur) {
            val localMatch = local.getByRemoteId(rem.id)

            // Mapeo seguro de enums recibidos como strings
            val recurrenceEnum = try {
                rem.periodicidade?.let { Recurrence.valueOf(it.uppercase()) }
            } catch (e: IllegalArgumentException) { null }

            val typeMovEnum = try {
                rem.tipo?.let { TypeMov.valueOf(it.uppercase()) }
            } catch (e: IllegalArgumentException) { null }

            if (localMatch == null) {
                // Insertar nuevo
                val nuevo = MovRecur(
                    nome = rem.nome,
                    importe = rem.importe,
                    periodicidade = recurrenceEnum,
                    data_ini = rem.data_ini,
                    data_rnv = rem.data_rnv,
                    tipo = typeMovEnum,
                    remote_id = rem.id
                )
                local.insert(nuevo)
            } else {
                // Actualizar si es necesario (merge)
                val actualizado = localMatch.copy(
                    nome = rem.nome,
                    importe = rem.importe,
                    periodicidade = recurrenceEnum,
                    data_ini = rem.data_ini,
                    data_rnv = rem.data_rnv,
                    tipo = typeMovEnum
                )
                local.update(actualizado)
            }
        }
        return remoteMovRecur
    }

    // -------------------------------------------------------------------------
    // FASE 3: Eliminación local de items borrados en el servidor
    // -------------------------------------------------------------------------

    /**
     * Elimina localmente todos los movimientos recurrentes que ya no existen
     * en PocketBase.
     *
     * Para ello compara:
     * - IDs remotos actualmente en el servidor (`remoteMovRecur`)
     * - IDs remotos almacenados localmente
     *
     * Cualquier registro cuyo `remote_id` NO esté presente en el servidor
     * se considera eliminado y se borra de Room.
     *
     * @param remoteMovRecur Lista completa de registros válidos del servidor.
     */
    private suspend fun deleteStaleMovRecur(remoteMovRecur: List<MovRecurRecord>) {
        val remoteIds = remoteMovRecur.map { it.id }.toSet()
        val localWithRemote = local.getAllWithRemoteId()
        var deletedCount = 0

        for (movRecur in localWithRemote) {
            if (movRecur.remote_id !in remoteIds) {
                local.delete(movRecur)
                deletedCount++
            }
        }
    }
}