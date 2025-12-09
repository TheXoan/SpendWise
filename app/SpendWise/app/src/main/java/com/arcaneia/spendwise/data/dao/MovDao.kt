package com.arcaneia.spendwise.data.dao

import androidx.room.*
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.entity.MovWithCategory
import kotlinx.coroutines.flow.Flow

/**
 * DAO (Data Access Object) encargado de gestionar las operaciones sobre la entidad [Mov]
 * y sus consultas relacionadas.
 *
 * Esta interfaz define las operaciones CRUD básicas para los movimientos simples,
 * así como consultas avanzadas orientadas a:
 *
 * - estadísticas (balance mensual)
 * - filtros por año y mes
 * - acceso reactivo mediante `Flow`
 * - soporte para sincronización con PocketBase
 *
 * Las consultas que exponen `Flow` permiten que la UI reaccione automáticamente
 * ante cualquier cambio en la tabla `mov`, mientras que las funciones marcadas
 * como `suspend` están pensadas para operaciones de sincronización o trabajo
 * en segundo plano.
 */
@Dao
interface MovDao {

    /**
     * Inserta un nuevo movimiento en la base de datos.
     *
     * @param mov Instancia del movimiento a insertar.
     * @return ID autogenerado por Room.
     */
    @Insert
    suspend fun insert(mov: Mov): Long

    /**
     * Actualiza los datos de un movimiento existente.
     *
     * @param mov Objeto [Mov] con los datos actualizados.
     */
    @Update
    suspend fun update(mov: Mov)

    /**
     * Elimina un movimiento de la base de datos.
     *
     * @param mov Movimiento que se desea eliminar.
     */
    @Delete
    suspend fun delete(mov: Mov)

    /**
     * Obtiene el balance del mes actual (ingresos − gastos).
     *
     * Se filtran los movimientos usando `strftime('%Y-%m', data_mov)` para limitar
     * el cálculo al mes actual según la hora local del dispositivo.
     *
     * @return Un [Flow] que emite automáticamente el balance actualizado.
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
     * Recupera todos los **años** en los que existen movimientos.
     *
     * @return Un [Flow] con los años ordenados de forma descendente.
     */
    @Query("SELECT DISTINCT strftime('%Y', data_mov) AS year FROM mov ORDER BY year DESC")
    fun getYearsWithValues(): Flow<List<String>>

    /**
     * Recupera todos los **meses** (formato MM) que contienen movimientos dentro de un año.
     *
     * @param year Año (YYYY).
     * @return Un [Flow] con los meses ordenados ascendentemente.
     */
    @Query("""
        SELECT DISTINCT strftime('%m', data_mov) AS month
        FROM mov
        WHERE strftime('%Y', data_mov) = :year
        ORDER BY month ASC
    """)
    fun getMonthsFromYear(year: String): Flow<List<String>>

    /**
     * Obtiene los movimientos pertenecientes a un año y mes específicos,
     * junto con el nombre de su categoría asociada (JOIN con tabla `categoria`).
     *
     * Los resultados se devuelven ordenados por fecha descendente.
     *
     * @param year Año a filtrar.
     * @param month Mes a filtrar.
     * @return Un [Flow] con la lista de movimientos enriquecidos.
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

    // -------------------------------------------------------------------------
    // 🔥 FUNCIONES ESPECIALIZADAS PARA SINCRONIZACIÓN (PocketBase)
    // -------------------------------------------------------------------------

    /**
     * Devuelve todos los movimientos locales aún no sincronizados con el servidor,
     * es decir, aquellos cuyo `remote_id` es null.
     *
     * @return Lista de movimientos pendientes de sincronización.
     */
    @Query("SELECT * FROM mov WHERE remote_id IS NULL")
    suspend fun getPendingToUpload(): List<Mov>

    /**
     * Asocia un ID remoto obtenido del servidor al movimiento local correspondiente.
     *
     * @param localId ID local del movimiento.
     * @param remoteId ID remoto asignado por PocketBase.
     */
    @Query("UPDATE mov SET remote_id = :remoteId WHERE id = :localId")
    suspend fun attachRemoteId(localId: Int, remoteId: String)

    /**
     * Busca un movimiento local por su `remote_id`.
     *
     * @param remoteId Identificador remoto.
     * @return El movimiento encontrado o `null`.
     */
    @Query("SELECT * FROM mov WHERE remote_id = :remoteId LIMIT 1")
    suspend fun getByRemoteId(remoteId: String): Mov?

    /**
     * Devuelve todos los movimientos que ya tienen asignado un `remote_id`,
     * es decir, aquellos que han sido previamente sincronizados.
     *
     * @return Lista de movimientos sincronizados.
     */
    @Query("SELECT * FROM mov WHERE remote_id IS NOT NULL")
    suspend fun getAllWithRemoteId(): List<Mov>

    /**
     * Recupera todos los movimientos locales sin filtrar.
     *
     * Útil para depuración, exportaciones o sincronización completa.
     */
    @Query("SELECT * FROM mov")
    suspend fun getAll(): List<Mov>

    /**
     * Elimina un movimiento local usando su ID remoto.
     *
     * Usado para reflejar borrados remotos en PocketBase.
     *
     * @param remoteId ID remoto del movimiento a eliminar.
     */
    @Query("DELETE FROM mov WHERE remote_id = :remoteId")
    suspend fun deleteByRemoteId(remoteId: String)

    /**
     * Recupera un movimiento por su ID local.
     *
     * @param id Identificador local del movimiento.
     * @return El movimiento encontrado o `null`.
     */
    @Query("SELECT * FROM mov WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Mov?

    /**
     * Recupera *todos* los movimientos en un flujo reactivo.
     *
     * @return Un [Flow] que emite la lista completa al cambiar la tabla.
     */
    @Query("SELECT * FROM mov")
    fun getAllFlow(): Flow<List<Mov>>
}