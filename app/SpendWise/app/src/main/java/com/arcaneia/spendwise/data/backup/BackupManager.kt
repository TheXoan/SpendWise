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

class BackupManager(
    private val context: Context,
    private val dbName: String = "spendwise_db"
) {

    private val dbPath: File
        get() = context.getDatabasePath(dbName)

    private val databaseDir: File
        get() = dbPath.parentFile!!

    /**
     * Exporta la base de datos a un ZIP
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
     * Guarda la exportación en una ubicación elegida por el usuario con el SAF.
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
     * Restaura la base de datos desde un ZIP.
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