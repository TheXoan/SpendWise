package com.arcaneia.spendwise.data.dao

import androidx.room.*
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.entity.MovWithCategory
import kotlinx.coroutines.flow.Flow

/**
 * DAO (Data Access Object) responsable de gestionar todas las operaciones
 * relacionadas con la entidad [Mov], incluyendo:
 *
 * - Operaciones CRUD básicas.
 * - Consultas avanzadas orientadas a estadísticas.
 * - Integración completa con PocketBase para sincronización remota.
 * - Soporte para notificaciones de movimientos recurrentes.
 *
 * Este DAO constituye la principal capa de acceso a datos de los movimientos
 * simples dentro de la base de datos local Room.
 */
@Dao
interface MovDao {

    // ============================================================================
    // CRUD BASE
    // ============================================================================

    /**
     * Inserta un nuevo movimiento en la base de datos local.
     *
     * @param mov Instancia a insertar.
     * @return El ID autogenerado por Room.
     */
    @Insert
    suspend fun insert(mov: Mov): Long

    /**
     * Actualiza un movimiento existente.
     *
     * @param mov Movimiento modificado.
     */
    @Update
    suspend fun update(mov: Mov)

    /**
     * Elimina un movimiento.
     *
     * @param mov Instancia a eliminar.
     */
    @Delete
    suspend fun delete(mov: Mov)

    // ============================================================================
    // ESTADÍSTICAS Y CONSULTAS PARA LA UI
    // ============================================================================

    /**
     * Calcula el balance total del mes actual:
     *
     * **Ingresos − Gastos**, considerando únicamente movimientos cuyo
     * `data_mov` pertenezca al mes corriente.
     *
     * @return Un [Flow] con el valor del balance actualizado en tiempo real.
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
     * Obtiene la lista de años en los que existen movimientos registrados.
     *
     * @return Un [Flow] con años únicos ordenados de forma descendente.
     */
    @Query("SELECT DISTINCT strftime('%Y', data_mov) AS year FROM mov ORDER BY year DESC")
    fun getYearsWithValues(): Flow<List<String>>

    /**
     * Obtiene los meses que contienen movimientos para un año específico.
     *
     * @param year Año en formato YYYY.
     * @return Un [Flow] con meses únicos ordenados ascendentemente.
     */
    @Query("""
        SELECT DISTINCT strftime('%m', data_mov) AS month
        FROM mov
        WHERE strftime('%Y', data_mov) = :year
        ORDER BY month ASC
    """)
    fun getMonthsFromYear(year: String): Flow<List<String>>

    /**
     * Obtiene todos los movimientos del año y mes especificados,
     * acompañados del nombre de su categoría mediante JOIN.
     *
     * @param year Año objetivo.
     * @param month Mes objetivo.
     * @return [Flow] con movimientos enriquecidos con su categoría.
     */
    @Query("""
        SELECT mov.*, categoria.nome AS categoriaNome
        FROM mov
        INNER JOIN categoria ON mov.categoria_id = categoria.id
        WHERE strftime('%Y', mov.data_mov) = :year
          AND strftime('%m', mov.data_mov) = :month
        ORDER BY mov.data_mov DESC
    """)
    fun getMovementsForYearMonth(
        year: String,
        month: String
    ): Flow<List<MovWithCategory>>

    // ============================================================================
    // SINCRONIZACIÓN POCKETBASE
    // ============================================================================

    /**
     * Devuelve todos los movimientos que aún no han sido subidos al servidor,
     * es decir, aquellos cuyo `remote_id` es `NULL`.
     *
     * @return Lista de movimientos pendientes de sincronizar.
     */
    @Query("SELECT * FROM mov WHERE remote_id IS NULL")
    suspend fun getPendingToUpload(): List<Mov>

    /**
     * Asocia un ID remoto asignado por PocketBase a un movimiento local.
     *
     * @param localId ID local del movimiento.
     * @param remoteId ID remoto generado en el servidor.
     */
    @Query("UPDATE mov SET remote_id = :remoteId WHERE id = :localId")
    suspend fun attachRemoteId(localId: Int, remoteId: String)

    /**
     * Obtiene un movimiento local usando su ID remoto.
     *
     * @param remoteId Identificador remoto del movimiento.
     * @return El movimiento encontrado o `null`.
     */
    @Query("SELECT * FROM mov WHERE remote_id = :remoteId LIMIT 1")
    suspend fun getByRemoteId(remoteId: String): Mov?

    /**
     * Obtiene todos los movimientos que ya han sido sincronizados,
     * es decir, aquellos que tienen un `remote_id` asignado.
     *
     * @return Lista completa de movimientos sincronizados.
     */
    @Query("SELECT * FROM mov WHERE remote_id IS NOT NULL")
    suspend fun getAllWithRemoteId(): List<Mov>

    /**
     * Devuelve todos los movimientos locales sin filtros.
     *
     * Usado en sincronización y exportación.
     */
    @Query("SELECT * FROM mov")
    suspend fun getAll(): List<Mov>

    /**
     * Elimina un movimiento local cuyo `remote_id` coincide
     * con un registro eliminado en PocketBase.
     *
     * @param remoteId ID remoto a eliminar.
     */
    @Query("DELETE FROM mov WHERE remote_id = :remoteId")
    suspend fun deleteByRemoteId(remoteId: String)

    /**
     * Obtiene un movimiento por su ID local.
     */
    @Query("SELECT * FROM mov WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Mov?

    /**
     * Devuelve todos los movimientos como flujo reactivo.
     */
    @Query("SELECT * FROM mov")
    fun getAllFlow(): Flow<List<Mov>>

    // ============================================================================
    // NOTIFICACIONES DE MOVIMIENTOS RECURRENTES
    // ============================================================================

    /**
     * Obtiene todos los movimientos que aún no han sido notificados.
     *
     * Usado por [RenewMovsRecurWorker] para mostrar notificaciones locales.
     *
     * @return Lista de movimientos sin notificar.
     */
    @Query("SELECT * FROM mov WHERE notificado = 0 ORDER BY data_mov ASC")
    suspend fun getPendingNotifications(): List<Mov>

    /**
     * Marca un movimiento como notificado.
     *
     * @param id ID local del movimiento.
     */
    @Query("UPDATE mov SET notificado = 1 WHERE id = :id")
    suspend fun markAsNotified(id: Int)

    /**
     * Busca un movimiento por su `renew_hash`, usado para detectar duplicados
     * durante la sincronización de renovaciones recurrentes.
     *
     * @param hash Valor del hash único generado en la renovación.
     * @return Movimiento encontrado o `null`.
     */
    @Query("SELECT * FROM mov WHERE renew_hash = :hash LIMIT 1")
    suspend fun getByRenewHash(hash: String): Mov?
}