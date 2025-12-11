package com.arcaneia.spendwise.data.database;

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
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&\u00a8\u0006\u000b"}, d2 = {"Lcom/arcaneia/spendwise/data/database/AppDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "categoriaDao", "Lcom/arcaneia/spendwise/data/dao/CategoriaDao;", "movDao", "Lcom/arcaneia/spendwise/data/dao/MovDao;", "movRecurDao", "Lcom/arcaneia/spendwise/data/dao/MovRecurDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.arcaneia.spendwise.data.entity.Categoria.class, com.arcaneia.spendwise.data.entity.Mov.class, com.arcaneia.spendwise.data.entity.MovRecur.class}, version = 10, exportSchema = false)
@androidx.room.TypeConverters(value = {com.arcaneia.spendwise.data.database.Converters.class})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.arcaneia.spendwise.data.database.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.arcaneia.spendwise.data.database.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    /**
     * @return DAO encargado de gestionar operaciones relacionadas con la tabla `categoria`.
     */
    @org.jetbrains.annotations.NotNull()
    public abstract com.arcaneia.spendwise.data.dao.CategoriaDao categoriaDao();
    
    /**
     * @return DAO encargado de las operaciones sobre movimientos normales ([Mov]).
     */
    @org.jetbrains.annotations.NotNull()
    public abstract com.arcaneia.spendwise.data.dao.MovDao movDao();
    
    /**
     * @return DAO encargado de las operaciones sobre movimientos recurrentes ([MovRecur]).
     */
    @org.jetbrains.annotations.NotNull()
    public abstract com.arcaneia.spendwise.data.dao.MovRecurDao movRecurDao();
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\nR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/arcaneia/spendwise/data/database/AppDatabase$Companion;", "", "<init>", "()V", "INSTANCE", "Lcom/arcaneia/spendwise/data/database/AppDatabase;", "getDatabase", "context", "Landroid/content/Context;", "clearInstance", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Obtiene la instancia única de la base de datos.
         *
         * Si la instancia aún no existe, se crea utilizando `Room.databaseBuilder()`.
         * Se aplica:
         * - `fallbackToDestructiveMigration(true)` para reconstruir la BD en caso
         *  de cambios de versión sin migraciones definidas.
         * - Un `DatabaseCallBack` para operaciones adicionales durante su creación.
         *
         * Esta función es segura para hilos múltiples gracias al uso de `synchronized`
         * y la anotación `@Volatile` en [INSTANCE].
         *
         * @param context Contexto necesario para crear la base de datos.
         * @return La instancia única de [AppDatabase].
         */
        @org.jetbrains.annotations.NotNull()
        public final com.arcaneia.spendwise.data.database.AppDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        /**
         * Limpia la instancia singleton, permitiendo que se vuelva a generar una nueva
         * cuando se invoque nuevamente [getDatabase].
         *
         * Esta función es útil en escenarios como restauraciones de backup donde
         * se requiere forzar la recreación de la base de datos.
         */
        public final void clearInstance() {
        }
    }
}