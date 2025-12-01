package com.arcaneia.spendwise.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.arcaneia.spendwise.data.model.TypeMov

/**
 * Entidad que representa un movimiento económico dentro de la aplicación.
 *
 * Los movimientos pueden ser ingresos o gastos, y están asociados a una categoría y,
 * opcionalmente, a un movimiento recurrente.
 *
 * Esta entidad define dos claves foráneas:
 *
 * 1. **categoria_id** → Referencia a [Categoria], con eliminación en cascada
 *    (si se borra la categoría, se borran sus movimientos).
 *
 * 2. **mov_recur_id** → Referencia a [MovRecur], con acción `SET_NULL`
 *    (si se elimina el movimiento recurrente, el campo queda en `null`).
 *
 * Además, la entidad define **índices** sobre las columnas `categoria_id` y `mov_recur_id`.
 * Room recomienda indexar todas las columnas usadas como claves foráneas porque:
 *
 * - Aceleran las operaciones `JOIN` que relacionan `mov` con `categoria` o `mov_recur`.
 * - Mejoran la velocidad de validación de integridad referencial en inserciones y actualizaciones.
 * - Optimizan consultas que filtran por estas columnas.
 *
 * La tabla asociada se llama `mov`.
 *
 * @property id Identificador único del movimiento.
 * Se genera automáticamente mediante `autoGenerate = true`.
 *
 * @property tipo Tipo de movimiento, representado por el enum [TypeMov]
 * (por ejemplo: *INGRESO* o *GASTO*). Puede ser `null` en casos especiales.
 *
 * @property importe Monto económico del movimiento.
 *
 * @property data_mov Fecha del movimiento en formato `"YYYY-MM-DD"`.
 *
 * @property descricion Descripción opcional del movimiento.
 *
 * @property categoria_id ID de la categoría asociada al movimiento.
 * También es una clave foránea con índice para mejorar rendimiento.
 *
 * @property mov_recur_id ID de un movimiento recurrente, si aplica.
 * Puede ser `null` si el movimiento no proviene de una recurrencia.
 * También está indexado para optimizar consultas y validaciones.
 */

@Entity(
    tableName = "mov",
    foreignKeys = [
        ForeignKey(
            entity = Categoria::class,
            parentColumns = ["id"],
            childColumns = ["categoria_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MovRecur::class,
            parentColumns = ["id"],
            childColumns = ["mov_recur_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index(
            value = ["categoria_id"]
        ),
        Index(value = ["mov_recur_id"])
    ]
)
data class Mov(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tipo: TypeMov?,
    val importe: Double,
    val data_mov: String,
    val descricion: String? = null,
    val categoria_id: Int,
    val mov_recur_id: Int? = null
)