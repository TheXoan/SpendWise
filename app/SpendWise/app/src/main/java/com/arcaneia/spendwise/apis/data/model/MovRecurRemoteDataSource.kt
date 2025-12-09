package com.arcaneia.spendwise.apis.data.model

import android.content.Context
import com.arcaneia.spendwise.apis.RetrofitClient
import com.arcaneia.spendwise.data.datastore.TokenDataStore
import com.arcaneia.spendwise.data.entity.MovRecur
import kotlinx.coroutines.flow.first

/**
 * Fuente de datos remota encargada de gestionar todas las operaciones
 * relacionadas con los **movimientos recurrentes** (`mov_recur`) en el
 * servidor PocketBase.
 *
 * Esta clase actúa como intermediaria entre la capa de sincronización/repositorio
 * y las llamadas HTTP realizadas mediante Retrofit, proporcionando funciones para:
 *
 * - Obtener todos los movimientos recurrentes remotos del usuario.
 * - Crear nuevos movimientos recurrentes en el servidor.
 * - Actualizar registros existentes.
 * - Eliminar movimientos recurrentes remotos.
 *
 * Internamente maneja la autenticación utilizando el token almacenado en
 * `TokenDataStore`, construyendo el encabezado `Bearer <token>` requerido
 * por PocketBase para todas las peticiones protegidas.
 *
 * ### Uso
 * Esta clase es utilizada típicamente por:
 * - `MovRecurSyncRepository`: para sincronización remota ↔ local.
 * - ViewModels que requieran crear, editar o borrar movimientos recurrentes directamente.
 *
 * @property context Contexto necesario para acceder al `TokenDataStore`.
 */
class MovRecurRemoteDataSource(private val context: Context) {

    /**
     * Genera el encabezado de autenticación para las peticiones HTTP.
     *
     * Obtiene el token almacenado en `TokenDataStore` y lo devuelve en formato
     * `"Bearer <token>"`, obligatorio para acceder a las colecciones protegidas
     * de PocketBase.
     *
     * @return Cadena de autenticación para usar en la API.
     * @throws IllegalArgumentException Si no existe un token almacenado.
     */
    private suspend fun authHeader(): String {
        val token = TokenDataStore.getToken(context).first()
        require(!token.isNullOrBlank()) { "No token available" }
        return "Bearer $token"
    }

    /**
     * Descarga todos los movimientos recurrentes pertenecientes al usuario dado.
     *
     * Realiza una consulta filtrada mediante el campo `user` en PocketBase.
     *
     * @param userId Identificador remoto del usuario propietario.
     * @return Lista de registros remotos [MovRecurRecord].
     */
    suspend fun fetchAll(userId: String): List<MovRecurRecord> {
        val auth = authHeader()

        return RetrofitClient.api.getMovRecur(
            auth = auth,
            filter = "user='$userId'"
        ).items
    }

    /**
     * Crea un nuevo movimiento recurrente en el servidor PocketBase.
     *
     * Mapea los campos del modelo local [MovRecur] al formato requerido
     * por el backend, incluyendo:
     * - Conversión de enums (`Recurrence`, `TypeMov`) a String.
     * - Inclusión del ID del usuario propietario.
     *
     * @param userId ID remoto del usuario propietario del registro.
     * @param movRecur Entidad local que se desea subir.
     * @return El registro creado por el servidor.
     */
    suspend fun create(
        userId: String,
        movRecur: MovRecur
    ): MovRecurRecord {
        val auth = authHeader()

        val body = mapOf(
            "nome" to movRecur.nome,
            "importe" to movRecur.importe,
            "periodicidade" to movRecur.periodicidade?.name,
            "data_ini" to movRecur.data_ini,
            "data_rnv" to movRecur.data_rnv,
            "tipo" to movRecur.tipo?.name,
            "user" to userId
        ).filterValues { it != null }

        return RetrofitClient.api.createMovRecur(auth, body)
    }

    /**
     * Actualiza un movimiento recurrente existente en PocketBase.
     *
     * Solo se envían los campos que pueden ser modificados, respetando la
     * estructura aceptada por la API REST.
     *
     * @param movRecurPBId ID remoto del movimiento recurrente que se actualizará.
     * @param movRecur Entidad local con los cambios aplicados.
     * @return El registro actualizado devuelto por el servidor.
     */
    suspend fun update(
        movRecurPBId: String,
        movRecur: MovRecur
    ): MovRecurRecord {
        val auth = authHeader()

        val body = mapOf(
            "nome" to movRecur.nome,
            "importe" to movRecur.importe,
            "periodicidade" to movRecur.periodicidade?.name,
            "data_ini" to movRecur.data_ini,
            "data_rnv" to movRecur.data_rnv,
            "tipo" to movRecur.tipo?.name
        ).filterValues { it != null }

        return RetrofitClient.api.updateMovRecur(auth, movRecurPBId, body)
    }

    /**
     * Elimina un movimiento recurrente remoto identificado por su ID.
     *
     * @param movRecurPBId ID remoto del registro a eliminar.
     */
    suspend fun delete(movRecurPBId: String) {
        val auth = authHeader()
        RetrofitClient.api.deleteMovRecur(auth, movRecurPBId)
    }
}