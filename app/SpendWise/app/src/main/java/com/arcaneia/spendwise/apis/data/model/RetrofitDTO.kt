package com.arcaneia.spendwise.apis.data.model

/**
 * Representa un registro de categoría tal como es devuelto por el servidor PocketBase.
 *
 * @property id Identificador único del registro remoto.
 * @property nome Nombre de la categoría.
 * @property tipo Tipo o clasificación de la categoría.
 * @property user Identificador del usuario propietario de la categoría.
 */
data class CategoriaRecord(
    val id: String,
    val nome: String,
    val tipo: String,
    val user: String
)

/**
 * Representa una respuesta paginada estándar de PocketBase.
 *
 * Esta estructura se usa para envolver listas de elementos obtenidos desde la API,
 * proporcionando información de paginación junto con los datos retornados.
 *
 * @param T Tipo de elementos contenidos en la lista.
 * @property page Número de página actual.
 * @property perPage Cantidad de elementos por página.
 * @property totalItems Número total de elementos disponibles en el servidor.
 * @property items Lista de elementos de la página actual.
 */
data class PocketBaseListResponse<T>(
    val page: Int,
    val perPage: Int,
    val totalItems: Int,
    val items: List<T>
)
