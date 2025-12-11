package com.arcaneia.spendwise.data.backup;

/**
 * Clase encargada de gestionar las operaciones de **respaldo (backup)** y
 * **restauración** de la base de datos de la aplicación.
 *
 * Proporciona funciones para:
 * - Exportar la base de datos en archivos temporales comprimidos en formato ZIP.
 * - Guardar la copia de seguridad en una ubicación elegida por el usuario mediante SAF.
 * - Importar y restaurar la base de datos desde un archivo ZIP previamente generado.
 *
 * Esta clase opera en **corrutinas** utilizando `Dispatchers.IO`, ya que se realizan
 * operaciones de lectura/escritura en disco.
 *
 * @property context Contexto necesario para acceder al sistema de archivos y resolver URIs.
 * @property dbName Nombre del archivo de base de datos de Room. Por defecto `"spendwise_db"`.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u000e\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\t8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lcom/arcaneia/spendwise/data/backup/BackupManager;", "", "context", "Landroid/content/Context;", "dbName", "", "<init>", "(Landroid/content/Context;Ljava/lang/String;)V", "dbPath", "Ljava/io/File;", "getDbPath", "()Ljava/io/File;", "databaseDir", "getDatabaseDir", "exportDatabaseToTempFile", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "exportWriteToUri", "", "uri", "Landroid/net/Uri;", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "importFromUri", "app_debug"})
public final class BackupManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String dbName = null;
    
    public BackupManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String dbName) {
        super();
    }
    
    private final java.io.File getDbPath() {
        return null;
    }
    
    private final java.io.File getDatabaseDir() {
        return null;
    }
    
    /**
     * Exporta los archivos de la base de datos (DB, SHM y WAL) hacia un archivo ZIP temporal.
     *
     * El ZIP se almacena dentro del directorio de caché de la aplicación,
     * en la carpeta `"backup_temp"`. Si un archivo ZIP previo existe, se reemplaza.
     *
     * @return Archivo `File` que apunta al ZIP generado.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object exportDatabaseToTempFile(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    /**
     * Escribe la copia de seguridad temporal en una ubicación elegida por el usuario
     * utilizando el **Storage Access Framework (SAF)**.
     *
     * @param uri URI proporcionada por el usuario donde se guardará el archivo ZIP.
     * @return `true` si la operación finaliza correctamente, `false` si ocurre algún error.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object exportWriteToUri(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    /**
     * Importa y restaura la base de datos desde un archivo ZIP.
     *
     * Antes de realizar la restauración, la base de datos de Room se cierra para evitar corrupción.
     * Luego, los archivos incluidos en el ZIP se extraen y reemplazan la base de datos actual.
     *
     * @param uri URI del archivo ZIP desde el cual se restaurará la base de datos.
     * @return `true` si la extracción y restauración se realizan correctamente, `false` si ocurre alguna excepción.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object importFromUri(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
}