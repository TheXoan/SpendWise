package com.arcaneia.spendwise.apis.data.model

import MovRecord
import android.content.Context
import com.arcaneia.spendwise.apis.RetrofitClient
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.datastore.TokenDataStore
import kotlinx.coroutines.flow.first

/**
 * Fuente de datos remota encargada de gestionar la comunicación entre la aplicación
 * y la colección `mov` de PocketBase.
 *
 * Esta clase encapsula todas las operaciones CRUD remotas relacionadas con movimientos
 * simples, proporcionando un acceso seguro, tipado y centralizado a las llamadas HTTP.
 * Utiliza Retrofit para interactuar con la API y DataStore para obtener el token de
 * autenticación almacenado en el dispositivo.
 *
 * ---
 *
 * ## 🔐 Autenticación
 * Cada operación realiza una llamada al método privado [authHeader], el cual:
 * - Recupera el token actual desde [TokenDataStore].
 * - Lanza una excepción si el token no existe (evitando llamadas inválidas al servidor).
 * - Devuelve el header `Bearer <token>` requerido por PocketBase.
 *
 * ---
 *
 * ## 🔄 Sincronización y compatibilidad
 * Esta fuente de datos es totalmente compatible con:
 * - **IDs remotos** (`categoriaPBId`, `movRecurPBId`), necesarios para el mapeo relacional.
 * - **renew_hash**, usado para evitar duplicados cuando un movimiento se genera desde
 *   una renovación recurrente.
 *
 * Las operaciones remotas se limitan a enviar y recibir datos; el mapeo hacia Room y
 * el merge final se realiza en `MovSyncRepository`.
 *
 * ---
 *
 * ## Métodos principales
 *
 * ### fetchAll(userId)
 * Obtiene todos los movimientos del usuario autenticado mediante un filtro en PocketBase.
 *
 * ### create(...)
 * Envía un nuevo movimiento al servidor.
 * Admite valores opcionales como `descricion` o `mov_recur_id`.
 *
 * ### update(...)
 * Actualiza un movimiento existente en PocketBase utilizando su ID remoto.
 *
 * ### delete(movPBId)
 * Elimina un movimiento remoto de forma definitiva.
 *
 * ---
 *
 * @property context Contexto necesario para acceder a DataStore y recursos del sistema.
 */
class MovRemoteDataSource(private val context: Context) {

    /**
     * Construye el header de autenticación `Bearer <token>` requerido por PocketBase.
     *
     * Recupera el token de DataStore y lanza una excepción si está vacío.
     *
     * @return Cadena con el header de autorización.
     * @throws IllegalArgumentException si no hay token disponible.
     */
    private suspend fun authHeader(): String {
        val token = TokenDataStore.getToken(context).first()
        require(!token.isNullOrBlank()) { "No token available" }
        return "Bearer $token"
    }

    /**
     * Obtiene todos los movimientos remotos pertenecientes al usuario especificado.
     *
     * Esta llamada aplica un filtro en el servidor:
     * ```
     * user='<userId>'
     * ```
     *
     * @param userId ID remoto del usuario autenticado.
     * @return Lista de [MovRecord] correspondientes al usuario.
     */
    suspend fun fetchAll(userId: String): List<MovRecord> {
        val auth = authHeader()

        return RetrofitClient.api.getMov(
            auth = auth,
            filter = "user='$userId'"
        ).items
    }

    /**
     * Crea un nuevo movimiento remoto en PocketBase.
     *
     * El cuerpo enviado incluye:
     *  - tipo, importe, fecha y categoría.
     *  - mov_recur_id (si proviene de una renovación).
     *  - renew_hash para evitar duplicados.
     *
     * @param userId ID del usuario propietario.
     * @param mov Objeto [Mov] local a sincronizar.
     * @param categoriaPBId ID remoto de la categoría asociada.
     * @param movRecurPBId ID remoto del movimiento recurrente (si lo hubiera).
     *
     * @return El movimiento remoto creado como [MovRecord].
     */
    suspend fun create(
        userId: String,
        mov: Mov,
        categoriaPBId: String,
        movRecurPBId: String?
    ): MovRecord {
        val auth = authHeader()

        val body = mapOf(
            "tipo" to mov.tipo!!.name,
            "importe" to mov.importe,
            "data_mov" to mov.data_mov,
            "descricion" to mov.descricion,
            "categoria_id" to categoriaPBId,
            "mov_recur_id" to movRecurPBId,
            "renew_hash" to mov.renew_hash,
            "user" to userId
        ).filterValues { it != null }

        return RetrofitClient.api.createMov(auth, body)
    }

    /**
     * Actualiza un movimiento ya existente en PocketBase.
     *
     * Solo se envían los campos necesarios para mantener consistencia con el modelo local.
     *
     * @param movPBId ID remoto del movimiento a actualizar.
     * @param mov Objeto [Mov] local con los valores actualizados.
     * @param categoriaPBId ID remoto de la categoría asociada.
     * @param movRecurPBId ID remoto del movimiento recurrente (si corresponde).
     *
     * @return Registro actualizado como [MovRecord].
     */
    suspend fun update(
        movPBId: String,
        mov: Mov,
        categoriaPBId: String,
        movRecurPBId: String?
    ): MovRecord {
        val auth = authHeader()

        val body = mapOf(
            "tipo" to mov.tipo!!.name,
            "importe" to mov.importe,
            "data_mov" to mov.data_mov,
            "descricion" to mov.descricion,
            "categoria_id" to categoriaPBId,
            "mov_recur_id" to movRecurPBId,
            "renew_hash" to mov.renew_hash
        ).filterValues { it != null }

        return RetrofitClient.api.updateMov(auth, movPBId, body)
    }

    /**
     * Elimina un movimiento remoto en PocketBase.
     *
     * @param movPBId ID remoto del movimiento que se desea eliminar.
     */
    suspend fun delete(movPBId: String) {
        val auth = authHeader()
        RetrofitClient.api.deleteMov(auth, movPBId)
    }
}
