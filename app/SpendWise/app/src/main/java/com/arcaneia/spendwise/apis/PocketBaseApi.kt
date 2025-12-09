package com.arcaneia.spendwise.apis

import com.arcaneia.spendwise.apis.data.model.CategoriaRecord
import com.arcaneia.spendwise.apis.data.model.MovRecord
import com.arcaneia.spendwise.apis.data.model.MovRecurRecord // <-- NUEVO IMPORT
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
 * Interfaz Retrofit que define todos los endpoints utilizados para comunicarse con PocketBase.
 *
 * Esta API proporciona:
 *
 * - **Autenticación:** login mediante email y contraseña.
 * - **Gestión de categorías:** CRUD completo sobre la colección `categoria`.
 * - **Gestión de movimientos simples:** CRUD sobre la colección `mov`.
 * - **Gestión de movimientos recurrentes:** CRUD sobre la colección `mov_recur`.
 *
 * Cada función corresponde a un endpoint REST específico y requiere un token válido
 * enviado en la cabecera `Authorization` cuando se trabaja con recursos protegidos.
 *
 * Las funciones devuelven objetos modelo mapeados desde PocketBase:
 * - [CategoriaRecord]
 * - [MovRecord]
 * - [MovRecurRecord]
 *
 * Todas las respuestas listadas se encapsulan en [PocketBaseListResponse], el formato
 * estándar de paginación utilizado por PocketBase.
 *
 * Esta interfaz es utilizada por los data sources remotos para implementar la
 * sincronización con la base de datos local.
 */
interface PocketBaseApi {

    // --- Endpoints de Autenticación ---

    /**
     * Autentica un usuario mediante email y contraseña.
     *
     * Este endpoint utiliza la colección `users` de PocketBase y devuelve
     * un token de autenticación junto con la información del usuario.
     *
     * @param request Objeto que contiene `email` y `password`.
     * @return [AuthResponse] que incluye el token JWT y los datos del usuario.
     */
    @POST("api/collections/users/auth-with-password")
    suspend fun login(@Body request: AuthRequest): AuthResponse

    // --- Endpoints de Categoría (categoria) ---

    /**
     * Obtiene una lista de categorías desde PocketBase.
     *
     * @param auth Token de autenticación en formato `"Bearer <token>"`.
     * @param filter Filtro opcional escrito en sintaxis de PocketBase.
     * @return Un objeto paginado con registros [CategoriaRecord].
     */
    @GET("api/collections/categoria/records")
    suspend fun getCategorias(
        @Header("Authorization") auth: String,
        @Query("filter") filter: String? = null
    ): PocketBaseListResponse<CategoriaRecord>

    /**
     * Crea una nueva categoría en el servidor.
     *
     * @param auth Token de autenticación.
     * @param body Datos de la categoría como un mapa llave/valor.
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
     * @param auth Token de autenticación.
     * @param id Identificador remoto de la categoría.
     * @param body Mapa con los campos a modificar.
     * @return La categoría actualizada como [CategoriaRecord].
     */
    @PATCH("api/collections/categoria/records/{id}")
    suspend fun updateCategoria(
        @Header("Authorization") auth: String,
        @Path("id") id: String,
        @Body body: Map<String, @JvmSuppressWildcards Any?>
    ): CategoriaRecord

    /**
     * Elimina una categoría remota.
     *
     * @param auth Token de autenticación.
     * @param id ID remoto de la categoría a borrar.
     */
    @DELETE("api/collections/categoria/records/{id}")
    suspend fun deleteCategoria(
        @Header("Authorization") auth: String,
        @Path("id") id: String
    )

    // --- Endpoints de Movimiento Simple (mov) ---

    /**
     * Obtiene todos los movimientos simples desde PocketBase.
     *
     * @param auth Token de autenticación.
     * @param filter Filtro opcional.
     * @return Lista paginada de [MovRecord].
     */
    @GET("api/collections/mov/records")
    suspend fun getMov(
        @Header("Authorization") auth: String,
        @Query("filter") filter: String? = null
    ): PocketBaseListResponse<MovRecord>

    /**
     * Crea un nuevo movimiento simple.
     *
     * @param auth Token de autenticación.
     * @param body Mapa con los datos del movimiento.
     * @return Registro creado como [MovRecord].
     */
    @POST("api/collections/mov/records")
    suspend fun createMov(
        @Header("Authorization") auth: String,
        @Body body: Map<String, @JvmSuppressWildcards Any?>
    ): MovRecord

    /**
     * Actualiza un movimiento simple existente.
     *
     * @param auth Token de autenticación.
     * @param id ID remoto del movimiento.
     * @param body Campos modificados.
     * @return Movimiento actualizado como [MovRecord].
     */
    @PATCH("api/collections/mov/records/{id}")
    suspend fun updateMov(
        @Header("Authorization") auth: String,
        @Path("id") id: String,
        @Body body: Map<String, @JvmSuppressWildcards Any?>
    ): MovRecord

    /**
     * Elimina un movimiento simple remoto.
     *
     * @param auth Token de autenticación.
     * @param id Identificador remoto del movimiento.
     */
    @DELETE("api/collections/mov/records/{id}")
    suspend fun deleteMov(
        @Header("Authorization") auth: String,
        @Path("id") id: String
    )

    // --- Endpoints de Movimiento Recurrente (mov_recur) ---

    /**
     * Obtiene la lista de movimientos recurrentes desde PocketBase.
     *
     * @param auth Token de autenticación en formato Bearer.
     * @param filter Filtro de servidor opcional.
     * @return Respuesta paginada con registros [MovRecurRecord].
     */
    @GET("api/collections/mov_recur/records")
    suspend fun getMovRecur(
        @Header("Authorization") auth: String,
        @Query("filter") filter: String? = null
    ): PocketBaseListResponse<MovRecurRecord>

    /**
     * Crea un nuevo movimiento recurrente en PocketBase.
     *
     * @param auth Token de autenticación.
     * @param body Datos del movimiento recurrente como mapa.
     * @return Registro creado como [MovRecurRecord].
     */
    @POST("api/collections/mov_recur/records")
    suspend fun createMovRecur(
        @Header("Authorization") auth: String,
        @Body body: Map<String, @JvmSuppressWildcards Any?>
    ): MovRecurRecord

    /**
     * Actualiza un movimiento recurrente existente.
     *
     * @param auth Token de autenticación.
     * @param id ID remoto del movimiento recurrente.
     * @param body Campos a modificar.
     * @return El registro actualizado como [MovRecurRecord].
     */
    @PATCH("api/collections/mov_recur/records/{id}")
    suspend fun updateMovRecur(
        @Header("Authorization") auth: String,
        @Path("id") id: String,
        @Body body: Map<String, @JvmSuppressWildcards Any?>
    ): MovRecurRecord

    /**
     * Elimina un movimiento recurrente de PocketBase.
     *
     * @param auth Token de autenticación.
     * @param id ID remoto del registro a eliminar.
     */
    @DELETE("api/collections/mov_recur/records/{id}")
    suspend fun deleteMovRecur(
        @Header("Authorization") auth: String,
        @Path("id") id: String
    )
}