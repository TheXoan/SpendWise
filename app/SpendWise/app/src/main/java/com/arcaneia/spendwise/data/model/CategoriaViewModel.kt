package com.arcaneia.spendwise.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcaneia.spendwise.data.entity.Categoria
import com.arcaneia.spendwise.data.repository.CategoriaRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CategoriaViewModel(private val repository: CategoriaRepository) : ViewModel() {

    fun insert(cat: Categoria) = viewModelScope.launch { repository.insert(cat) }
    fun delete(cat: Categoria) = viewModelScope.launch { repository.delete(cat) }

}