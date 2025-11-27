package com.arcaneia.spendwise.utils

import com.arcaneia.spendwise.data.model.Recurrence
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

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
fun calculateNextDate(dateIni: String, periodicity: Recurrence): String {

    val date = LocalDate.parse(dateIni)

    return when (periodicity) {
        Recurrence.MENSUAL -> date.plusMonths(1)
        Recurrence.ANUAL   -> date.plusYears(1)
        Recurrence.SEMANAL -> date.plusWeeks(1)
    }.toString()
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
fun formatDateForDB(date: String): String {
    val inFmt = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val outFmt = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return outFmt.format(inFmt.parse(date)!!)
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
fun uiFormat(date: String): String = runCatching {
    LocalDate.parse(date.take(10))
        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}.getOrElse { date }