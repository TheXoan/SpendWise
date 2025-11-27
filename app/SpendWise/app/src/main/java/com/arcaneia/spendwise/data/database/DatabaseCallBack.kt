package com.arcaneia.spendwise.data.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Callback personalizado para la base de datos Room.
 *
 * Esta clase permite ejecutar operaciones automáticamente cuando la base de datos
 * se abre, utilizando el método [onOpen]. En este caso, se asegura de que exista
 * una categoría predeterminada con ID = 1 bajo el nombre `"Recurrente"`.
 *
 * Este callback se registra en la base de datos dentro de [AppDatabase] mediante:
 * ```
 * .addCallback(DatabaseCallBack(context))
 * ```
 *
 * @property context Contexto necesario para acceder a recursos o realizar futuras
 * operaciones adicionales dentro del callback.
 */
class DatabaseCallBack(
    private val context: Context
) : RoomDatabase.Callback() {

    /**
     * Se ejecuta automáticamente cada vez que la base de datos se abre.
     *
     * En este caso, inserta una categoría por defecto si no existe, utilizando
     * la sentencia SQL:
     * ```
     * INSERT OR IGNORE INTO categoria (id, nome, tipo)
     * VALUES (1, 'Recurrente', '')
     * ```
     *
     * Esto garantiza que la categoría "Recurrente" esté disponible para operaciones
     * que dependen de ella, sin sobrescribirla si ya existe.
     *
     * @param db Instancia de [SupportSQLiteDatabase] que permite ejecutar sentencias SQL.
     */
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