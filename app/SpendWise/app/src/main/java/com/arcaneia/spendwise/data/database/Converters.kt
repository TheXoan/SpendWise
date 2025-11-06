package com.arcaneia.spendwise.data.database

import androidx.room.TypeConverter
import com.arcaneia.spendwise.data.model.Recurrence

class Converters {

    // --- Recurrence ---
    @TypeConverter
    fun fromRecurrence(value: Recurrence): String = value.name

    @TypeConverter
    fun toRecurrence(value: String): Recurrence = Recurrence.valueOf(value)

}