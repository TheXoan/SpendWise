package com.arcaneia.spendwise.utils;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u000e\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001\u001a\u000e\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001\u00a8\u0006\b"}, d2 = {"calculateNextDate", "", "dateIni", "periodicity", "Lcom/arcaneia/spendwise/data/model/Recurrence;", "formatDateForDB", "date", "uiFormat", "app_debug"})
public final class DateToolsKt {
    
    /**
     * Calcula la siguiente fecha de renovación para un movimiento recurrente.
     *
     * @param dateIni Fecha inicial en formato `yyyy-MM-dd`.
     * @param periodicity Tipo de recurrencia (semanal, mensual o anual).
     * @return Fecha resultante en formato `yyyy-MM-dd`.
     *
     * Esta función interpreta la fecha inicial como un `LocalDate`, aplica la
     * cantidad de tiempo correspondiente según la periodicidad y devuelve la fecha
     * calculada en formato base para la base de datos.
     *
     * Ejemplos:
     * - Si dateIni = "2025-02-01" y periodicity = MENSUAL → "2025-03-01"
     * - Si dateIni = "2025-02-01" y periodicity = SEMANAL → "2025-02-08"
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String calculateNextDate(@org.jetbrains.annotations.NotNull()
    java.lang.String dateIni, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.Recurrence periodicity) {
        return null;
    }
    
    /**
     * Convierte una fecha desde el formato UI (`dd/MM/yyyy`) al formato
     * utilizado en la base de datos (`yyyy-MM-dd`).
     *
     * @param date Fecha en formato `dd/MM/yyyy`.
     * @return Fecha convertida en formato `yyyy-MM-dd`.
     *
     * @throws Exception si el formato de entrada no es válido.
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatDateForDB(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    /**
     * Formatea una fecha al formato amigable de UI (`dd/MM/yyyy`).
     *
     * Esta función:
     * - Recorta el string a 10 caracteres por seguridad.
     * - Intenta parsearlo como `yyyy-MM-dd`.
     * - Si falla, devuelve la fecha original sin modificar.
     *
     * @param date Fecha en formato `yyyy-MM-dd` o similar.
     * @return Fecha convertida al formato `dd/MM/yyyy`, o el mismo valor si ocurre un error.
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String uiFormat(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
}