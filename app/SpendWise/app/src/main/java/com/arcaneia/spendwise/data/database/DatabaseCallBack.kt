package com.arcaneia.spendwise.data.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class DatabaseCallBack(
    private val context: Context
) : RoomDatabase.Callback() {

    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
        db.execSQL(
            """
            INSERT OR IGNORE INTO categoria (id, nome, tipo)
            VALUES (1, 'Recurrente', '')
            """.trimIndent()
        )
    }
}