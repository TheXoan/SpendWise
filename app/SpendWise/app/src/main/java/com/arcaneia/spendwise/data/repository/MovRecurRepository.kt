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

class MovRecurRepository(
    private val movRecurDao: MovRecurDao,
    private val movDao: MovDao
) {

    suspend fun insert(movRecur: MovRecur) {
        movRecurDao.insert(movRecur)
    }

    suspend fun delete(movRecur: MovRecur) {
        movRecurDao.delete(movRecur)
    }

    suspend fun update(movRecur: MovRecur) {
        movRecurDao.update(movRecur)
    }

    fun getAllMovRecur(): Flow<List<MovRecur>> = movRecurDao.getAllMovRecur()

    suspend fun processRenewals(): List<Mov> {

        val createdMovs = mutableListOf<Mov>()

        val dateComplete = SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault()).format(Date())
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

    suspend fun getPendingRenewalsCount(): Int {
        val today = LocalDate.now().toString()
        return movRecurDao.getMovsToRenew(today).size
    }
}