package com.arcaneia.spendwise.data.model

/**
 * Enum que representa los distintos tipos de **recurrencia** disponibles para
 * los movimientos recurrentes dentro de la aplicación.
 *
 * Cada valor incluye una descripción legible que puede utilizarse directamente
 * en la interfaz de usuario.
 *
 * @property description Texto descriptivo del tipo de recurrencia.
 */
enum class Recurrence(val description: String) {

    /** Movimiento recurrente que se repite cada semana. */
    SEMANAL("Semanal"),

    /** Movimiento recurrente que se repite una vez al mes. */
    MENSUAL("Mensual"),

    /** Movimiento recurrente que se repite una vez al año. */
    ANUAL("Anual")
}