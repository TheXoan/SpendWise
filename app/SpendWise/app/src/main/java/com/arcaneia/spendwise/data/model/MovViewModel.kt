package com.arcaneia.spendwise.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.entity.MovWithCategory
import com.arcaneia.spendwise.data.repository.MovRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar los movimientos económicos ([Mov]) y sus
 * variantes enriquecidas con categoría ([MovWithCategory]).
 *
 * Proporciona operaciones CRUD sobre movimientos mediante el repositorio
 * ([MovRepository]) y expone múltiples flujos reactivos (`StateFlow`) que
 * permiten construir interfaces dinámicas basadas en:
 *
 * - El balance económico del mes actual.
 * - Los años disponibles con movimientos.
 * - Los meses disponibles en función del año seleccionado.
 * - Los movimientos filtrados por año y mes.
 *
 * El uso de `stateIn`, `combine`, `flatMapLatest` y `MutableStateFlow`
 * permite que la UI siempre reciba datos consistentes y actualizados,
 * reaccionando ante cualquier cambio de selección.
 *
 * @property repository Repositorio que gestiona el acceso y operaciones
 * sobre los datos persistidos de movimientos.
 */
class MovViewModel(private val repository: MovRepository) : ViewModel() {

    /**
     * Inserta un movimiento en la base de datos.
     *
     * @param movimiento Movimiento a insertar.
     */
    fun insert(movimiento: Mov) = viewModelScope.launch { repository.insert(movimiento) }

    /**
     * Elimina un movimiento de la base de datos.
     *
     * @param movimiento Movimiento a eliminar.
     */
    fun delete(movimiento: Mov) = viewModelScope.launch { repository.delete(movimiento) }

    /**
     * Actualiza un movimiento existente en la base de datos.
     *
     * @param movimiento Movimiento con los datos actualizados.
     */
    fun update(movimiento: Mov) = viewModelScope.launch { repository.update(movimiento) }

    /**
     * Balance económico del mes actual.
     *
     * Este flujo emite el total de ingresos menos gastos correspondientes
     * al mes en curso. Se expone como `StateFlow` con valor inicial 0.0.
     */
    val balanceMes = repository.getBalanceMesActual()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )

    /**
     * Lista de años que contienen movimientos registrados.
     *
     * Se utiliza para la generación dinámica del selector de años.
     */
    val yearsAvailable = repository.getYearsWithValues()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    // ---------------------------------------------------------------------------------------------
    // Selección de año
    // ---------------------------------------------------------------------------------------------

    /** Flujo interno que representa el año seleccionado. */
    private val _selectedYear = MutableStateFlow<String?>(null)

    /**
     * Año actualmente seleccionado por el usuario.
     *
     * Una vez modificado, reinicia también la selección de mes
     * para garantizar coherencia en los filtros.
     */
    val selectedYearFlow: StateFlow<String?> = _selectedYear

    /**
     * Cambia el año seleccionado y reinicia la selección de mes.
     *
     * @param year Año seleccionado.
     */
    fun onYearSelected(year: String) {
        _selectedYear.value = year
        _selectedMonth.value = null // Al cambiar el año, reseteamos el mes
    }

    /**
     * Meses disponibles según el año seleccionado.
     *
     * Cada vez que cambia el año, se consulta el repositorio para
     * obtener únicamente los meses que contienen movimientos.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    val monthsAvailable = _selectedYear
        .filterNotNull()
        .flatMapLatest { year ->
            repository.getMonthsFromYear(year)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    // ---------------------------------------------------------------------------------------------
    // Selección de mes
    // ---------------------------------------------------------------------------------------------

    /** Flujo interno que representa el mes seleccionado. */
    private val _selectedMonth = MutableStateFlow<String?>(null)

    /**
     * Mes actualmente seleccionado por el usuario.
     */
    val selectedMonthFlow: StateFlow<String?> = _selectedMonth

    /**
     * Cambia el mes seleccionado.
     *
     * @param month Mes seleccionado.
     */
    fun onMonthSelected(month: String) { _selectedMonth.value = month }

    // ---------------------------------------------------------------------------------------------
    // Movimientos filtrados por año y mes
    // ---------------------------------------------------------------------------------------------

    /**
     * Lista de movimientos filtrados según el año y el mes seleccionados.
     *
     * Funcionamiento:
     * - Se combinan ambos flujos (`_selectedYear` y `_selectedMonth`).
     * - Si ambos valores están establecidos, se consulta el repositorio para
     *   obtener los movimientos correspondientes.
     * - Si falta alguno, se emite una lista vacía.
     *
     * El uso de `flatMapLatest` garantiza que los datos se actualicen
     * automáticamente cuando cambia cualquiera de las selecciones.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    val movements: StateFlow<List<MovWithCategory>> = combine(_selectedYear, _selectedMonth) { year, month ->
        if (year != null && month != null) {
            repository.getMovementsForYearMonth(year, month)
        } else {
            flowOf(emptyList<MovWithCategory>())
        }
    }.flatMapLatest { it }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
}