package com.arcaneia.spendwise.apis.data.model

/**
 * Representa un registro de movimiento tal como es devuelto por el servidor PocketBase.
 * Este DTO (Data Transfer Object) se utiliza para deserializar las respuestas JSON de la API.
 *
 * NOTA: Los campos `categoria_id` y `mov_recur_id` contendrán los **IDs remotos (strings)**
 * de las colecciones relacionadas en PocketBase. Es responsabilidad del SyncRepository mapear
 * estos IDs remotos a los IDs locales de Room.
 *
 * @property id Identificador único del registro en el servidor PocketBase.
 * @property tipo Tipo de movimiento (INGRESO/GASTO). Es un String y puede ser nulo si no está definido en el servidor.
 * @property importe Monto económico de la transacción.
 * @property data_mov Fecha en la que ocurrió el movimiento, típicamente en formato YYYY-MM-DD.
 * @property descricion Descripción opcional del movimiento.
 * @property categoria_id ID remoto de la categoría a la que pertenece este movimiento.
 * @property mov_recur_id ID remoto del movimiento recurrente que originó este registro (puede ser nulo si no es recurrente).
 * @property user Identificador del usuario propietario de este registro en PocketBase.
 */
data class MovRecord(
    val id: String,
    val tipo: String?, // En PocketBase, los enums suelen serializarse como Strings
    val importe: Double,
    val data_mov: String,
    val descricion: String? = null,
    val categoria_id: String, // ID remoto de Categoria
    val mov_recur_id: String? = null, // ID remoto de MovRecur
    val user: String
)