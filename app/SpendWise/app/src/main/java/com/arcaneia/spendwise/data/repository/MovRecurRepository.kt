package com.arcaneia.spendwise.data.repository

import com.arcaneia.spendwise.data.dao.MovDao
import com.arcaneia.spendwise.data.dao.MovRecurDao
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.entity.MovRecur
import com.arcaneia.spendwise.utils.calculateNextDate
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

/**
 * Repositorio encargado de gestionar las operaciones relacionadas con los
 * movimientos recurrentes ([MovRecur]) y su generación automática como movimientos
 * normales ([Mov]).
 *
 * Este repositorio actúa como capa intermedia entre los DAOs y el resto de la
 * aplicación, encapsulando la lógica de renovación automática de movimientos
 * recurrentes, así como operaciones básicas de inserción, actualización y eliminación.
 *
 * @property movRecurDao DAO responsable de las operaciones sobre la tabla `mov_recur`.
 * @property movDao DAO responsable de las operaciones sobre la tabla `mov`.
 */
class MovRecurRepository(
    private val movRecurDao: MovRecurDao,
    private val movDao: MovDao
) {

    /**
     * Inserta un nuevo movimiento recurrente.
     *
     * @param movRecur Instancia de [MovRecur] a insertar.
     */
    suspend fun insert(movRecur: MovRecur) {
        movRecurDao.insert(movRecur)
    }

    /**
     * Elimina un movimiento recurrente específico.
     *
     * @param movRecur Instancia que se desea eliminar.
     */
    suspend fun delete(movRecur: MovRecur) {
        movRecurDao.delete(movRecur)
    }

    /**
     * Actualiza los datos de un movimiento recurrente existente.
     *
     * @param movRecur Instancia con los valores actualizados.
     */
    suspend fun update(movRecur: MovRecur) {
        movRecurDao.update(movRecur)
    }

    /**
     * Obtiene todos los movimientos recurrentes registrados en la base de datos.
     *
     * @return Un flujo con la lista de [MovRecur], ordenados por fecha de renovación.
     */
    fun getAllMovRecur(): Flow<List<MovRecur>> = movRecurDao.getAllMovRecur()

    /**
     * Procesa todas las renovaciones pendientes de movimientos recurrentes.
     *
     * Para cada movimiento recurrente cuya fecha de renovación (`data_rnv`)
     * sea menor o igual a la fecha actual:
     *
     * 1. Se crean nuevos movimientos ([Mov]) con la información correspondiente.
     * 2. Se insertan en la tabla `mov`.
     * 3. Se calcula la siguiente fecha de renovación basada en su periodicidad.
     * 4. Se actualiza el movimiento recurrente con la nueva fecha.
     *
     * Este proceso gestiona correctamente múltiples renovaciones atrasadas,
     * avanzando la fecha tantas veces como sea necesario.
     *
     * @return Una lista con todos los movimientos generados durante el proceso.
     */
    suspend fun processRenewals(): List<Mov> {

        val createdMovs = mutableListOf<Mov>()

        val dateComplete = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val today = LocalDate.now().toString()
        val renewals = movRecurDao.getMovsToRenew(today)

        for (renews in renewals) {

            var nextDate = renews.data_rnv
            val todayDate = LocalDate.now()

            while (!LocalDate.parse(nextDate).isAfter(todayDate)) {

                val newMov = Mov(
                    tipo = renews.tipo,
                    importe = renews.importe,
                    data_mov = dateComplete,
                    descricion = renews.nombre,
                    categoria_id = 1,
                    mov_recur_id = renews.id
                )
                movDao.insert(newMov)
                createdMovs.add(newMov)

                // Avanza a la siguiente fecha recurrente
                nextDate = calculateNextDate(nextDate, renews.periodicidade!!)
            }
            movRecurDao.update(renews.copy(data_rnv = nextDate))
        }
        return createdMovs
    }

    /**
     * Obtiene la cantidad de movimientos recurrentes que deben renovarse hoy.
     *
     * @return Número de entradas en `mov_recur` cuya fecha `data_rnv` es menor o igual a la fecha actual.
     */
    suspend fun getPendingRenewalsCount(): Int {
        val today = LocalDate.now().toString()
        return movRecurDao.getMovsToRenew(today).size
    }
}