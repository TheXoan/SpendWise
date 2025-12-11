package com.arcaneia.spendwise.data.database;

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
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0007J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0005H\u0007\u00a8\u0006\f"}, d2 = {"Lcom/arcaneia/spendwise/data/database/Converters;", "", "<init>", "()V", "fromRecurrence", "", "value", "Lcom/arcaneia/spendwise/data/model/Recurrence;", "toRecurrence", "fromTipoMov", "Lcom/arcaneia/spendwise/data/model/TypeMov;", "toTipoMov", "app_debug"})
public final class Converters {
    
    public Converters() {
        super();
    }
    
    /**
     * Convierte un valor del enum [Recurrence] en un `String` que puede ser almacenado en la BD.
     *
     * @param value Valor del enum a convertir.
     * @return Nombre del enum como cadena.
     */
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromRecurrence(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.Recurrence value) {
        return null;
    }
    
    /**
     * Convierte una cadena almacenada en la base de datos en un valor del enum [Recurrence].
     *
     * @param value Cadena almacenada en la BD.
     * @return Instancia correspondiente del enum [Recurrence].
     */
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final com.arcaneia.spendwise.data.model.Recurrence toRecurrence(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
    
    /**
     * Convierte un valor del enum [TypeMov] a una cadena para ser almacenada en la base de datos.
     *
     * @param value Valor del enum a convertir.
     * @return Nombre del enum como cadena.
     */
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromTipoMov(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.TypeMov value) {
        return null;
    }
    
    /**
     * Convierte una cadena almacenada en la base de datos en un valor del enum [TypeMov].
     *
     * @param value Cadena almacenada en la BD.
     * @return Instancia correspondiente del enum [TypeMov].
     */
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final com.arcaneia.spendwise.data.model.TypeMov toTipoMov(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
}