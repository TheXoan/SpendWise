package com.arcaneia.spendwise.data.database

import androidx.room.TypeConverter
import com.arcaneia.spendwise.data.model.Recurrence
import com.arcaneia.spendwise.data.model.TypeMov

/**
 * Clase que contiene los convertidores de tipos utilizados por Room para almacenar
 * correctamente valores de enums en la base de datos.
 *
 * Room no puede guardar directamente tipos enumerados (`enum class`), por lo que
 * estos convertidores permiten transformar los enums [Recurrence] y [TypeMov] a
 * `String` para su almacenamiento, y viceversa para su recuperación.
 *
 * Esta clase se registra en la base de datos mediante la anotación
 * `@TypeConverters(Converters::class)` en [AppDatabase].
 */
class Converters {

    // --- Recurrence ---

    /**
     * Convierte un valor del enum [Recurrence] en un `String` que puede ser almacenado en la BD.
     *
     * @param value Valor del enum a convertir.
     * @return Nombre del enum como cadena.
     */
    @TypeConverter
    fun fromRecurrence(value: Recurrence): String = value.name

    /**
     * Convierte una cadena almacenada en la base de datos en un valor del enum [Recurrence].
     *
     * @param value Cadena almacenada en la BD.
     * @return Instancia correspondiente del enum [Recurrence].
     */
    @TypeConverter
    fun toRecurrence(value: String): Recurrence = Recurrence.valueOf(value)

    // --- TypeMov ---

    /**
     * Convierte un valor del enum [TypeMov] a una cadena para ser almacenada en la base de datos.
     *
     * @param value Valor del enum a convertir.
     * @return Nombre del enum como cadena.
     */
    @TypeConverter
    fun fromTipoMov(value: TypeMov): String {
        return value.name
    }

    /**
     * Convierte una cadena almacenada en la base de datos en un valor del enum [TypeMov].
     *
     * @param value Cadena almacenada en la BD.
     * @return Instancia correspondiente del enum [TypeMov].
     */
    @TypeConverter
    fun toTipoMov(value: String): TypeMov {
        return TypeMov.valueOf(value)
    }
}