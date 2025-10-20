package com.arcaneia.spendwise.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mov_recur")
data class MovRecur(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val importe: Double,
    val periodicidade: String, // "mensual", "anual", "semanal"
    val data_ini: Long,
    val data_rnv: Long
)