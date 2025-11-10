package com.arcaneia.spendwise.data.database

import androidx.room.TypeConverter
import com.arcaneia.spendwise.data.model.Recurrence
import com.arcaneia.spendwise.data.model.TypeMov

class Converters {

    // --- Recurrence ---
    @TypeConverter
    fun fromRecurrence(value: Recurrence): String = value.name

    @TypeConverter
    fun toRecurrence(value: String): Recurrence = Recurrence.valueOf(value)

    // --- Type Mov ---

    @TypeConverter
    fun fromTipoMov(value: TypeMov): String {
        return value.name
    }

    @TypeConverter
    fun toTipoMov(value: String): TypeMov {
        return TypeMov.valueOf(value)
    }
}