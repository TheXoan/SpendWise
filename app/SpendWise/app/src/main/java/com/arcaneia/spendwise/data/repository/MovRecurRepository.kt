package com.arcaneia.spendwise.data.repository

import com.arcaneia.spendwise.data.dao.MovRecurDao
import com.arcaneia.spendwise.data.entity.MovRecur
import kotlinx.coroutines.flow.Flow

class MovRecurRepository(private val movRecurDao: MovRecurDao) {

    suspend fun insert(movRecur: MovRecur) {
        movRecurDao.insert(movRecur)
    }

    suspend fun delete(movRecur: MovRecur) {
        movRecurDao.delete(movRecur)
    }

    fun getAllMovRecur(): Flow<List<MovRecur>> = movRecurDao.getAllMovRecur()
}