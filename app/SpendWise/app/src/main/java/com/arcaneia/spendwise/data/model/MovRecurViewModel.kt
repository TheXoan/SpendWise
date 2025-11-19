package com.arcaneia.spendwise.data.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcaneia.spendwise.data.entity.MovRecur
import com.arcaneia.spendwise.data.repository.MovRecurRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MovRecurViewModel(private val repository: MovRecurRepository) : ViewModel() {


    fun insert(m: MovRecur) = viewModelScope.launch { repository.insert(m) }
    fun delete(m: MovRecur) = viewModelScope.launch { repository.delete(m) }
    fun update(m: MovRecur) = viewModelScope.launch { repository.update(m) }

    // Todos los Movimientos recurrentes ordenados por id DESC
    val movRecurList: StateFlow<List<MovRecur>> = repository.getAllMovRecur()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}