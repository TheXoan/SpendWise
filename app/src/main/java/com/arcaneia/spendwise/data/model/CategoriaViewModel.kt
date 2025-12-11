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
import com.arcaneia.spendwise.data.datastore.TokenDataStore
import kotlinx.coroutines.flow.first

/**
 * ViewModel encargado de gestionar la lógica de categorías dentro de la aplicación.
 *
 * Esta clase implementa un flujo de trabajo **offline-first con sincronización instantánea**:
 *
 * - Cualquier cambio (insert/update/delete) se aplica inmediatamente a la base de datos local.
 * - Automáticamente se intenta sincronizar la operación con PocketBase.
 * - Los cambios locales se observan en tiempo real mediante un `StateFlow`.
 *
 * El ViewModel actúa como capa intermedia entre la UI, el repositorio local y la API remota,
 * asegurando consistencia entre los datos locales y los remotos sin bloquear la interfaz.
 *
 * @property repository Repositorio local encargado del acceso a `CategoriaDao`.
 * @property remoteDataSource Cliente remoto encargado de realizar operaciones en PocketBase.
 * @property appContext Contexto necesario para acceder a `TokenDataStore` y obtener el `userId`.
 */
class CategoriaViewModel(
    private val repository: CategoriaRepository,
    private val remoteDataSource: CategoriaRemoteDataSource,
    private val appContext: android.content.Context,
) : ViewModel() {

    /**
     * Flujo interno que mantiene la lista de categorías observada desde Room.
     */
    private val _categorias = MutableStateFlow<List<Categoria>>(emptyList())

    /**
     * Flujo de solo lectura que expone la lista de categorías a la UI.
     */
    val categorias: StateFlow<List<Categoria>> = _categorias.asStateFlow()

    init {
        // Observa automáticamente cambios en la base de datos local
        viewModelScope.launch {
            repository.getAllCategories().collect {
                _categorias.value = it
            }
        }
    }

    /**
     * Inserta una categoría **localmente e inmediatamente**, y luego intenta sincronizarla
     * con el servidor.
     *
     * Flujo de trabajo:
     * 1. Se inserta en Room.
     * 2. Se crea el registro correspondiente en PocketBase.
     * 3. Se actualiza el `remote_id` en la base de datos local.
     *
     * @param cat Categoría que se desea insertar.
     */
    fun insert(cat: Categoria) = viewModelScope.launch {
        // 1️⃣ Insert local
        val localId = repository.insert(cat).toInt()

        try {
            // 2️⃣ Crear en PocketBase
            val userId = TokenDataStore.getUserId(appContext).first()!!
            val created = remoteDataSource.create(userId, cat)

            // 3️⃣ Actualizar remote_id en local usando update
            repository.getById(localId)?.let { saved ->
                repository.update(saved.copy(remote_id = created.id))
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Actualiza una categoría tanto local como remotamente.
     *
     * - Primero se actualiza en la base de datos local.
     * - Si ya fue sincronizada previamente (tiene `remote_id`), también se actualiza en PocketBase.
     *
     * Se preserva el `remote_id` original y solo se modifican los campos editables.
     *
     * @param cat Categoría con los nuevos valores a aplicar.
     */
    fun update(cat: Categoria) = viewModelScope.launch {

        val saved = repository.getById(cat.id) ?: return@launch

        // construir merge correcto
        val categoryToSave = saved.copy(
            nome = cat.nome,
            tipo = saved.tipo,
            remote_id = saved.remote_id
        )

        // 1. actualizar local
        repository.update(categoryToSave)

        // 2. actualizar remoto inmediatamente (si tiene remote_id)
        categoryToSave.remote_id?.let { remoteId ->
            try {
                remoteDataSource.update(remoteId, categoryToSave)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Elimina una categoría localmente y, si corresponde, también remotamente.
     *
     * Flujo:
     * - Si la categoría tiene un `remote_id`, se elimina primero de PocketBase.
     * - Luego se elimina en Room.
     *
     * @param id Identificador local de la categoría que se desea eliminar.
     */
    fun deleteById(id: Int) = viewModelScope.launch {
        repository.getById(id)?.let { cat ->
            // Borrar en PocketBase si tiene remote_id
            cat.remote_id?.let { remoteId ->
                try {
                    remoteDataSource.delete(remoteId)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            // Borrar en local
            repository.deleteById(id)
        }
    }
}