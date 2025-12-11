package com.arcaneia.spendwise.data.model;

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
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0011J\u000e\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0011J\u000e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0011J\u000e\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020 J\u000e\u0010/\u001a\u00020&2\u0006\u00100\u001a\u00020 R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u001d\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u00100\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015R\u0016\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0015R#\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u00100\u0013\u00a2\u0006\u000e\n\u0000\u0012\u0004\b)\u0010*\u001a\u0004\b+\u0010\u0015R\u0016\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0015R#\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002020\u00100\u0013\u00a2\u0006\u000e\n\u0000\u0012\u0004\b3\u0010*\u001a\u0004\b4\u0010\u0015\u00a8\u00065"}, d2 = {"Lcom/arcaneia/spendwise/data/model/MovViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/arcaneia/spendwise/data/repository/MovRepository;", "remoteDataSource", "Lcom/arcaneia/spendwise/apis/data/model/MovRemoteDataSource;", "categoriaDao", "Lcom/arcaneia/spendwise/data/dao/CategoriaDao;", "movRecurDao", "Lcom/arcaneia/spendwise/data/dao/MovRecurDao;", "appContext", "Landroid/content/Context;", "<init>", "(Lcom/arcaneia/spendwise/data/repository/MovRepository;Lcom/arcaneia/spendwise/apis/data/model/MovRemoteDataSource;Lcom/arcaneia/spendwise/data/dao/CategoriaDao;Lcom/arcaneia/spendwise/data/dao/MovRecurDao;Landroid/content/Context;)V", "_movs", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/arcaneia/spendwise/data/entity/Mov;", "movs", "Lkotlinx/coroutines/flow/StateFlow;", "getMovs", "()Lkotlinx/coroutines/flow/StateFlow;", "insert", "Lkotlinx/coroutines/Job;", "mov", "update", "newMov", "delete", "balanceMes", "", "getBalanceMes", "yearsAvailable", "", "getYearsAvailable", "_selectedYear", "selectedYearFlow", "getSelectedYearFlow", "onYearSelected", "", "year", "monthsAvailable", "getMonthsAvailable$annotations", "()V", "getMonthsAvailable", "_selectedMonth", "selectedMonthFlow", "getSelectedMonthFlow", "onMonthSelected", "month", "movements", "Lcom/arcaneia/spendwise/data/entity/MovWithCategory;", "getMovements$annotations", "getMovements", "app_debug"})
public final class MovViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.arcaneia.spendwise.data.repository.MovRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.arcaneia.spendwise.apis.data.model.MovRemoteDataSource remoteDataSource = null;
    @org.jetbrains.annotations.NotNull()
    private final com.arcaneia.spendwise.data.dao.CategoriaDao categoriaDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.arcaneia.spendwise.data.dao.MovRecurDao movRecurDao = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context appContext = null;
    
    /**
     * Flujo interno que mantiene la lista de movimientos almacenada en Room.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.arcaneia.spendwise.data.entity.Mov>> _movs = null;
    
    /**
     * Lista de movimientos simple observable por la UI.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.arcaneia.spendwise.data.entity.Mov>> movs = null;
    
    /**
     * Flujo con el balance mensual del usuario.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Double> balanceMes = null;
    
    /**
     * Lista de años disponibles según los movimientos registrados.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> yearsAvailable = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _selectedYear = null;
    
    /**
     * Año seleccionado por la UI para filtrar movimientos.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> selectedYearFlow = null;
    
    /**
     * Meses disponibles para el año seleccionado.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> monthsAvailable = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _selectedMonth = null;
    
    /**
     * Mes seleccionado para filtrar movimientos.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> selectedMonthFlow = null;
    
    /**
     * Flujo reactivo con los movimientos filtrados por año y mes,
     * incluyendo su categoría (`MovWithCategory`).
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.arcaneia.spendwise.data.entity.MovWithCategory>> movements = null;
    
    public MovViewModel(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.repository.MovRepository repository, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.apis.data.model.MovRemoteDataSource remoteDataSource, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.dao.CategoriaDao categoriaDao, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.dao.MovRecurDao movRecurDao, @org.jetbrains.annotations.NotNull()
    android.content.Context appContext) {
        super();
    }
    
    /**
     * Lista de movimientos simple observable por la UI.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.arcaneia.spendwise.data.entity.Mov>> getMovs() {
        return null;
    }
    
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
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insert(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Mov mov) {
        return null;
    }
    
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
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job update(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Mov newMov) {
        return null;
    }
    
    /**
     * Elimina un movimiento simple tanto en PocketBase como en la base de datos local.
     *
     * Flujo:
     * 1. Si el movimiento tiene `remote_id`, se elimina en PocketBase.
     * 2. Se elimina de Room de inmediato.
     *
     * @param mov Movimiento a eliminar.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job delete(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Mov mov) {
        return null;
    }
    
    /**
     * Flujo con el balance mensual del usuario.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Double> getBalanceMes() {
        return null;
    }
    
    /**
     * Lista de años disponibles según los movimientos registrados.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> getYearsAvailable() {
        return null;
    }
    
    /**
     * Año seleccionado por la UI para filtrar movimientos.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSelectedYearFlow() {
        return null;
    }
    
    /**
     * Establece el año seleccionado y reinicia la selección de mes.
     */
    public final void onYearSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String year) {
    }
    
    /**
     * Meses disponibles para el año seleccionado.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> getMonthsAvailable() {
        return null;
    }
    
    /**
     * Meses disponibles para el año seleccionado.
     */
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    @java.lang.Deprecated()
    public static void getMonthsAvailable$annotations() {
    }
    
    /**
     * Mes seleccionado para filtrar movimientos.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSelectedMonthFlow() {
        return null;
    }
    
    /**
     * Establece el mes seleccionado.
     */
    public final void onMonthSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String month) {
    }
    
    /**
     * Flujo reactivo con los movimientos filtrados por año y mes,
     * incluyendo su categoría (`MovWithCategory`).
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.arcaneia.spendwise.data.entity.MovWithCategory>> getMovements() {
        return null;
    }
    
    /**
     * Flujo reactivo con los movimientos filtrados por año y mes,
     * incluyendo su categoría (`MovWithCategory`).
     */
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    @java.lang.Deprecated()
    public static void getMovements$annotations() {
    }
}