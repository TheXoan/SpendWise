//[app](../../../index.md)/[com.arcaneia.spendwise.data.backup](../index.md)/[BackupManager](index.md)

# BackupManager

[androidJvm]\
class [BackupManager](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), dbName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html) = &quot;spendwise_db&quot;)

Clase encargada de gestionar las operaciones de **respaldo (backup)** y **restauración** de la base de datos de la aplicación.

Proporciona funciones para:

- 
   Exportar la base de datos en archivos temporales comprimidos en formato ZIP.
- 
   Guardar la copia de seguridad en una ubicación elegida por el usuario mediante SAF.
- 
   Importar y restaurar la base de datos desde un archivo ZIP previamente generado.

Esta clase opera en **corrutinas** utilizando `Dispatchers.IO`, ya que se realizan operaciones de lectura/escritura en disco.

## Constructors

| | |
|---|---|
| [BackupManager](-backup-manager.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), dbName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html) = &quot;spendwise_db&quot;) |

## Functions

| Name | Summary |
|---|---|
| [exportDatabaseToTempFile](export-database-to-temp-file.md) | [androidJvm]<br>suspend fun [exportDatabaseToTempFile](export-database-to-temp-file.md)(): [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)<br>Exporta los archivos de la base de datos (DB, SHM y WAL) hacia un archivo ZIP temporal. |
| [exportWriteToUri](export-write-to-uri.md) | [androidJvm]<br>suspend fun [exportWriteToUri](export-write-to-uri.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)<br>Escribe la copia de seguridad temporal en una ubicación elegida por el usuario utilizando el **Storage Access Framework (SAF)**. |
| [importFromUri](import-from-uri.md) | [androidJvm]<br>suspend fun [importFromUri](import-from-uri.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)<br>Importa y restaura la base de datos desde un archivo ZIP. |
