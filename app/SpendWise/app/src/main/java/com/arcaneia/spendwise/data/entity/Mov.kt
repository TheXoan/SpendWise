package com.arcaneia.spendwise.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.arcaneia.spendwise.data.model.TypeMov

/**
 * Entidad que representa un movimiento económico individual dentro de la aplicación.
 *
 * Un **movimiento** puede ser un ingreso o un gasto, estar asociado a una categoría
 * y opcionalmente provenir de un movimiento recurrente.
 *
 * Esta entidad integra tanto datos locales como referencias para sincronización remota
 * con PocketBase.
 *
 * ---
 * ### 🔗 Relaciones con otras entidades
 *
 * Se definen dos claves foráneas:
 *
 * 1. **`categoria_id`** → referencia a [Categoria]
 *    - `CASCADE`: si se elimina una categoría, también se eliminan sus movimientos.
 *
 * 2. **`mov_recur_id`** → referencia a [MovRecur]
 *    - `SET_NULL`: si se elimina el movimiento recurrente, el movimiento simple permanece,
 *      pero deja de estar vinculado a esa recurrencia.
 *
 * ---
 * ### ⚡ Índices
 * La entidad define índices en:
 * - `categoria_id`
 * - `mov_recur_id`
 *
 * Esto optimiza:
 * - consultas con JOIN,
 * - filtros por categoría o recurrencia,
 * - validación de claves foráneas.
 *
 * ---
 * ### 🌐 Sincronización remota (PocketBase)
 *
 * Los campos:
 * - `remote_id`
 * - `renew_hash`
 * - `notificado`
 *
 * permiten:
 * - identificar el registro remoto asociado,
 * - evitar duplicados generados por renovaciones,
 * - controlar qué movimientos deben generar notificaciones locales.
 *
 * ---
 * @property id
 * ID autogenerado del movimiento en la base de datos local.
 *
 * @property tipo
 * Tipo de movimiento ([TypeMov]): INGRESO o GASTO. Puede ser `null` en casos especiales.
 *
 * @property importe
 * Cantidad económica del movimiento.
 *
 * @property data_mov
 * Fecha del movimiento en formato `"YYYY-MM-DD"` (o `"YYYY-MM-DD HH:mm:ss"` si se usa con hora).
 *
 * @property descricion
 * Descripción opcional del movimiento.
 *
 * @property categoria_id
 * ID local de la categoría asociada (clave foránea a [Categoria]).
 *
 * @property mov_recur_id
 * ID local del movimiento recurrente que originó este movimiento, o `null` si no es recurrente.
 *
 * @property remote_id
 * ID remoto en PocketBase. Si es `null`, aún no ha sido sincronizado.
 *
 * @property renew_hash
 * Hash único usado para evitar duplicados entre dispositivos
 * cuando se generan movimientos recurrentes automáticamente.
 *
 * @property notificado
 * Indica si este movimiento ya fue notificado localmente.
 * Utilizado para evitar notificaciones repetidas.
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
        Index(value = ["categoria_id"]),
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
    val mov_recur_id: Int? = null,
    val remote_id: String? = null,
    val renew_hash: String? = null,
    val notificado: Boolean = false
)