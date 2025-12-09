package com.arcaneia.spendwise.data.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcaneia.spendwise.apis.data.model.MovRemoteDataSource
import com.arcaneia.spendwise.data.dao.CategoriaDao
import com.arcaneia.spendwise.data.dao.MovRecurDao
import com.arcaneia.spendwise.data.datastore.TokenDataStore
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.entity.MovWithCategory
import com.arcaneia.spendwise.data.repository.MovRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar los **movimientos simples** (`Mov`)
 * siguiendo un enfoque **offline-first con sincronización inmediata**.
 *
 * Proporciona:
 * - Inserción, actualización y eliminación sincronizadas con PocketBase.
 * - Cálculo de estadísticas (balance mensual).
 * - Filtros reactivos por año y mes.
 * - Flujo de movimientos enriquecidos con su categoría (`MovWithCategory`).
 *
 * ### Enfoque offline-first:
 * - Todas las operaciones se aplican **primero en local** (Room).
 * - Inmediatamente después se sincronizan con la API remota.
 * - Si la categoría o el mov_recur aún no tienen `remote_id`, la sincronización remota se omite hasta que exista.
 *
 * ### Flujos importantes:
 * - [movs]: lista completa de movimientos simples registrados.
 * - [balanceMes]: balance del mes actual.
 * - [yearsAvailable] y [monthsAvailable]: filtros disponibles según los datos almacenados.
 * - [movements]: lista filtrada por año + mes, junto con su categoría.
 *
 * @property repository Repositorio principal para operaciones sobre [Mov] y consultas estadísticas.
 * @property remoteDataSource Cliente remoto encargado de comunicarse con PocketBase.
 * @property categoriaDao DAO utilizado para mapear IDs locales de categoría hacia sus IDs remotos.
 * @property movRecurDao DAO utilizado para mapear IDs locales de movimientos recurrentes hacia IDs remotos.
 * @property appContext Contexto necesario para acceder a [TokenDataStore].
 */
class MovViewModel(
    private val repository: MovRepository,
    private val remoteDataSource: MovRemoteDataSource,
    private val categoriaDao: CategoriaDao,
    private val movRecurDao: MovRecurDao,
    private val appContext: Context
) : ViewModel() {

    /**
     * Flujo interno que mantiene la lista de movimientos almacenada en Room.
     */
    private val _movs = MutableStateFlow<List<Mov>>(emptyList())

    /**
     * Lista de movimientos simple observable por la UI.
     */
    val movs: StateFlow<List<Mov>> = _movs

    init {
        // Observa cambios en tiempo real desde Room.
        viewModelScope.launch {
            repository.getAllFlow().collect {
                _movs.value = it
            }
        }
    }

    // =========================================================
    // INSERT → local + remoto inmediato
    // =========================================================

    /**
     * Inserta un movimiento simple en Room y luego intenta subirlo a PocketBase.
     *
     * Flujo del insert:
     * 1. Inserta en la base de datos local.
     * 2. Obtiene el `userId`.
     * 3. Obtiene el `remote_id` de la categoría asociada.
     * 4. Obtiene el `remote_id` del movimiento recurrente si aplica.
     * 5. Crea el movimiento en PocketBase.
     * 6. Actualiza el movimiento local con su `remote_id`.
     *
     * Si algún ID remoto requerido no existe, la subida remota se omite.
     *
     * @param mov Movimiento a insertar.
     */
    fun insert(mov: Mov) = viewModelScope.launch {

        // 1) INSERT LOCAL
        val localId = repository.insert(mov).toInt()

        try {
            // 2) USER ID
            val userId = TokenDataStore.getUserId(appContext).first()!!

            // 3) CATEGORIA → remote_id
            val catLocal = categoriaDao.getById(mov.categoria_id)
            val catPB = catLocal?.remote_id ?: return@launch

            // 4) MOV_RECUR → remote_id
            val movRecurPB = mov.mov_recur_id?.let {
                movRecurDao.getById(it)?.remote_id
            }

            // 5) CREAR REMOTO
            val created = remoteDataSource.create(
                userId = userId,
                mov = mov,
                categoriaPBId = catPB,
                movRecurPBId = movRecurPB
            )

            // 6) ACTUALIZAR remote_id LOCAL
            repository.getById(localId)?.let { saved ->
                repository.update(saved.copy(remote_id = created.id))
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // =========================================================
    // UPDATE → local + remoto inmediato
    // =========================================================

    /**
     * Actualiza un movimiento simple tanto en local como en remoto.
     *
     * Flujo:
     * 1. Recupera el registro original para preservar el `remote_id`.
     * 2. Sobrescribe solo los campos modificables.
     * 3. Actualiza el registro en Room.
     * 4. Si tiene `remote_id`, intenta actualizarlo también en PocketBase.
     *
     * El update remoto se ejecuta únicamente si:
     * - El movimiento tiene `remote_id`, y
     * - Su categoría tiene `remote_id`.
     *
     * @param newMov Movimiento con los valores editados.
     */
    fun update(newMov: Mov) = viewModelScope.launch {

        val saved = repository.getById(newMov.id) ?: return@launch

        // Mantener remote_id → actualizar datos
        val movToSave = saved.copy(
            tipo = newMov.tipo,
            importe = newMov.importe,
            descricion = newMov.descricion,
            data_mov = newMov.data_mov,
            categoria_id = newMov.categoria_id,
            mov_recur_id = newMov.mov_recur_id
        )

        try {
            // 1) UPDATE LOCAL
            repository.update(movToSave)

            // 2) UPDATE REMOTO (si tiene remote_id asignado)
            movToSave.remote_id?.let { remoteId ->

                // Mapeo categoría
                val catPB = categoriaDao.getById(movToSave.categoria_id)?.remote_id
                    ?: return@let // No puede actualizar si no hay categoría remota

                // Mapeo mov_recur remoto
                val movRecurPB = movToSave.mov_recur_id?.let {
                    movRecurDao.getById(it)?.remote_id
                }

                remoteDataSource.update(
                    movPBId = remoteId,
                    mov = movToSave,
                    categoriaPBId = catPB,
                    movRecurPBId = movRecurPB
                )
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // =========================================================
    // DELETE → remoto + local inmediato
    // =========================================================

    /**
     * Elimina un movimiento simple tanto en PocketBase como en la base de datos local.
     *
     * Flujo:
     * 1. Si el movimiento tiene `remote_id`, se elimina en PocketBase.
     * 2. Se elimina de Room de inmediato.
     *
     * @param mov Movimiento a eliminar.
     */
    fun delete(mov: Mov) = viewModelScope.launch {

        try {
            // 1) DELETE REMOTO
            mov.remote_id?.let { remoteId ->
                remoteDataSource.delete(remoteId)
            }

            // 2) DELETE LOCAL
            repository.delete(mov)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // =========================================================
    // FLUJOS REACTIVOS Y FILTROS
    // =========================================================

    /**
     * Flujo con el balance mensual del usuario.
     */
    val balanceMes = repository.getBalanceMesActual()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    /**
     * Lista de años disponibles según los movimientos registrados.
     */
    val yearsAvailable = repository.getYearsWithValues()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _selectedYear = MutableStateFlow<String?>(null)

    /**
     * Año seleccionado por la UI para filtrar movimientos.
     */
    val selectedYearFlow: StateFlow<String?> = _selectedYear

    /**
     * Establece el año seleccionado y reinicia la selección de mes.
     */
    fun onYearSelected(year: String) {
        _selectedYear.value = year
        _selectedMonth.value = null
    }

    /**
     * Meses disponibles para el año seleccionado.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    val monthsAvailable = _selectedYear
        .filterNotNull()
        .flatMapLatest { year -> repository.getMonthsFromYear(year) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _selectedMonth = MutableStateFlow<String?>(null)

    /**
     * Mes seleccionado para filtrar movimientos.
     */
    val selectedMonthFlow: StateFlow<String?> = _selectedMonth

    /**
     * Establece el mes seleccionado.
     */
    fun onMonthSelected(month: String) {
        _selectedMonth.value = month
    }

    /**
     * Flujo reactivo con los movimientos filtrados por año y mes,
     * incluyendo su categoría (`MovWithCategory`).
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    val movements: StateFlow<List<MovWithCategory>> =
        combine(_selectedYear, _selectedMonth) { year, month ->
            if (year != null && month != null) {
                repository.getMovementsForYearMonth(year, month)
            } else flowOf(emptyList())
        }.flatMapLatest { it }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}