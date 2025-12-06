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
     *
     * Si ocurre un conflicto (por ejemplo, IDs repetidos), se reemplaza el registro existente.
     *
     * @param categoria Entidad a insertar.
     * @return El ID autogenerado de la fila insertada.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoria: Categoria): Long

    /**
     * Actualiza los datos de una categoría existente.
     *
     * @param categoria Categoría con los nuevos valores.
     */
    @Update
    suspend fun update(categoria: Categoria)

    /**
     * Elimina una categoría de la base de datos.
     *
     * @param categoria Entidad que se desea eliminar.
     */
    @Delete
    suspend fun delete(categoria: Categoria)

    /**
     * Recupera todas las categorías excepto la de ID 1.
     *
     * @return Un flujo con la lista de categorías actualizada en tiempo real.
     */
    @Query("SELECT * FROM categoria WHERE id != 1")
    fun getAllCategories(): Flow<List<Categoria>>

    /**
     * Elimina una categoría según su ID.
     *
     * @param categoriaId ID de la categoría que se desea eliminar.
     */
    @Query("DELETE FROM categoria WHERE id = :categoriaId")
    suspend fun deleteById(categoriaId: Int)

    /**
     * Busca una categoría por su ID.
     *
     * @param id Identificador local.
     * @return La categoría encontrada o null si no existe.
     */
    @Query("SELECT * FROM categoria WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Categoria?


    // --------------------------
    // 🔥 FUNCIONES PARA SYNC
    // --------------------------

    /**
     * Obtiene todas las categorías locales que aún no fueron subidas al servidor,
     * es decir, aquellas cuyo `remote_id` es null.
     *
     * @return Lista de categorías pendientes de sincronización.
     */
    @Query("SELECT * FROM categoria WHERE remote_id IS NULL")
    suspend fun getPendingToUpload(): List<Categoria>

    /**
     * Busca una categoría local usando su ID remoto.
     *
     * @param remoteId Identificador asignado por el servidor (PocketBase).
     * @return La categoría correspondiente o null si no existe.
     */
    @Query("SELECT * FROM categoria WHERE remote_id = :remoteId LIMIT 1")
    suspend fun getByRemoteId(remoteId: String): Categoria?

    /**
     * Obtiene todas las categorías que ya tienen un ID remoto asignado.
     *
     * @return Lista de categorías sincronizadas con PocketBase.
     */
    @Query("SELECT * FROM categoria WHERE remote_id IS NOT NULL")
    suspend fun getAllWithRemoteId(): List<Categoria>

    /**
     * Asigna un `remote_id` a una categoría previamente insertada en la base de datos local.
     *
     * @param localId ID local de la categoría.
     * @param remoteId ID remoto obtenido del servidor.
     */
    @Query("UPDATE categoria SET remote_id = :remoteId WHERE id = :localId")
    suspend fun attachRemoteId(localId: Int, remoteId: String)
}