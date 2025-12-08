package com.arcaneia.spendwise.apis

import com.arcaneia.spendwise.apis.data.model.CategoriaRecord
import com.arcaneia.spendwise.apis.data.model.MovRecord
import com.arcaneia.spendwise.apis.data.model.PocketBaseListResponse
import com.arcaneia.spendwise.data.model.AuthRequest
import com.arcaneia.spendwise.data.model.AuthResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interfaz que define los endpoints utilizados para comunicarse con PocketBase.
 *
 * Esta API permite autenticar usuarios y realizar operaciones CRUD sobre la colección
 * de categorías. Cada llamada requiere un token válido en el header `Authorization`
 * cuando así se indica.
 */
interface PocketBaseApi {

    /**
     * Autentica a un usuario usando email y contraseña.
     *
     * @param request Contiene las credenciales necesarias para la autenticación.
     * @return Respuesta con el token y datos del usuario autenticado.
     */
    @POST("api/collections/users/auth-with-password")
    suspend fun login(@Body request: AuthRequest): AuthResponse

    /**
     * Obtiene la lista de categorías desde PocketBase.
     *
     * @param auth Header de autorización en formato `Bearer <token>`.
     * @param filter Filtro opcional aplicado sobre los registros en el servidor.
     * @return Respuesta paginada con los registros de categorías.
     */
    @GET("api/collections/categoria/records")
    suspend fun getCategorias(
        @Header("Authorization") auth: String,
        @Query("filter") filter: String? = null
    ): PocketBaseListResponse<CategoriaRecord>

    /**
     * Crea una nueva categoría en PocketBase.
     *
     * @param auth Header de autorización en formato `Bearer <token>`.
     * @param body Mapa con los datos necesarios para crear la categoría.
     * @return El registro creado como [CategoriaRecord].
     */
    @POST("api/collections/categoria/records")
    suspend fun createCategoria(
        @Header("Authorization") auth: String,
        @Body body: Map<String, @JvmSuppressWildcards Any?>
    ): CategoriaRecord

    /**
     * Actualiza una categoría existente en PocketBase.
     *
     * @param auth Header de autorización en formato `Bearer <token>`.
     * @param id Identificador del registro remoto a actualizar.
     * @param body Mapa con los cambios a aplicar sobre la categoría.
     * @return El registro actualizado como [CategoriaRecord].
     */
    @PATCH("api/collections/categoria/records/{id}")
    suspend fun updateCategoria(
        @Header("Authorization") auth: String,
        @Path("id") id: String,
        @Body body: Map<String, @JvmSuppressWildcards Any?>
    ): CategoriaRecord

    /**
     * Elimina una categoría en PocketBase.
     *
     * @param auth Header de autorización en formato `Bearer <token>`.
     * @param id Identificador del registro remoto a eliminar.
     */
    @DELETE("api/collections/categoria/records/{id}")
    suspend fun deleteCategoria(
        @Header("Authorization") auth: String,
        @Path("id") id: String
    )

    @GET("api/collections/mov/records")
    suspend fun getMov(
        @Header("Authorization") auth: String,
        @Query("filter") filter: String? = null
    ): PocketBaseListResponse<MovRecord>

    @POST("api/collections/mov/records")
    suspend fun createMov(
        @Header("Authorization") auth: String,
        @Body body: Map<String, @JvmSuppressWildcards Any?>
    ): MovRecord

    @PATCH("api/collections/mov/records/{id}")
    suspend fun updateMov(
        @Header("Authorization") auth: String,
        @Path("id") id: String,
        @Body body: Map<String, @JvmSuppressWildcards Any?>
    ): MovRecord

    @DELETE("api/collections/mov/records/{id}")
    suspend fun deleteMov(
        @Header("Authorization") auth: String,
        @Path("id") id: String
    )
}