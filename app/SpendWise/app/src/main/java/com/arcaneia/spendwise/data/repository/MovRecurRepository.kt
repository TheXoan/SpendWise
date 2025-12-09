package com.arcaneia.spendwise.data.repository

import android.content.Context
import com.arcaneia.spendwise.apis.data.model.MovRecurRemoteDataSource
import com.arcaneia.spendwise.data.dao.MovDao
import com.arcaneia.spendwise.data.dao.MovRecurDao
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.entity.MovRecur
import com.arcaneia.spendwise.utils.calculateNextDate
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

/**
 * Repositorio encargado de gestionar los **movimientos recurrentes** y su conversión
 * automática a movimientos simples.
 *
 * Este componente centraliza:
 *
 * ---
 * ### 🔄 **Renovación automática de movimientos recurrentes**
 *
 * Para cada registro de [MovRecur] cuya fecha `data_rnv` sea menor o igual a la fecha actual:
 *
 * 1. Se generan uno o varios [Mov] locales según corresponda.
 * 2. Los movimientos generan un **renew_hash determinista**, lo cual permite:
 *    - Evitar duplicados entre dispositivos durante la sincronización.
 *    - Garantizar que cada renovación sea única y rastreable.
 * 3. Se actualiza `data_rnv` avanzando tantas veces como sea necesario mediante `calculateNextDate()`.
 * 4. Se actualiza el movimiento recurrente tanto **localmente** como en **PocketBase** (si tiene `remote_id`).
 *
 * ---
 * ### 🌐 **Sincronización remota**
 *
 * Tras actualizar la fecha de renovación, si el movimiento recurrente tiene un ID remoto,
 * se envía la actualización a PocketBase mediante [MovRecurRemoteDataSource].
 *
 * Si la actualización remota falla (por ejemplo, sin conexión), el estado local quedará corregido
 * y la próxima sincronización lo alineará con el servidor.
 *
 * ---
 * ### 🧩 Dependencias
 *
 * @property movRecurDao DAO para acceder a la tabla de movimientos recurrentes.
 * @property movDao DAO para insertar los movimientos simples generados.
 * @property appContext Contexto usado para acceder al DataSource remoto.
 *
 * ---
 * Este repositorio **no** dispara notificaciones: eso es responsabilidad
 * del `RenewMovsRecurWorker`, que consume el resultado de `processRenewals()`.
 */
class MovRecurRepository(
    private val movRecurDao: MovRecurDao,
    private val movDao: MovDao,
    private val appContext: Context
) {

    /**
     * Inserta un movimiento recurrente en la base de datos local.
     */
    suspend fun insert(movRecur: MovRecur) {
        movRecurDao.insert(movRecur)
    }

    /**
     * Elimina un movimiento recurrente local.
     */
    suspend fun delete(movRecur: MovRecur) {
        movRecurDao.delete(movRecur)
    }

    /**
     * Actualiza el contenido de un movimiento recurrente local.
     */
    suspend fun update(movRecur: MovRecur) {
        movRecurDao.update(movRecur)
    }

    /**
     * Devuelve un flujo reactivo con todos los movimientos recurrentes
     * almacenados en la base de datos.
     */
    fun getAllMovRecur(): Flow<List<MovRecur>> = movRecurDao.getAllMovRecur()

    /**
     * Procesa todas las renovaciones pendientes de movimientos recurrentes.
     *
     * Flujo completo:
     *
     * 1. Obtiene todos los `MovRecur` cuya `data_rnv` sea ≤ hoy.
     * 2. Por cada uno:
     *    - Genera movimientos simples ([Mov]) con fecha actual.
     *    - Crea un **renew_hash determinista**, único por fecha de renovación.
     *    - Inserta esos movimientos simples en la base local.
     *    - Avanza `data_rnv` tantas veces como sea necesario.
     *    - Actualiza el `MovRecur` en local.
     *    - Si tiene `remote_id`, actualiza también el registro en PocketBase.
     *
     * 3. Devuelve una lista con todos los movimientos generados.
     *
     * Este método **no notifica** al usuario.
     * La notificación se realiza posteriormente por el `RenewMovsRecurWorker`.
     *
     * @return Lista de movimientos simples creados durante el proceso.
     */
    suspend fun processRenewals(): List<Mov> {

        val createdMovs = mutableListOf<Mov>()
        val today = LocalDate.now().toString()
        val nowFullDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

        val renewals = movRecurDao.getMovsToRenew(today)

        for (recur in renewals) {

            var nextDate = recur.data_rnv
            val todayDate = LocalDate.now()

            while (!LocalDate.parse(nextDate).isAfter(todayDate)) {

                // Hash determinista por fecha, evitando duplicados entre dispositivos
                val renewHash = "RENEW_${recur.id}_${nextDate}"

                val newMov = Mov(
                    tipo = recur.tipo,
                    importe = recur.importe,
                    descricion = recur.nome,
                    data_mov = nowFullDate,
                    categoria_id = 1, // Categoría "Recurrente"
                    mov_recur_id = recur.id,
                    renew_hash = renewHash
                )

                movDao.insert(newMov)
                createdMovs.add(newMov)

                nextDate = calculateNextDate(nextDate, recur.periodicidade!!)
            }

            // Actualizar localmente el MovRecur
            val updated = recur.copy(data_rnv = nextDate)
            movRecurDao.update(updated)

            // Actualizar remoto si existe en PocketBase
            updated.remote_id?.let { remoteId ->

                try {
                    MovRecurRemoteDataSource(appContext).update(
                        movRecurPBId = remoteId,
                        movRecur = updated
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                    // PocketBase se corregirá automáticamente en la siguiente sync
                }
            }
        }

        return createdMovs
    }

    /**
     * Devuelve cuántos movimientos recurrentes deberían renovarse hoy.
     */
    suspend fun getPendingRenewalsCount(): Int {
        val today = LocalDate.now().toString()
        return movRecurDao.getMovsToRenew(today).size
    }
}