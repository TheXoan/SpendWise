package com.arcaneia.spendwise.data.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcaneia.spendwise.apis.data.model.MovRecurRemoteDataSource
import com.arcaneia.spendwise.data.dao.MovRecurDao
import com.arcaneia.spendwise.data.entity.MovRecur
import com.arcaneia.spendwise.data.repository.MovRecurRepository
import com.arcaneia.spendwise.data.datastore.TokenDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar los **movimientos recurrentes** (`MovRecur`)
 * aplicando un enfoque **offline-first con sincronización instantánea**.
 *
 * La lógica principal del ViewModel es:
 *
 * - Aplicar todos los cambios inmediatamente en la base de datos local (Room).
 * - Sincronizar la operación con PocketBase en segundo plano.
 * - Mantener un `StateFlow` con la lista de movimientos recurrentes para la UI.
 *
 * De este modo, la interfaz se mantiene fluida y consistente incluso en modo offline.
 *
 * ### Flujo de sincronización aplicado:
 * - **Insert:** local → remoto → actualización con `remote_id`.
 * - **Update:** local → remoto (solo si tiene `remote_id`).
 * - **Delete:** remoto → local.
 *
 * @property repository Repositorio que expone consultas reactivas (`Flow`) para movimientos recurrentes.
 * @property remoteDataSource Cliente remoto encargado de comunicarse con PocketBase.
 * @property dao DAO directo para operaciones puntuales sobre Room.
 * @property appContext Contexto necesario para obtener el `userId` desde `TokenDataStore`.
 */
class MovRecurViewModel(
    private val repository: MovRecurRepository,
    private val remoteDataSource: MovRecurRemoteDataSource,
    private val dao: MovRecurDao,
    private val appContext: Context
) : ViewModel() {

    /**
     * Flujo interno con la lista de movimientos recurrentes almacenados en Room.
     */
    private val _movRecur = MutableStateFlow<List<MovRecur>>(emptyList())

    /**
     * Flujo observable por la UI que expone la lista de movimientos recurrentes.
     */
    val movRecur: StateFlow<List<MovRecur>> = _movRecur

    init {
        // Observación continua de los movimientos recurrentes en Room
        viewModelScope.launch {
            repository.getAllMovRecur().collect {
                _movRecur.value = it
            }
        }
    }

    // -------------------------------------------------------------------------
    // INSERT (local → remoto) offline-first
    // -------------------------------------------------------------------------

    /**
     * Inserta un movimiento recurrente **localmente de forma inmediata**
     * y luego intenta sincronizarlo con el servidor PocketBase.
     *
     * Flujo:
     * 1. Se inserta en Room.
     * 2. Se crea el registro en PocketBase.
     * 3. Se actualiza el registro local con su `remote_id`.
     *
     * @param m Movimiento recurrente a insertar.
     */
    fun insert(m: MovRecur) = viewModelScope.launch {

        // 1) INSERT LOCAL
        val localId = dao.insert(m).toInt()

        try {
            val userId = TokenDataStore.getUserId(appContext).first()!!

            // 2) Crear en remoto
            val created = remoteDataSource.create(userId, m)

            // 3) Guardar remote_id en local
            dao.getById(localId)?.let { saved ->
                dao.update(saved.copy(remote_id = created.id))
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // -------------------------------------------------------------------------
    // UPDATE (local → remoto)
    // -------------------------------------------------------------------------

    /**
     * Actualiza un movimiento recurrente tanto en la base de datos local como en PocketBase.
     *
     * - Primero se obtiene el registro actual para preservar el `remote_id`.
     * - Se actualiza en Room.
     * - Si ya estaba sincronizado, se aplica la actualización en el servidor.
     *
     * @param m Movimiento recurrente con los valores actualizados.
     */
    fun update(m: MovRecur) = viewModelScope.launch {

        // Obtener original (preservar remote_id)
        val saved = dao.getById(m.id) ?: return@launch

        val toSave = saved.copy(
            nome = m.nome,
            importe = m.importe,
            periodicidade = m.periodicidade,
            data_ini = m.data_ini,
            data_rnv = m.data_rnv,
            tipo = m.tipo
        )

        try {
            // 1) UPDATE LOCAL
            dao.update(toSave)

            // 2) UPDATE REMOTO
            toSave.remote_id?.let { remoteId ->
                remoteDataSource.update(remoteId, toSave)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // -------------------------------------------------------------------------
    // DELETE (local → remoto)
    // -------------------------------------------------------------------------

    /**
     * Elimina un movimiento recurrente tanto local como remotamente.
     *
     * Flujo:
     * - Si el movimiento tiene `remote_id`, se elimina en PocketBase.
     * - Luego se elimina de Room.
     *
     * @param m Movimiento recurrente que se desea eliminar.
     */
    fun delete(m: MovRecur) = viewModelScope.launch {

        try {
            // 1) DELETE REMOTO (si corresponde)
            m.remote_id?.let { remoteId ->
                remoteDataSource.delete(remoteId)
            }

            // 2) DELETE LOCAL
            dao.delete(m)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}