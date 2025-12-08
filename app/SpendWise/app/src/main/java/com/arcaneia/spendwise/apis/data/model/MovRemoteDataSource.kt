package com.arcaneia.spendwise.apis.data.model

import android.content.Context
import com.arcaneia.spendwise.apis.RetrofitClient
import com.arcaneia.spendwise.data.datastore.TokenDataStore
import com.arcaneia.spendwise.data.entity.Mov
import kotlinx.coroutines.flow.first

/**
 * Data source encargado de gestionar las operaciones remotas relacionadas con los
 * movimientos simples (`mov`) en el servidor PocketBase.
 *
 * Esta clase actúa como la capa de acceso a la API REST para la colección de movimientos,
 * manejando la autenticación mediante tokens y traduciendo los datos locales ([Mov]) a
 * los formatos requeridos por el servidor.
 *
 * Requiere el ID remoto (PocketBase ID) de la categoría asociada para crear/actualizar
 * registros debido a las relaciones de base de datos en el backend.
 *
 * @property context El contexto de la aplicación, necesario para acceder al [TokenDataStore].
 */
class MovRemoteDataSource(private val context: Context) {

    /**
     * Reutilizamos la función de autenticación ya existente. Obtiene el token de autenticación
     * del DataStore y lo formatea como un encabezado Bearer.
     *
     * @return El encabezado de autorización como String ("Bearer <token>").
     * @throws IllegalArgumentException si no hay un token disponible.
     */
    private suspend fun authHeader(): String {
        val token = TokenDataStore.getToken(context).first()
        require(!token.isNullOrBlank()) { "No token available" }
        return "Bearer $token"
    }

    /**
     * Obtiene todos los movimientos asociados al usuario especificado mediante una consulta
     * filtrada por el ID del usuario.
     *
     * @param userId El identificador remoto del usuario actual.
     * @return Una lista de registros de movimientos [MovRecord] obtenidos del servidor.
     */
    suspend fun fetchAll(userId: String): List<MovRecord> {
        val auth = authHeader()

        return RetrofitClient.api.getMov(
            auth = auth,
            filter = "user='$userId'"
        ).items
    }

    /**
     * Crea un nuevo movimiento en el servidor PocketBase.
     *
     * Este método mapea el objeto [Mov] local a un mapa de campos de PocketBase,
     * incluyendo los IDs remotos de las relaciones (`categoriaPBId` y `movRecurPBId`).
     *
     * @param userId El ID remoto del usuario propietario.
     * @param mov El objeto [Mov] local a subir.
     * @param categoriaPBId ID remoto (PocketBase ID) de la categoría a la que se asocia el movimiento.
     * @param movRecurPBId ID remoto (PocketBase ID) del movimiento recurrente, si aplica (opcional).
     * @return El [MovRecord] creado devuelto por el servidor.
     */
    suspend fun create(
        userId: String,
        mov: Mov,
        categoriaPBId: String,
        movRecurPBId: String? = null
    ): MovRecord {
        val auth = authHeader()

        // PocketBase espera los IDs de las relaciones, no los IDs locales de Room.
        val body = mapOf(
            "tipo" to mov.tipo?.name, // Usamos .name para obtener el String del enum TypeMov
            "importe" to mov.importe,
            "data_mov" to mov.data_mov,
            "descricion" to mov.descricion,
            "categoria_id" to categoriaPBId,
            "mov_recur_id" to movRecurPBId,
            "user" to userId
        ).filterValues { it != null } // Eliminamos campos nulos (como mov_recur) si no se proporcionan

        return RetrofitClient.api.createMov(auth, body)
    }

    /**
     * Actualiza un movimiento existente en el servidor.
     *
     * @param movPBId El ID remoto (PocketBase ID) del movimiento a actualizar.
     * @param mov El objeto [Mov] local con los nuevos valores.
     * @param categoriaPBId ID remoto de la nueva categoría asociada (o la misma si no cambia).
     * @param movRecurPBId ID remoto del movimiento recurrente (opcional).
     * @return El [MovRecord] actualizado devuelto por el servidor.
     */
    suspend fun update(
        movPBId: String,
        mov: Mov,
        categoriaPBId: String,
        movRecurPBId: String? = null
    ): MovRecord {
        val auth = authHeader()

        val body = mapOf(
            "tipo" to mov.tipo?.name,
            "importe" to mov.importe,
            "data_mov" to mov.data_mov,
            "descricion" to mov.descricion,
            "categoria_id" to categoriaPBId,
            "mov_recur_id" to movRecurPBId
        ).filterValues { it != null }

        return RetrofitClient.api.updateMov(auth, movPBId, body)
    }

    /**
     * Elimina el movimiento asociado al identificador remoto proporcionado.
     *
     * @param movPBId El ID remoto (PocketBase ID) del movimiento a eliminar.
     */
    suspend fun delete(movPBId: String) {
        val auth = authHeader()
        RetrofitClient.api.deleteMov(auth, movPBId)
    }
}