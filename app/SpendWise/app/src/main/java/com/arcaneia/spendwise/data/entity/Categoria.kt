package com.arcaneia.spendwise.data.entity

import androidx.room.*

@Entity(tableName = "categoria")
data class Categoria(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nombre: String,

    val tipo: String

)