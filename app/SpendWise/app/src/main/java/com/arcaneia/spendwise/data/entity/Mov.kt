package com.arcaneia.spendwise.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
    ]
)
data class Mov(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tipo: String,
    val importe: Double,
    val data_mov: String,
    val descricion: String? = null,
    val categoria_id: Int,
    val mov_recur_id: Int? = null

)