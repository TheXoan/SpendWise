package com.arcaneia.spendwise.data.backup

import android.content.Context
import android.net.Uri
import androidx.room.Room
import com.arcaneia.spendwise.data.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

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
class BackupManager(
    private val context: Context,
    private val dbName: String = "spendwise_db"
) {

    private val dbPath: File
        get() = context.getDatabasePath(dbName)

    private val databaseDir: File
        get() = dbPath.parentFile!!

    /**
     * Exporta los archivos de la base de datos (DB, SHM y WAL) hacia un archivo ZIP temporal.
     *
     * El ZIP se almacena dentro del directorio de caché de la aplicación,
     * en la carpeta `"backup_temp"`. Si un archivo ZIP previo existe, se reemplaza.
     *
     * @return Archivo `File` que apunta al ZIP generado.
     */
    suspend fun exportDatabaseToTempFile(): File = withContext(Dispatchers.IO) {

        val exportDir = File(context.cacheDir, "backup_temp")
        if (!exportDir.exists()) exportDir.mkdirs()

        val backupFile = File(exportDir, "database_backup.zip")
        if (backupFile.exists()) backupFile.delete()

        ZipOutputStream(backupFile.outputStream()).use { zip ->
            listOf(
                dbName,
                "$dbName-shm",
                "$dbName-wal"
            ).forEach { name ->
                val file = File(databaseDir, name)
                if (file.exists()) {
                    zip.putNextEntry(ZipEntry(name))
                    file.inputStream().copyTo(zip)
                    zip.closeEntry()
                }
            }
        }

        backupFile
    }

    /**
     * Escribe la copia de seguridad temporal en una ubicación elegida por el usuario
     * utilizando el **Storage Access Framework (SAF)**.
     *
     * @param uri URI proporcionada por el usuario donde se guardará el archivo ZIP.
     * @return `true` si la operación finaliza correctamente, `false` si ocurre algún error.
     */
    suspend fun exportWriteToUri(uri: Uri): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            val tempBackup = exportDatabaseToTempFile()

            context.contentResolver.openOutputStream(uri)?.use { output ->
                tempBackup.inputStream().use { input ->
                    input.copyTo(output)
                }
            }

            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
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
    suspend fun importFromUri(uri: Uri): Boolean = withContext(Dispatchers.IO) {
        try {
            // Cerrar la DB antes de reemplazar archivos para evitar corromper la base de datos
            Room.databaseBuilder(context,
                AppDatabase::class.java, dbName)
                .build()
                .close()

            context.contentResolver.openInputStream(uri)?.use { input ->
                ZipInputStream(input).use { zip ->
                    var entry = zip.nextEntry
                    while (entry != null) {
                        val outFile = File(databaseDir, entry.name)
                        outFile.outputStream().use { output ->
                            zip.copyTo(output)
                        }
                        entry = zip.nextEntry
                    }
                }
            }

            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}