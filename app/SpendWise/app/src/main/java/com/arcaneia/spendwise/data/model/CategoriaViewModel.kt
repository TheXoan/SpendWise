package com.arcaneia.spendwise.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcaneia.spendwise.data.entity.Categoria
import com.arcaneia.spendwise.data.repository.CategoriaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar la lógica y el estado relacionado con la entidad [Categoria].
 *
 * Actúa como intermediario entre la UI y el [CategoriaRepository], proporcionando
 * operaciones para insertar, eliminar y obtener categorías, además de exponer un
 * flujo observable del listado actual.
 *
 * Todas las operaciones que acceden al repositorio se ejecutan dentro de
 * `viewModelScope` para respetar el ciclo de vida.
 *
 * @property repository Repositorio encargado de las operaciones sobre categorías.
 */
class CategoriaViewModel(private val repository: CategoriaRepository) : ViewModel() {

    /**
     * Inserta una nueva categoría en el sistema.
     *
     * @param cat Instancia de [Categoria] que se desea insertar.
     */
    fun insert(cat: Categoria) = viewModelScope.launch { repository.insert(cat) }

    /**
     * Elimina una categoría existente.
     *
     * @param cat Instancia de [Categoria] que se desea eliminar.
     */
    fun delete(cat: Categoria) = viewModelScope.launch { repository.delete(cat) }

    /** Flujo interno mutable que contiene la lista actual de categorías. */
    private val _categorias = MutableStateFlow<List<Categoria>>(emptyList())

    /**
     * Flujo público y de solo lectura que expone la lista de categorías.
     * La UI puede observar este estado para actualizarse automáticamente.
     */
    val categorias: StateFlow<List<Categoria>> = _categorias.asStateFlow()

    init {
        obtenerCategorias()
    }

    /**
     * Obtiene todas las categorías desde el repositorio y actualiza el flujo [_categorias].
     *
     * Se ejecuta automáticamente en la inicialización del ViewModel.
     */
    private fun obtenerCategorias() {
        viewModelScope.launch {
            repository.getAllCategories().collect { lista ->
                _categorias.value = lista
            }
        }
    }

    /**
     * Elimina una categoría según su ID.
     *
     * @param id Identificador de la categoría que se desea eliminar.
     */
    fun deleteById(id: Int) = viewModelScope.launch { repository.deleteById(id) }
}