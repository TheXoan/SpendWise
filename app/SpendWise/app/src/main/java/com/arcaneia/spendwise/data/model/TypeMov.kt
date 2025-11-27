package com.arcaneia.spendwise.data.model

/**
 * Enum que representa los tipos de movimiento económico disponibles dentro
 * de la aplicación: ingresos y gastos.
 *
 * Cada valor incluye una descripción legible que puede usarse directamente
 * en la interfaz de usuario.
 *
 * @property description Texto descriptivo del tipo de movimiento.
 */
enum class TypeMov(val description: String) {

    /** Representa un movimiento de tipo ingreso. */
    INGRESO("Ingreso"),

    /** Representa un movimiento de tipo gasto. */
    GASTO("Gasto"),
}