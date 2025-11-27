package com.arcaneia.spendwise.data.repository

import com.arcaneia.spendwise.data.dao.MovDao
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.entity.MovWithCategory
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio encargado de gestionar las operaciones relacionadas con los
 * movimientos económicos ([Mov]) y sus consultas asociadas.
 *
 * Actúa como capa intermedia entre el [MovDao] y los ViewModels, proporcionando
 * una API clara para insertar, actualizar, eliminar y consultar información
 * de movimientos, incluyendo estadísticas como balances y agrupaciones por fechas.
 *
 * @property movDao DAO responsable de las operaciones sobre la tabla `mov`.
 */
class MovRepository(private val movDao: MovDao) {

    /**
     * Inserta un nuevo movimiento en la base de datos.
     *
     * @param mov Instancia del movimiento a insertar.
     */
    suspend fun insert(mov: Mov) {
        movDao.insert(mov)
    }

    /**
     * Elimina un movimiento existente.
     *
     * @param mov Instancia del movimiento que se desea eliminar.
     */
    suspend fun delete(mov: Mov) {
        movDao.delete(mov)
    }

    /**
     * Actualiza los datos de un movimiento existente.
     *
     * @param mov Instancia con los datos modificados.
     */
    suspend fun update(mov: Mov) {
        movDao.update(mov)
    }

    /**
     * Obtiene el balance del mes actual:
     *
     * **Ingresos totales − Gastos totales**
     *
     * @return Un flujo con el balance actualizado cada vez que se modifica la tabla `mov`.
     */
    fun getBalanceMesActual(): Flow<Double> = movDao.getBalanceMesActual()

    /**
     * Obtiene la lista de años (`"YYYY"`) en los que existen movimientos.
     *
     * @return Un flujo que emite la lista de años ordenada de forma descendente.
     */
    fun getYearsWithValues(): Flow<List<String>> = movDao.getYearsWithValues()

    /**
     * Obtiene todos los meses (`"MM"`) en los que existen movimientos
     * dentro de un año específico.
     *
     * @param year Año en formato `"YYYY"`.
     * @return Un flujo con la lista de meses en orden ascendente.
     */
    fun getMonthsFromYear(year: String): Flow<List<String>> =
        movDao.getMonthsFromYear(year)

    /**
     * Obtiene todos los movimientos de un mes y año concretos, junto con
     * el nombre de su categoría asociada.
     *
     * Este método devuelve una lista de [MovWithCategory] proveniente de una
     * consulta `JOIN` entre `mov` y `categoria`.
     *
     * @param year Año en formato `"YYYY"`.
     * @param month Mes en formato `"MM"`.
     * @return Un flujo con la lista de movimientos enriquecidos con su categoría.
     */
    fun getMovementsForYearMonth(year: String, month: String): Flow<List<MovWithCategory>> {
        return movDao.getMovementsForYearMonth(year, month)
    }
}