package com.arcaneia.spendwise.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.arcaneia.spendwise.data.entity.Categoria
import kotlinx.coroutines.flow.Flow

/**
 * DAO (Data Access Object) para gestionar todas las operaciones relacionadas
 * con la entidad [Categoria] dentro de la base de datos Room.
 *
 * Proporciona métodos para insertar, actualizar, eliminar y consultar categorías.
 * Algunos métodos son `suspend` ya que realizan operaciones de escritura en la base
 * de datos, mientras que las consultas que devuelven flujos usan `Flow` para
 * observar cambios en tiempo real.
 */
@Dao
interface CategoriaDao {

    /**
     * Inserta una nueva categoría en la base de datos.
     * Si ya existe una categoría con el mismo ID, se reemplaza.
     *
     * @param categoria Instancia de [Categoria] que será insertada.
     * @return El ID generado para la nueva categoría.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoria: Categoria): Long

    /**
     * Actualiza los campos de una categoría existente.
     *
     * @param categoria Instancia de [Categoria] con los datos actualizados.
     */
    @Update
    suspend fun update(categoria: Categoria)

    /**
     * Elimina una categoría específica de la base de datos.
     *
     * @param categoria Instancia de [Categoria] que será eliminada.
     */
    @Delete
    suspend fun delete(categoria: Categoria)

    /**
     * Obtiene todas las categorías almacenadas en la base de datos.
     *
     * @return Un flujo (`Flow`) que emite listas de [Categoria] cada vez que
     * la tabla cambia, permitiendo actualizaciones en tiempo real.
     */
    @Query("SELECT * FROM categoria WHERE id != 0")
    fun getAllCategories(): Flow<List<Categoria>>

    /**
     * Elimina una categoría de la base de datos según su ID.
     *
     * @param categoriaId ID de la categoría que se desea eliminar.
     */
    @Query("DELETE FROM categoria WHERE id = :categoriaId")
    suspend fun deleteById(categoriaId: Int)
}