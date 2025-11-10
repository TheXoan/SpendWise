package com.arcaneia.spendwise.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arcaneia.spendwise.data.model.Recurrence
import com.arcaneia.spendwise.data.model.TypeMov

@Entity(tableName = "mov_recur")
data class MovRecur(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val importe: Double,
    val periodicidade: Recurrence?, // "MENSUAL", "ANUAL", "SEMANAL"
    val data_ini: Long,
    val data_rnv: Long,
    val tipo: TypeMov? // INGRESO, GASTO
)