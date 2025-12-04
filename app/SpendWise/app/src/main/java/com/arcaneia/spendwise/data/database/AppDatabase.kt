package com.arcaneia.spendwise.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arcaneia.spendwise.data.dao.CategoriaDao
import com.arcaneia.spendwise.data.dao.MovDao
import com.arcaneia.spendwise.data.dao.MovRecurDao
import com.arcaneia.spendwise.data.entity.Categoria
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.entity.MovRecur

/**
 * Base de datos principal de la aplicación, implementada con Room.
 *
 * Aquí se registran todas las entidades y DAOs utilizados en la capa de persistencia.
 * La base de datos incluye:
 * - [Categoria]
 * - [Mov]
 * - [MovRecur]
 *
 * También aplica convertidores personalizados mediante [Converters] para transformar
 * tipos complejos a tipos compatibles con la base de datos.
 *
 * La base de datos utiliza un patrón **Singleton** para garantizar que solo exista
 * una única instancia activa durante el ciclo de vida de la aplicación.
 *
 * @see RoomDatabase
 */
@Database(
    entities = [Categoria::class, Mov::class, MovRecur::class],
    version = 9,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    /**
     * @return DAO encargado de gestionar operaciones relacionadas con la tabla `categoria`.
     */
    abstract fun categoriaDao(): CategoriaDao

    /**
     * @return DAO encargado de las operaciones sobre movimientos normales ([Mov]).
     */
    abstract fun movDao(): MovDao

    /**
     * @return DAO encargado de las operaciones sobre movimientos recurrentes ([MovRecur]).
     */
    abstract fun movRecurDao(): MovRecurDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Obtiene la instancia única de la base de datos.
         *
         * Si la instancia aún no existe, se crea utilizando `Room.databaseBuilder()`.
         * Se aplica:
         * - `fallbackToDestructiveMigration(true)` para reconstruir la BD en caso
         *   de cambios de versión sin migraciones definidas.
         * - Un `DatabaseCallBack` para operaciones adicionales durante su creación.
         *
         * @param context Contexto necesario para crear la base de datos.
         * @return La instancia única de [AppDatabase].
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "spendwise_db"
                    )
                        .fallbackToDestructiveMigration(true)
                        .addCallback(DatabaseCallBack(context))
                        .build()
                INSTANCE = instance
                instance
            }
        }

        /**
         * Limpia la instancia singleton, permitiendo que se vuelva a generar una nueva
         * cuando se invoque nuevamente [getDatabase].
         *
         * Esta función es útil en escenarios como restauraciones de backup donde
         * se requiere forzar la recreación de la base de datos.
         */
        fun clearInstance() {
            INSTANCE = null
        }
    }
}