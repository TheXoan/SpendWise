/**
 * Representa un registro remoto de la colección `mov` en PocketBase.
 *
 * Esta clase actúa como un **DTO (Data Transfer Object)** utilizado para mapear
 * las respuestas JSON provenientes del servidor. Cada instancia corresponde a
 * un movimiento simple almacenado en PocketBase, incluyendo información básica,
 * referencias a entidades relacionadas y metadatos útiles para sincronización.
 *
 * ---
 *
 * ## 🔗 Relaciones y sincronización
 *
 * PocketBase almacena relaciones mediante **IDs remotos (String)**.
 * Por ello, los campos `categoria_id` y `mov_recur_id` deben ser traducidos
 * posteriormente por el `MovSyncRepository` a los IDs internos de Room.
 *
 * Además, este DTO incluye el campo `renew_hash`, un identificador único
 * generado por las renovaciones automáticas, que permite:
 * - Detectar duplicados en sincronizaciones entre dispositivos.
 * - Evitar que un mismo movimiento recurrente genere múltiples copias.
 *
 * ---
 *
 * ## Propiedades
 *
 * @property id
 * ID único generado por PocketBase para este movimiento.
 *
 * @property tipo
 * Tipo de movimiento (`INGRESO` o `GASTO`) como String. Puede ser nulo si
 * en el servidor no se estableció el campo.
 *
 * @property importe
 * Cantidad económica asociada al movimiento.
 *
 * @property data_mov
 * Fecha del movimiento en formato `"YYYY-MM-DD HH:mm:ss"` o `"YYYY-MM-DD"`
 * según el origen del dato.
 *
 * @property descricion
 * Texto descriptivo del movimiento. Puede ser nulo.
 *
 * @property categoria_id
 * ID remoto de la categoría asociada. Debe mapearse al ID local en Room.
 *
 * @property mov_recur_id
 * ID remoto de la entrada `mov_recur` que generó este movimiento.
 * Es nulo si el movimiento no proviene de una recurrencia.
 *
 * @property user
 * ID remoto del usuario propietario del registro.
 *
 * @property renew_hash
 * Identificador único que permite detectar movimientos creados automáticamente
 * por renovaciones recurrentes y evitar duplicados en la sincronización.
 */
data class MovRecord(
    val id: String,
    val tipo: String?,
    val importe: Double,
    val data_mov: String,
    val descricion: String? = null,
    val categoria_id: String,
    val mov_recur_id: String? = null,
    val user: String,
    val renew_hash: String?
)