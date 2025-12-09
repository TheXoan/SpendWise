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
 * Incluye operaciones CRUD básicas y un conjunto de métodos especializados
 * para la sincronización con el backend PocketBase, permitiendo mapear
 * categorías locales con sus equivalentes remotos mediante `remote_id`.
 *
 * Este DAO está optimizado para funcionar en entornos offline-first,
 * garantizando que cada categoría pueda ser insertada, actualizada,
 * consultada o marcada como sincronizada dependiendo del estado
 * de la base de datos local y remota.
 */
@Dao
interface CategoriaDao {

    /**
     * Inserta una nueva categoría en la base de datos.
     *
     * Si ocurre un conflicto (por ejemplo, IDs repetidos),
     * el registro se reemplaza usando `OnConflictStrategy.REPLACE`.
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
     * Recupera todas las categorías excepto la de ID 1,
     * que corresponde a la categoría reservada **"Recurrente"**.
     *
     * @return Un flujo reactivo con la lista de categorías.
     */
    @Query("SELECT * FROM categoria WHERE id != 1 ORDER BY id ASC")
    fun getAllCategories(): Flow<List<Categoria>>

    /**
     * Elimina una categoría según su ID.
     *
     * @param categoriaId ID de la categoría que se desea eliminar.
     */
    @Query("DELETE FROM categoria WHERE id = :categoriaId")
    suspend fun deleteById(categoriaId: Int)

    /**
     * Busca una categoría por su ID local.
     *
     * @param id Identificador autogenerado por Room.
     * @return Categoría encontrada, o `null` si no existe.
     */
    @Query("SELECT * FROM categoria WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Categoria?


    // -------------------------------------------------------------------------
    // 🔥 FUNCIONES PARA SINCRONIZACIÓN (PocketBase)
    // -------------------------------------------------------------------------

    /**
     * Devuelve todas las categorías locales que aún no se han subido al servidor,
     * es decir, aquellas cuyo `remote_id` es null.
     *
     * Esta lista se utiliza durante la fase de *subida* en procesos de sincronización.
     *
     * @return Lista de categorías pendientes de sincronización.
     */
    @Query("SELECT * FROM categoria WHERE remote_id IS NULL")
    suspend fun getPendingToUpload(): List<Categoria>

    /**
     * Busca una categoría local mediante su identificador remoto (`remote_id`).
     *
     * Se usa durante el proceso de fusión (merge) entre los datos remotos
     * y locales para evitar duplicados.
     *
     * @param remoteId ID asignado por PocketBase.
     * @return La categoría local correspondiente o `null` si no existe.
     */
    @Query("SELECT * FROM categoria WHERE remote_id = :remoteId LIMIT 1")
    suspend fun getByRemoteId(remoteId: String): Categoria?

    /**
     * Obtiene todas las categorías que ya tienen asignado un `remote_id`,
     * lo cual indica que ya han sido sincronizadas con el servidor.
     *
     * Esta lista es fundamental para detectar eliminaciones remotas.
     *
     * @return Lista de categorías sincronizadas.
     */
    @Query("SELECT * FROM categoria WHERE remote_id IS NOT NULL")
    suspend fun getAllWithRemoteId(): List<Categoria>

    /**
     * Asigna un `remote_id` a una categoría almacenada en la base de datos local.
     *
     * Este método se usa tras insertar la categoría en PocketBase,
     * permitiendo enlazar la fila local con su identificación remota.
     *
     * @param localId ID autogenerado en Room.
     * @param remoteId ID remoto asignado por PocketBase.
     */
    @Query("UPDATE categoria SET remote_id = :remoteId WHERE id = :localId")
    suspend fun attachRemoteId(localId: Int, remoteId: String)
}