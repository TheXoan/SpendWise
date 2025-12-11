package com.arcaneia.spendwise.data.database;

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
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/arcaneia/spendwise/data/database/DatabaseCallBack;", "Landroidx/room/RoomDatabase$Callback;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "onOpen", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "app_debug"})
public final class DatabaseCallBack extends androidx.room.RoomDatabase.Callback {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    public DatabaseCallBack(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
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
    @java.lang.Override()
    public void onOpen(@org.jetbrains.annotations.NotNull()
    androidx.sqlite.db.SupportSQLiteDatabase db) {
    }
}