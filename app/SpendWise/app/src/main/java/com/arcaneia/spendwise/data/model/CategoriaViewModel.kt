package com.arcaneia.spendwise.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcaneia.spendwise.apis.data.model.CategoriaRemoteDataSource
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
 * También gestiona la eliminación remota de categorías utilizando [remoteDataSource],
 * asegurando consistencia entre la base de datos local y PocketBase.
 *
 * Todas las operaciones que acceden al repositorio se ejecutan dentro de
 * `viewModelScope` para respetar el ciclo de vida.
 *
 * @property repository Repositorio encargado de las operaciones locales sobre categorías.
 * @property remoteDataSource Fuente de datos remota utilizada para eliminar categorías en PocketBase.
 */
class CategoriaViewModel(
    private val repository: CategoriaRepository,
    private val remoteDataSource: CategoriaRemoteDataSource
) : ViewModel() {

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
     * Elimina una categoría según su ID, gestionando tanto el borrado remoto
     * como el local.
     *
     * Flujo del proceso:
     * 1. Verifica si la categoría existe en la base de datos local.
     * 2. Si tiene `remote_id`, intenta eliminarla también en PocketBase mediante [remoteDataSource].
     * 3. Finalmente, elimina la categoría de la base de datos local mediante el repositorio.
     *
     * Si ocurre un error durante el borrado remoto, este se captura y se imprime,
     * pero el borrado local continúa para evitar inconsistencias internas.
     *
     * @param id Identificador de la categoría que se desea eliminar.
     */
    fun deleteById(id: Int) = viewModelScope.launch {
        val categoria = repository.getById(id)

        if (categoria != null) {
            // 1️⃣ Borrar en PocketBase si existe remota
            categoria.remote_id?.let { remoteId ->
                try {
                    remoteDataSource.delete(remoteId)
                } catch (e: Exception) {
                    e.printStackTrace()
                    // si falla, puedes decidir marcarla para borrado pendiente (offline real)
                }
            }

            // 2️⃣ Borrar en local siempre
            repository.deleteById(id)
        }
    }
}