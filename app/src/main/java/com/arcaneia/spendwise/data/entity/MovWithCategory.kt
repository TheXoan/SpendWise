package com.arcaneia.spendwise.data.entity

import androidx.room.Embedded

/**
 * Clase auxiliar utilizada para representar un movimiento ([Mov]) junto con
 * el nombre de su categoría correspondiente.
 *
 * Esta clase es el resultado de una consulta `JOIN` entre las tablas
 * `mov` y `categoria`, permitiendo acceder a la información combinada
 * sin necesidad de consultar múltiples entidades por separado.
 *
 * Se usa frecuentemente en listas, reportes o vistas donde se necesita
 * mostrar el movimiento junto al nombre de su categoría.
 *
 * @property mov Instancia completa del movimiento recuperado.
 * @property categoriaNome Nombre de la categoría asociada al movimiento.
 */
data class MovWithCategory(
    @Embedded val mov: Mov,
    val categoriaNome: String
)