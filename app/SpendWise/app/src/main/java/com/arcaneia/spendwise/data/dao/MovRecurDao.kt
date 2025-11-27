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
 * recurrentes, así como obtener aquellos que deben renovarse según su fecha programada.
 */
@Dao
interface MovRecurDao {

    /**
     * Inserta un movimiento recurrente en la base de datos.
     * Si ya existe otro con el mismo ID, será reemplazado.
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
     * Obtiene todos los movimientos recurrentes ordenados por la fecha de renovación
     * (`data_rnv`) en orden ascendente.
     *
     * @return Un `Flow<List<MovRecur>>` que emite la lista completa cada vez que cambia la tabla.
     */
    @Query("SELECT * FROM mov_recur ORDER BY data_rnv ASC")
    fun getAllMovRecur(): Flow<List<MovRecur>>

    /**
     * Obtiene todos los movimientos recurrentes cuya fecha de renovación sea
     * anterior o igual a la fecha indicada.
     *
     * Esta función es útil para determinar qué movimientos deben renovarse en el día actual.
     *
     * @param today Fecha límite en formato `"YYYY-MM-DD"`.
     * @return Una lista con los movimientos recurrentes que deben renovarse.
     */
    @Query("SELECT * FROM mov_recur WHERE data_rnv <= :today")
    suspend fun getMovsToRenew(today: String): List<MovRecur>
}
