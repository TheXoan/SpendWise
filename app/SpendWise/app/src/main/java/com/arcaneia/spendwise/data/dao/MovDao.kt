package com.arcaneia.spendwise.data.dao

import androidx.room.*
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.entity.MovWithCategory
import kotlinx.coroutines.flow.Flow

/**
 * DAO (Data Access Object) encargado de gestionar las operaciones sobre la entidad [Mov]
 * y sus consultas relacionadas.
 *
 * Este DAO utiliza `Flow` para exponer datos reactivos que se actualizan automáticamente
 * al modificarse la tabla correspondiente, y funciones `suspend` para las operaciones
 * de sincronización (lectura/escritura) que no requieren reactividad.
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
     * al mes actual (comparando el formato 'YYYY-MM' local).
     *
     * @return Un [Flow] de [Double] que emite el balance actualizado cada vez que cambia la tabla.
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
     * Obtiene la lista de años (formato `"YYYY"`) para los cuales existen movimientos registrados.
     *
     * @return Un [Flow] que emite la lista de años como [String] en orden descendente.
     */
    @Query("SELECT DISTINCT strftime('%Y', data_mov) AS year FROM mov ORDER BY year DESC")
    fun getYearsWithValues(): Flow<List<String>>

    /**
     * Obtiene todos los meses (formato `"MM"`) que poseen movimientos dentro del año indicado.
     *
     * @param year Año en formato `"YYYY"` del cual se quieren obtener los meses.
     * @return Un [Flow] de [List] de [String] con los meses ordenados ascendentemente.
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
     * de la categoría (`categoria.nome`), mapeado en [MovWithCategory].
     *
     * Los resultados se devuelven ordenados por fecha descendente.
     *
     * @param year Año en formato `"YYYY"`.
     * @param month Mes en formato `"MM"`.
     * @return Un [Flow] que emite la lista completa de [MovWithCategory] para el periodo.
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

    /**
     * Obtiene todos los movimientos locales que aún no tienen un ID remoto asignado.
     * Esta función se utiliza durante el proceso de sincronización para identificar
     * los registros pendientes de subir al servidor.
     *
     * @return Lista de movimientos [Mov] con `remote_id` nulo.
     */
    @Query("SELECT * FROM mov WHERE remote_id IS NULL")
    suspend fun getPendingToUpload(): List<Mov>

    /**
     * Asigna un identificador remoto (ID de PocketBase) a un movimiento local existente
     * después de haber sido subido exitosamente al servidor.
     *
     * @param localId ID de Room del movimiento a actualizar.
     * @param remoteId ID de PocketBase que se debe adjuntar.
     */
    @Query("UPDATE mov SET remote_id = :remoteId WHERE id = :localId")
    suspend fun attachRemoteId(localId: Int, remoteId: String)

    /**
     * Busca un movimiento local usando su ID remoto.
     * Se utiliza durante la fusión (merge) para saber si un registro remoto ya existe localmente.
     *
     * @param remoteId ID de PocketBase del movimiento.
     * @return El movimiento [Mov] local que coincide, o `null`.
     */
    @Query("SELECT * FROM mov WHERE remote_id = :remoteId LIMIT 1")
    suspend fun getByRemoteId(remoteId: String): Mov?

    /**
     * Obtiene todos los movimientos locales que ya han sido sincronizados y tienen un ID remoto.
     * Esta lista se utiliza para comparar contra la lista de movimientos remotos y detectar
     * registros que fueron borrados en el servidor.
     *
     * @return Lista de movimientos [Mov] con un `remote_id` no nulo.
     */
    @Query("SELECT * FROM mov WHERE remote_id IS NOT NULL")
    suspend fun getAllWithRemoteId(): List<Mov>
}