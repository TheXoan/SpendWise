package com.arcaneia.spendwise.data.dao

import androidx.room.*
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.entity.MovWithCategory
import kotlinx.coroutines.flow.Flow

/**
 * DAO (Data Access Object) encargado de gestionar las operaciones sobre la entidad [Mov]
 * y sus consultas relacionadas, incluyendo obtención de balances, años, meses y
 * movimientos combinados con categoría.
 *
 * Este DAO utiliza `Flow` para exponer datos reactivos que se actualizan automáticamente
 * al modificarse la tabla correspondiente.
 */
@Dao
interface MovDao {

    /**
     * Inserta un nuevo movimiento en la base de datos.
     *
     * @param mov Instancia del movimiento a insertar.
     * @return El ID autogenerado del movimiento recién insertado.
     */
    @Insert
    suspend fun insert(mov: Mov): Long

    /**
     * Actualiza los datos de un movimiento existente.
     *
     * @param mov Objeto [Mov] con los datos modificados.
     */
    @Update
    suspend fun update(mov: Mov)

    /**
     * Elimina un movimiento de la base de datos.
     *
     * @param mov Instancia de [Mov] que se desea eliminar.
     */
    @Delete
    suspend fun delete(mov: Mov)

    /**
     * Obtiene el balance del mes actual, calculado como:
     *
     * **Ingresos totales − Gastos totales**
     *
     * Solo se tienen en cuenta los movimientos cuyo campo `data_mov` pertenece
     * al mes actual.
     *
     * @return Un `Flow<Double>` que emite el balance actualizado cada vez que cambia la tabla.
     */
    @Query("""
    SELECT 
        (IFNULL(SUM(CASE WHEN tipo = 'INGRESO' THEN importe END), 0) -
         IFNULL(SUM(CASE WHEN tipo = 'GASTO' THEN importe END), 0))
    FROM mov
    WHERE strftime('%Y-%m', data_mov) = strftime('%Y-%m', 'now', 'localtime')
    """)
    fun getBalanceMesActual(): Flow<Double>

    /**
     * Obtiene la lista de años (formato `"YYYY"`) para los cuales existen movimientos.
     *
     * @return Un flujo que emite la lista de años en orden descendente.
     */
    @Query("SELECT DISTINCT strftime('%Y', data_mov) AS year FROM mov ORDER BY year DESC")
    fun getYearsWithValues(): Flow<List<String>>

    /**
     * Obtiene todos los meses (formato `"MM"`) que poseen movimientos dentro del año indicado.
     *
     * @param year Año en formato `"YYYY"` del cual se quieren obtener los meses.
     * @return Un `Flow<List<String>>` con los meses ordenados ascendentemente.
     */
    @Query("""
        SELECT DISTINCT strftime('%m', data_mov) AS month
        FROM mov
        WHERE strftime('%Y', data_mov) = :year
        ORDER BY month ASC
    """)
    fun getMonthsFromYear(year: String): Flow<List<String>>

    /**
     * Obtiene todos los movimientos correspondientes a un mes y año específico,
     * junto con los datos de su categoría asociada.
     *
     * Realiza un `INNER JOIN` con la tabla `categoria` para obtener el nombre
     * de la categoría, mapeado en [MovWithCategory].
     *
     * Los resultados se devuelven ordenados por fecha descendente.
     *
     * @param year Año en formato `"YYYY"`.
     * @param month Mes en formato `"MM"`.
     * @return Un flujo que emite la lista completa de movimientos con categoría.
     */
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