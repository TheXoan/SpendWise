package com.arcaneia.spendwise.utils

import com.arcaneia.spendwise.data.model.Recurrence
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun calculateNextDate(dateIni: String, periodicity: Recurrence): String {

    val date = LocalDate.parse(dateIni)

    return when (periodicity) {
        Recurrence.MENSUAL -> date.plusMonths(1)
        Recurrence.ANUAL   -> date.plusYears(1)
        Recurrence.SEMANAL -> date.plusWeeks(1)
    }.toString()

}

fun formatDateForDB(date: String): String {
    val inFmt = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val outFmt = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return outFmt.format(inFmt.parse(date)!!)
}

fun uiFormat(date: String): String = runCatching {
    LocalDate.parse(date.take(10))
        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}.getOrElse { date }