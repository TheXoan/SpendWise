package com.arcaneia.spendwise.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcaneia.spendwise.data.entity.Categoria
import com.arcaneia.spendwise.data.repository.CategoriaRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CategoriaViewModel(private val repository: CategoriaRepository) : ViewModel() {

    fun insert(cat: Categoria) = viewModelScope.launch { repository.insert(cat) }
    fun delete(cat: Categoria) = viewModelScope.launch { repository.delete(cat) }



    private val _categorias = MutableStateFlow<List<Categoria>>(emptyList())
    val categorias: StateFlow<List<Categoria>> = _categorias.asStateFlow()

    init {
        obtenerCategorias()
    }

    private fun obtenerCategorias() {
        viewModelScope.launch {
            repository.getAllCategories().collect { lista ->
                _categorias.value = lista
            }
        }
    }
}