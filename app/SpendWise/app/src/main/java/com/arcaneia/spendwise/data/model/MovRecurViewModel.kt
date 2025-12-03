package com.arcaneia.spendwise.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcaneia.spendwise.data.entity.MovRecur
import com.arcaneia.spendwise.data.repository.MovRecurRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel responsable de gestionar las operaciones relacionadas con los
 * movimientos recurrentes ([MovRecur]) dentro de la aplicación.
 *
 * Esta clase actúa como capa intermedia entre la interfaz de usuario y el repositorio
 * ([MovRecurRepository]), permitiendo realizar operaciones de inserción,
 * actualización y eliminación sin exponer la lógica interna.
 *
 * Además, mantiene un flujo observable con la lista de movimientos recurrentes
 * ordenados por ID descendente, utilizando `StateFlow` para aportar un estado
 * reactivo y orientado a la UI.
 *
 * @property repository Instancia del repositorio encargado de acceder y gestionar
 * los datos persistidos de movimientos recurrentes.
 */
class MovRecurViewModel(private val repository: MovRecurRepository) : ViewModel() {

    /**
     * Inserta un nuevo movimiento recurrente en la base de datos.
     *
     * La operación se ejecuta dentro de `viewModelScope` para asegurar que se
     * respete el ciclo de vida del ViewModel y evitar fugas de memoria.
     *
     * @param m Objeto [MovRecur] que se desea insertar.
     */
    fun insert(m: MovRecur) = viewModelScope.launch { repository.insert(m) }

    /**
     * Elimina un movimiento recurrente de la base de datos.
     *
     * La operación se ejecuta de forma asíncrona a través de `viewModelScope`.
     *
     * @param m Movimiento recurrente a eliminar.
     */
    fun delete(m: MovRecur) = viewModelScope.launch { repository.delete(m) }

    /**
     * Actualiza los datos de un movimiento recurrente existente en la base de datos.
     *
     * Se utiliza una corrutina ligada al ciclo de vida del ViewModel para
     * realizar la operación de manera segura.
     *
     * @param m Movimiento recurrente con los nuevos valores a actualizar.
     */
    fun update(m: MovRecur) = viewModelScope.launch { repository.update(m) }

    /**
     * Flujo observable que emite la lista completa de movimientos recurrentes.
     *
     * Los valores se obtienen desde el repositorio mediante `getAllMovRecur()`,
     * y se convierten en un `StateFlow` utilizando `stateIn` para asegurar que:
     *
     * - Se mantenga siempre un valor inicial.
     * - La UI pueda suscribirse sin riesgo de recibir `null`.
     * - Se comparta el estado mientras existan suscriptores activos.
     *
     * La lista está ordenada por *id* en orden descendente.
     */
    val movRecurList: StateFlow<List<MovRecur>> = repository.getAllMovRecur()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}