package com.arcaneia.spendwise.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.repository.MovRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MovViewModel(private val repository: MovRepository) : ViewModel() {

    val balanceMes = repository.getBalanceMesActual()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )

    fun insert(movimiento: Mov) = viewModelScope.launch { repository.insert(movimiento) }
    fun delete(movimiento: Mov) = viewModelScope.launch { repository.delete(movimiento) }
}