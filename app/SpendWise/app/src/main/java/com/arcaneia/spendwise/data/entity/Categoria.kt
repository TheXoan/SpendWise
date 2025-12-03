package com.arcaneia.spendwise.data.entity

import androidx.room.*

/**
 * Entidad que representa una categoría dentro de la base de datos de Room.
 *
 * Las categorías permiten clasificar los movimientos (ingresos o gastos)
 * según su naturaleza, como por ejemplo: “Comida”, “Transporte”, “Salario”, etc.
 *
 * La tabla asociada se llama `categoria`.
 *
 * @property id Identificador único de la categoría.
 * Se genera automáticamente mediante `autoGenerate = true`.
 *
 * @property nome Nombre descriptivo de la categoría (ej.: "Comida", "Recurrente").
 *
 * @property tipo Tipo de categoría, que puede ser utilizado para diferenciar
 * categorías especiales o para marcar comportamientos específicos dentro de la app.
 */
@Entity(tableName = "categoria")
data class Categoria(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nome: String,

    val tipo: String

)