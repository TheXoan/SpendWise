package com.arcaneia.spendwise.apis.data.model

import android.content.Context
import com.arcaneia.spendwise.apis.RetrofitClient
import com.arcaneia.spendwise.data.datastore.TokenDataStore
import com.arcaneia.spendwise.data.entity.Categoria
import kotlinx.coroutines.flow.first

/**
 * Data source encargado de gestionar las operaciones remotas relacionadas con las categorías.
 *
 * Esta clase se comunica con la API usando [RetrofitClient] para obtener, crear, actualizar
 * y eliminar categorías asociadas a un usuario. Requiere un token válido almacenado en
 * [TokenDataStore] para autenticar las solicitudes.
 *
 * @property context Context necesario para acceder al almacenamiento del token.
 */
class CategoriaRemoteDataSource(private val context: Context) {

    /**
     * Obtiene el encabezado de autenticación necesario para las llamadas a la API.
     *
     * Recupera el token almacenado en [TokenDataStore] y construye el header de autorización
     * en formato `Bearer <token>`. Si no existe un token válido, se lanza una excepción.
     *
     * @return Cadena de texto con el header de autorización.
     * @throws IllegalArgumentException Si el token es nulo o está vacío.
     */
    private suspend fun authHeader(): String {
        val token = TokenDataStore.getToken(context).first()
        require(!token.isNullOrBlank()) { "No token available" }
        return "Bearer $token"
    }

    /**
     * Obtiene todas las categorías asociadas al usuario especificado.
     *
     * Realiza una solicitud GET a la API aplicando un filtro por `userId`.
     *
     * @param userId Identificador del usuario.
     * @return Lista de [CategoriaRecord] obtenidos desde la API.
     */
    suspend fun fetchAll(userId: String): List<CategoriaRecord> {
        val auth = authHeader()

        return RetrofitClient.api.getCategorias(
            auth = auth,
            filter = "user='$userId'"
        ).items
    }

    /**
     * Crea una nueva categoría asociada al usuario especificado.
     *
     * Envía los datos de la categoría a la API y devuelve el registro creado.
     *
     * @param userId Identificador del usuario propietario.
     * @param categoria Datos de la categoría a crear.
     * @return El registro creado como [CategoriaRecord].
     */
    suspend fun create(userId: String, categoria: Categoria): CategoriaRecord {
        val auth = authHeader()

        val body = mapOf(
            "nome" to categoria.nome,
            "tipo" to categoria.tipo,
            "user" to userId
        )

        return RetrofitClient.api.createCategoria(auth, body)
    }

    /**
     * Actualiza los datos de una categoría existente.
     *
     * Envía un cuerpo con los valores modificados al endpoint correspondiente.
     *
     * @param categoriaPBId Identificador de la categoría en PocketBase.
     * @param categoria Nuevos datos de la categoría.
     * @return El registro actualizado como [CategoriaRecord].
     */
    suspend fun update(categoriaPBId: String, categoria: Categoria): CategoriaRecord {
        val auth = authHeader()

        val body = mapOf(
            "nome" to categoria.nome,
            "tipo" to categoria.tipo
        )

        return RetrofitClient.api.updateCategoria(auth, categoriaPBId, body)
    }

    /**
     * Elimina la categoría asociada al identificador proporcionado.
     *
     * Envía una solicitud DELETE a la API.
     *
     * @param categoriaPBId Identificador de la categoría en PocketBase.
     */
    suspend fun delete(categoriaPBId: String) {
        val auth = authHeader()
        RetrofitClient.api.deleteCategoria(auth, categoriaPBId)
    }
}