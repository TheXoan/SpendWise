package com.arcaneia.spendwise.data.repository

import com.arcaneia.spendwise.data.dao.MovDao
import com.arcaneia.spendwise.data.entity.Mov
import kotlinx.coroutines.flow.Flow

class MovRepository (private val movDao: MovDao) {

    suspend fun insert(mov: Mov) {
        movDao.insert(mov)
    }

    suspend fun delete(mov: Mov) {
        movDao.insert(mov)
    }

    fun getBalanceMesActual(): Flow<Double> = movDao.getBalanceMesActual()
    fun getYearsWithValues(): Flow<List<String>> = movDao.getYearsWithValues()
    fun getMonthsFromYear(year: String): Flow<List<String>> = movDao.getMonthsFromYear(year)
    fun getMovementsForYearMonth(year: String, month: String): Flow<List<Mov>> = movDao.getMovementsForYearMonth(year, month)
}