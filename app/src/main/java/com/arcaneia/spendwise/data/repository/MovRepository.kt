package com.arcaneia.spendwise.data.repository

import com.arcaneia.spendwise.data.dao.MovDao
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.entity.MovWithCategory
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio encargado de gestionar todas las operaciones relacionadas con la entidad [Mov].
 *
 * Funciona como una capa intermedia entre el [MovDao] y los ViewModels, proporcionando
 * una API limpia y desacoplada para:
 *
 * - Insertar nuevos movimientos.
 * - Actualizar y eliminar movimientos existentes.
 * - Consultar información estadística como balance mensual, años disponibles y meses disponibles.
 * - Observar en tiempo real la lista completa de movimientos almacenados.
 *
 * El repositorio permite mantener una arquitectura más modular y facilita pruebas unitarias,
 * ya que aísla el acceso directo a Room detrás de esta abstracción.
 *
 * @property movDao DAO encargado de todas las operaciones sobre la tabla `mov`.
 */
class MovRepository(private val movDao: MovDao) {

    /**
     * Inserta un nuevo movimiento en la base de datos local.
     *
     * A diferencia de otras inserciones, devuelve explícitamente el ID autogenerado para permitir
     * que el ViewModel pueda asociarle posteriormente un `remote_id` cuando se sincronice
     * con PocketBase.
     *
     * @param mov Movimiento a insertar.
     * @return El ID generado por Room tras la inserción.
     */
    suspend fun insert(mov: Mov): Long {
        return movDao.insert(mov)
    }

    /**
     * Elimina un movimiento de la base de datos.
     *
     * @param mov Entidad [Mov] que se desea eliminar.
     */
    suspend fun delete(mov: Mov) {
        movDao.delete(mov)
    }

    /**
     * Actualiza un movimiento previamente existente en la base de datos.
     *
     * @param mov Movimiento con los valores actualizados.
     */
    suspend fun update(mov: Mov) {
        movDao.update(mov)
    }

    /**
     * Obtiene un movimiento por su ID local.
     *
     * Esta función es esencial para operaciones de actualización sincronizada, ya que permite
     * conservar el `remote_id` durante merges o sincronizaciones remotas.
     *
     * @param id Identificador local del movimiento.
     * @return La entidad [Mov] correspondiente, o `null` si no existe.
     */
    suspend fun getById(id: Int): Mov? {
        return movDao.getById(id)
    }

    /**
     * Devuelve un flujo reactivo con la lista completa de movimientos almacenados.
     *
     * Este flujo se actualiza automáticamente cada vez que la tabla `mov` cambia,
     * lo que permite que la UI sea completamente reactiva sin necesidad de refrescar manualmente.
     *
     * @return Un [Flow] que emite listas de [Mov].
     */
    fun getAllFlow(): Flow<List<Mov>> {
        return movDao.getAllFlow()
    }

    /**
     * Obtiene el balance del mes actual, calculado como:
     * **Ingresos − Gastos**.
     *
     * @return Un flujo con el balance actualizado automáticamente.
     */
    fun getBalanceMesActual(): Flow<Double> = movDao.getBalanceMesActual()

    /**
     * Obtiene la lista de años en los que existen movimientos registrados.
     *
     * @return Un flujo con una lista de strings correspondientes a los años (`"YYYY"`).
     */
    fun getYearsWithValues(): Flow<List<String>> = movDao.getYearsWithValues()

    /**
     * Obtiene los meses asociados a un año específico en los que existen movimientos.
     *
     * @param year Año en formato `"YYYY"`.
     * @return Un flujo con la lista de meses (`"MM"`).
     */
    fun getMonthsFromYear(year: String): Flow<List<String>> =
        movDao.getMonthsFromYear(year)

    /**
     * Obtiene todos los movimientos pertenecientes a un año y mes específicos,
     * incluyendo la información de la categoría mediante un JOIN.
     *
     * @param year Año en formato `"YYYY"`.
     * @param month Mes en formato `"MM"`.
     * @return Un flujo de listas de [MovWithCategory].
     */
    fun getMovementsForYearMonth(year: String, month: String): Flow<List<MovWithCategory>> {
        return movDao.getMovementsForYearMonth(year, month)
    }
}