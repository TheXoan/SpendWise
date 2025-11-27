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

class MovViewModel(private val repository: MovRepository) : ViewModel() {


    fun insert(movimiento: Mov) = viewModelScope.launch { repository.insert(movimiento) }
    fun delete(movimiento: Mov) = viewModelScope.launch { repository.delete(movimiento) }
    fun update(movimiento: Mov) = viewModelScope.launch { repository.update(movimiento) }

    val balanceMes = repository.getBalanceMesActual()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )


    /**
     * Años con movimientos
     */
    val yearsAvailable = repository.getYearsWithValues()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    // Año seleccionado
    private val _selectedYear = MutableStateFlow<String?>(null)
    val selectedYearFlow: StateFlow<String?> = _selectedYear
    fun onYearSelected(year: String) {_selectedYear.value = year
        _selectedMonth.value = null // Resetear mes seleccionado al cambiar el año
    }

    /**
     * Meses con movimientos según el año
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

    // Mes seleccionado
    private val _selectedMonth = MutableStateFlow<String?>(null)
    val selectedMonthFlow: StateFlow<String?> = _selectedMonth
    fun onMonthSelected(month: String) {_selectedMonth.value = month}

    /**
     * Movimientos filtrados por mes y año
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