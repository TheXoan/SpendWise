package com.arcaneia.spendwise.apis.data.model

/**
 * Representa un **registro remoto de movimiento recurrente** tal como es devuelto por PocketBase.
 *
 * Esta clase modela exactamente la estructura de un ítem dentro de la colección remota
 * `mov_recur` en el servidor, y se utiliza para mapear las respuestas JSON recibidas por Retrofit.
 *
 * Cada instancia refleja el estado actual del movimiento recurrente almacenado en el backend,
 * incluyendo su periodicidad, fechas relacionadas y tipo de movimiento.
 *
 * ### Relación con la capa local
 * Este objeto es utilizado por el sincronizador (`MovRecurSyncRepository`) para:
 * - Crear nuevos registros locales si no existen.
 * - Actualizar datos locales cuando el servidor tiene cambios.
 * - Relacionar los movimientos recurrentes remotos con sus equivalentes locales mediante `remote_id`.
 *
 * ### Campos recibidos desde PocketBase
 * PocketBase almacena los enums (`Recurrence`, `TypeMov`) como **strings**, por lo que aquí se reciben
 * en formato textual y posteriormente se convierten en enums Kotlin en la capa de sincronización.
 *
 * @property id Identificador único del registro en PocketBase.
 * @property nome Nombre descriptivo del movimiento recurrente.
 * @property importe Importe económico que se genera en cada renovación.
 * @property periodicidade Tipo de recurrencia en formato String tal como se guarda en PocketBase.
 * @property data_ini Fecha de inicio en formato `YYYY-MM-DD`.
 * @property data_rnv Fecha programada para la próxima renovación (`YYYY-MM-DD`).
 * @property tipo Tipo de movimiento en formato String (`"INGRESO"` o `"GASTO"`).
 * @property user Identificador remoto del usuario propietario de este registro.
 */
data class MovRecurRecord(
    val id: String,
    val nome: String,
    val importe: Double,
    val periodicidade: String?, // PocketBase usa String para el enum Recurrence
    val data_ini: String,
    val data_rnv: String,
    val tipo: String?, // PocketBase usa String para el enum TypeMov
    val user: String
)