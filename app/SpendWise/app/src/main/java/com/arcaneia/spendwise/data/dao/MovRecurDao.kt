package com.arcaneia.spendwise.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.arcaneia.spendwise.data.entity.MovRecur
import kotlinx.coroutines.flow.Flow

@Dao
interface MovRecurDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movRecur: MovRecur): Long

    @Delete
    suspend fun delete(movRecur: MovRecur)

    @Update
    suspend fun update(movRecur: MovRecur)

    @Query("SELECT * FROM mov_recur ORDER BY data_rnv ASC")
    fun getAllMovRecur(): Flow<List<MovRecur>>

    @Query("SELECT * FROM mov_recur WHERE data_rnv <= :today")
    suspend fun getMovsToRenew(today: String): List<MovRecur>
}