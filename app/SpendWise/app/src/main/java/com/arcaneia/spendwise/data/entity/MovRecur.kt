package com.arcaneia.spendwise.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arcaneia.spendwise.data.model.Recurrence
import com.arcaneia.spendwise.data.model.TypeMov

/**
 * Entidad que representa un **movimiento recurrente** dentro de la aplicación (Room).
 *
 * Los movimientos recurrentes permiten programar transacciones automáticas en
 * intervalos definidos (mensual, anual, semanal, etc.).
 * Cada vez que un movimiento recurrente llega a su fecha de renovación (`data_rnv`),
 * la aplicación puede generar automáticamente un movimiento normal asociado.
 *
 * La tabla asociada se llama `mov_recur`.
 *
 * @property id Identificador único del movimiento recurrente.
 * Se genera automáticamente mediante `autoGenerate = true`.
 *
 * @property nome Nombre descriptivo del movimiento recurrente
 * (ej.: “Suscripción Netflix”, “Salario mensual”).
 *
 * @property importe Monto económico que se renovará periódicamente.
 *
 * @property periodicidade Tipo de recurrencia, representado por el enum [Recurrence]
 * ("MENSUAL", "ANUAL", "SEMANAL", etc.). Puede ser `null` en algunos casos especiales.
 *
 * @property data_ini Fecha de inicio de la recurrencia, en formato `"YYYY-MM-DD"`.
 * Esta fecha marca el punto de partida para el cálculo de futuras renovaciones.
 *
 * @property data_rnv Fecha de la próxima renovación del movimiento, en formato `"YYYY-MM-DD"`.
 * Esta fecha es actualizada por el sistema tras cada generación de movimiento.
 *
 * @property tipo Tipo de movimiento recurrente, representado por el enum [TypeMov]
 * (por ejemplo: *INGRESO* o *GASTO*). Puede ser `null` en casos excepcionales.
 *
 * @property remote_id Identificador remoto (PocketBase ID) del registro.
 * Es `null` si el movimiento recurrente aún no ha sido sincronizado con el servidor.
 */
@Entity(tableName = "mov_recur")
data class MovRecur(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val importe: Double,
    val periodicidade: Recurrence?, // "MENSUAL", "ANUAL", "SEMANAL"
    val data_ini: String,
    val data_rnv: String,
    val tipo: TypeMov?, // INGRESO, GASTO
    val remote_id: String? = null
)