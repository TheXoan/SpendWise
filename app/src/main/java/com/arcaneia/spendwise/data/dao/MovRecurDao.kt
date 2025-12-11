package com.arcaneia.spendwise.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.arcaneia.spendwise.data.entity.MovRecur
import kotlinx.coroutines.flow.Flow

/**
 * DAO (Data Access Object) encargado de gestionar las operaciones relacionadas con
 * movimientos recurrentes representados por la entidad [MovRecur].
 *
 * Incluye funciones para insertar, actualizar, eliminar y consultar movimientos
 * recurrentes, así como obtener aquellos que deben renovarse según su fecha programada
 * y métodos auxiliares para la sincronización con el servidor.
 */
@Dao
interface MovRecurDao {

    /**
     * Inserta un movimiento recurrente en la base de datos.
     * Si ya existe otro con el mismo ID, será reemplazado (`OnConflictStrategy.REPLACE`).
     *
     * @param movRecur Instancia del movimiento recurrente que se desea insertar.
     * @return El ID autogenerado del registro insertado.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movRecur: MovRecur): Long

    /**
     * Elimina un movimiento recurrente existente.
     *
     * @param movRecur Instancia de [MovRecur] que debe eliminarse.
     */
    @Delete
    suspend fun delete(movRecur: MovRecur)

    /**
     * Actualiza los datos de un movimiento recurrente existente.
     *
     * @param movRecur Instancia con los campos actualizados.
     */
    @Update
    suspend fun update(movRecur: MovRecur)

    /**
     * Obtiene todos los movimientos recurrentes ordenados por la fecha de la próxima renovación
     * (`data_rnv`) en orden ascendente.
     *
     * @return Un [Flow] de [List] de [MovRecur] que emite la lista completa cada vez que cambia la tabla.
     */
    @Query("SELECT * FROM mov_recur ORDER BY data_rnv ASC")
    fun getAllMovRecur(): Flow<List<MovRecur>>

    /**
     * Obtiene todos los movimientos recurrentes cuya fecha de renovación (`data_rnv`) sea
     * anterior o igual a la fecha indicada.
     *
     * Esta función es crucial para determinar qué movimientos deben renovarse en el día actual
     * o han vencido.
     *
     * @param today Fecha límite en formato `"YYYY-MM-DD"`.
     * @return Una lista con los movimientos recurrentes [MovRecur] que deben renovarse.
     */
    @Query("SELECT * FROM mov_recur WHERE data_rnv <= :today")
    suspend fun getMovsToRenew(today: String): List<MovRecur>

    /**
     * Busca un movimiento recurrente por su ID local (clave primaria de Room).
     *
     * @param id El ID local (Int) del movimiento recurrente.
     * @return El movimiento [MovRecur] correspondiente, o `null` si no se encuentra.
     */
    @Query("SELECT * FROM mov_recur WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): MovRecur?

    /**
     * Obtiene todos los movimientos recurrentes locales que aún no fueron subidos,
     * es decir, aquellos cuyo `remote_id` es null.
     *
     * Se utiliza en la fase de subida de la sincronización.
     *
     * @return Una lista de movimientos [MovRecur] pendientes de subir.
     */
    @Query("SELECT * FROM mov_recur WHERE remote_id IS NULL")
    suspend fun getPendingToUpload(): List<MovRecur>

    /**
     * Busca un movimiento recurrente local usando su ID remoto (PocketBase ID).
     * Se utiliza en la fase de fusión (merge) de la sincronización.
     *
     * @param remoteId El identificador de PocketBase del movimiento recurrente.
     * @return El movimiento [MovRecur] local que coincide, o `null`.
     */
    @Query("SELECT * FROM mov_recur WHERE remote_id = :remoteId LIMIT 1")
    suspend fun getByRemoteId(remoteId: String): MovRecur?

    /**
     * Obtiene todos los movimientos recurrentes que ya tienen un ID remoto asignado.
     * Esta lista se utiliza para comparar contra el servidor y detectar borrados remotos.
     *
     * @return Una lista de movimientos [MovRecur] sincronizados.
     */
    @Query("SELECT * FROM mov_recur WHERE remote_id IS NOT NULL")
    suspend fun getAllWithRemoteId(): List<MovRecur>

    /**
     * Asigna un identificador remoto (`remote_id`) a un movimiento recurrente
     * que fue previamente insertado localmente.
     *
     * @param localId ID de Room del movimiento recurrente.
     * @param remoteId ID asignado por PocketBase.
     */
    @Query("UPDATE mov_recur SET remote_id = :remoteId WHERE id = :localId")
    suspend fun attachRemoteId(localId: Int, remoteId: String)
}