package com.arcaneia.spendwise.data.dao

import androidx.room.*
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.entity.MovWithCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface MovDao {

    @Insert
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
    WHERE strftime('%Y-%m', data_mov) = strftime('%Y-%m', 'now', 'localtime')
    """)
    fun getBalanceMesActual(): Flow<Double>

    // Obtener los años que tienen algún movimiento
    @Query("SELECT DISTINCT strftime('%Y', data_mov) AS year FROM mov ORDER BY year DESC")
    fun getYearsWithValues(): Flow<List<String>>

    // Obtener todos los meses que tienen algún movimiento de un año en concreto
    @Query("""
        SELECT DISTINCT strftime('%m', data_mov) AS month
        FROM mov
        WHERE strftime('%Y', data_mov) = :year
        ORDER BY month ASC
    """)
    fun getMonthsFromYear(year: String): Flow<List<String>>

    // Obtener todos los movimientos filtrados por mes y año
    @Query("""
    SELECT mov.*, categoria.nome AS categoriaNome
    FROM mov
    INNER JOIN categoria ON mov.categoria_id = categoria.id
    WHERE strftime('%Y', mov.data_mov) = :year
      AND strftime('%m', mov.data_mov) = :month
    ORDER BY mov.data_mov DESC
""")
    fun getMovementsForYearMonth(year: String, month: String): Flow<List<MovWithCategory>>


}