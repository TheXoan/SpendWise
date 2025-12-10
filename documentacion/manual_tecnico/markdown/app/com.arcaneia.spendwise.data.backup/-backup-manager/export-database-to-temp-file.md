//[app](../../../index.md)/[com.arcaneia.spendwise.data.backup](../index.md)/[BackupManager](index.md)/[exportDatabaseToTempFile](export-database-to-temp-file.md)

# exportDatabaseToTempFile

[androidJvm]\
suspend fun [exportDatabaseToTempFile](export-database-to-temp-file.md)(): [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)

Exporta los archivos de la base de datos (DB, SHM y WAL) hacia un archivo ZIP temporal.

El ZIP se almacena dentro del directorio de caché de la aplicación, en la carpeta `"backup_temp"`. Si un archivo ZIP previo existe, se reemplaza.

#### Return

Archivo `File` que apunta al ZIP generado.
