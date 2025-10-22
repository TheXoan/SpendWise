package com.arcaneia.spendwise.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arcaneia.spendwise.data.entity.MovRecur
import kotlinx.coroutines.flow.Flow

@Dao
interface MovRecurDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movRecur: MovRecur): Long

    @Delete
    suspend fun delete(movRecur: MovRecur)

}
