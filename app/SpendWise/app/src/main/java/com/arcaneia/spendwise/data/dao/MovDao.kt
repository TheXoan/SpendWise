package com.arcaneia.spendwise.data.dao

import androidx.room.*
import com.arcaneia.spendwise.data.entity.Mov
import kotlinx.coroutines.flow.Flow

@Dao
interface MovDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mov: Mov): Long

    @Update
    suspend fun update(mov: Mov)

    @Delete
    suspend fun delete(mov: Mov)

    // Balance ingresos/gastos del mes actual
    @Query("""
        SELECT 
            (IFNULL(SUM(CASE WHEN tipo = 'ingreso' THEN importe END), 0) -
             IFNULL(SUM(CASE WHEN tipo = 'gasto' THEN importe END), 0))
        FROM mov
        WHERE strftime('%Y-%m', datetime(data_mov / 1000, 'unixepoch')) = 
              strftime('%Y-%m', 'now', 'localtime')
    """)
    fun getBalanceMesActual(): Flow<Double>
}