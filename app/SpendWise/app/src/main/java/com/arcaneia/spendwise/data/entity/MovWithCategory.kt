package com.arcaneia.spendwise.data.entity

import androidx.room.Embedded

data class MovWithCategory(
    @Embedded val mov: Mov,
    val categoriaNome: String
)