package com.arcaneia.spendwise.data.repository

import com.arcaneia.spendwise.data.dao.CategoriaDao
import com.arcaneia.spendwise.data.entity.Categoria
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio responsable de gestionar las operaciones relacionadas con la entidad [Categoria].
 *
 * Actúa como capa intermedia entre los DAO y el ViewModel, proporcionando una interfaz
 * clara y desacoplada para realizar operaciones de inserción, eliminación y consulta
 * de categorías dentro de la base de datos Room.
 *
 * @property categoriaDao DAO que contiene las operaciones sobre la tabla `categoria`.
 */
class CategoriaRepository(private val categoriaDao: CategoriaDao) {

    /**
     * Inserta una nueva categoría en la base de datos.
     *
     * @param categoria Instancia de [Categoria] que se desea insertar.
     */
    suspend fun insert(categoria: Categoria) = categoriaDao.insert(categoria)

    /**
     * Elimina una categoría existente.
     *
     * @param categoria Instancia de [Categoria] que se desea eliminar.
     */
    suspend fun delete(categoria: Categoria) = categoriaDao.delete(categoria)

    /**
     * Obtiene todas las categorías almacenadas en la base de datos.
     *
     * @return Un `Flow<List<Categoria>>` que emite actualizaciones cada vez que
     * la tabla `categoria` cambia.
     */
    fun getAllCategories(): Flow<List<Categoria>> = categoriaDao.getAllCategories()

    /**
     * Elimina una categoría según su identificador.
     *
     * @param id ID de la categoría que se desea eliminar.
     */
    suspend fun deleteById(id: Int) = categoriaDao.deleteById(id)

    /**
     * Obtiene una categoría según su ID local.
     *
     * @param id Identificador de la categoría.
     * @return La categoría encontrada o `null` si no existe.
     */
    suspend fun getById(id: Int) = categoriaDao.getById(id)
}