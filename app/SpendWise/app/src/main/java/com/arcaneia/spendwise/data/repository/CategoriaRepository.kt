package com.arcaneia.spendwise.data.repository

import com.arcaneia.spendwise.data.dao.CategoriaDao
import com.arcaneia.spendwise.data.entity.Categoria
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio responsable de manejar todas las operaciones relacionadas con la entidad [Categoria].
 *
 * Esta clase actúa como una capa intermedia entre el *Data Access Object* ([CategoriaDao])
 * y el ViewModel correspondiente, proporcionando un punto de acceso único y estructurado para:
 *
 * - Insertar nuevas categorías.
 * - Actualizar y eliminar categorías existentes.
 * - Consultar la lista completa de categorías almacenadas.
 * - Buscar categorías por ID.
 *
 * El uso de un repositorio logra:
 * - Desacoplar las operaciones de acceso a datos de la lógica de UI.
 * - Facilitar la escalabilidad y el testeo.
 * - Centralizar la lógica de lectura y escritura en la base de datos Room.
 *
 * > **Nota:** Este repositorio trabaja exclusivamente con datos locales mediante Room.
 * > La sincronización remota con PocketBase es gestionada por clases específicas de sincronización.
 *
 * @property categoriaDao DAO encargado de ejecutar las operaciones sobre la tabla `categoria`.
 */
class CategoriaRepository(private val categoriaDao: CategoriaDao) {

    /**
     * Inserta una nueva categoría en la base de datos local.
     *
     * @param categoria Objeto [Categoria] que se desea insertar.
     * @return El ID autogenerado de la categoría insertada.
     */
    suspend fun insert(categoria: Categoria) = categoriaDao.insert(categoria)

    /**
     * Elimina una categoría especificada de la base de datos.
     *
     * @param categoria Entidad a eliminar.
     */
    suspend fun delete(categoria: Categoria) = categoriaDao.delete(categoria)

    /**
     * Obtiene un flujo reactivo con la lista completa de categorías almacenadas.
     *
     * Se excluye la categoría con ID 1 si así lo define el DAO, y los cambios se emiten
     * automáticamente a cualquier suscriptor cada vez que la tabla `categoria` es modificada.
     *
     * @return [Flow] que emite una lista de [Categoria].
     */
    fun getAllCategories(): Flow<List<Categoria>> = categoriaDao.getAllCategories()

    /**
     * Elimina una categoría usando su ID local.
     *
     * @param id Identificador de la categoría.
     */
    suspend fun deleteById(id: Int) = categoriaDao.deleteById(id)

    /**
     * Recupera una categoría a partir de su ID.
     *
     * @param id ID local de la categoría.
     * @return La categoría encontrada, o `null` si no existe.
     */
    suspend fun getById(id: Int) = categoriaDao.getById(id)

    /**
     * Actualiza una categoría previamente registrada en la base de datos.
     *
     * @param categoria Entidad con los valores actualizados.
     */
    suspend fun update(categoria: Categoria) = categoriaDao.update(categoria)
}