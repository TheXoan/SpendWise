package com.arcaneia.spendwise.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcaneia.spendwise.data.entity.Categoria
import com.arcaneia.spendwise.data.repository.CategoriaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoriaViewModel(private val repository: CategoriaRepository) : ViewModel() {

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
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

    fun deleteById(id: Int) = viewModelScope.launch { repository.deleteById(id) }
}