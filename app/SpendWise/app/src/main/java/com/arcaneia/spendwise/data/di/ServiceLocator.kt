package com.arcaneia.spendwise.data.di

import android.content.Context
import com.arcaneia.spendwise.data.database.AppDatabase
import com.arcaneia.spendwise.data.repository.MovRecurRepository

object ServiceLocator {

    fun getMovRecurRepository(context: Context): MovRecurRepository {
        val db = AppDatabase.getDatabase(context)
        return MovRecurRepository(
            movRecurDao = db.movRecurDao(),
            movDao = db.movDao()
        )
    }
}