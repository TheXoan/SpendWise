package com.arcaneia.spendwise.data.repository;

/**
 * Repositorio encargado de gestionar todas las operaciones relacionadas con la entidad [Mov].
 *
 * Funciona como una capa intermedia entre el [MovDao] y los ViewModels, proporcionando
 * una API limpia y desacoplada para:
 *
 * - Insertar nuevos movimientos.
 * - Actualizar y eliminar movimientos existentes.
 * - Consultar información estadística como balance mensual, años disponibles y meses disponibles.
 * - Observar en tiempo real la lista completa de movimientos almacenados.
 *
 * El repositorio permite mantener una arquitectura más modular y facilita pruebas unitarias,
 * ya que aísla el acceso directo a Room detrás de esta abstracción.
 *
 * @property movDao DAO encargado de todas las operaciones sobre la tabla `mov`.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\r\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u000e\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00140\u0013J\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0013J\u0012\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00140\u0013J\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00140\u00132\u0006\u0010\u001a\u001a\u00020\u0018J\"\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00140\u00132\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/arcaneia/spendwise/data/repository/MovRepository;", "", "movDao", "Lcom/arcaneia/spendwise/data/dao/MovDao;", "<init>", "(Lcom/arcaneia/spendwise/data/dao/MovDao;)V", "insert", "", "mov", "Lcom/arcaneia/spendwise/data/entity/Mov;", "(Lcom/arcaneia/spendwise/data/entity/Mov;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "", "update", "getById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllFlow", "Lkotlinx/coroutines/flow/Flow;", "", "getBalanceMesActual", "", "getYearsWithValues", "", "getMonthsFromYear", "year", "getMovementsForYearMonth", "Lcom/arcaneia/spendwise/data/entity/MovWithCategory;", "month", "app_debug"})
public final class MovRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.arcaneia.spendwise.data.dao.MovDao movDao = null;
    
    public MovRepository(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.dao.MovDao movDao) {
        super();
    }
    
    /**
     * Inserta un nuevo movimiento en la base de datos local.
     *
     * A diferencia de otras inserciones, devuelve explícitamente el ID autogenerado para permitir
     * que el ViewModel pueda asociarle posteriormente un `remote_id` cuando se sincronice
     * con PocketBase.
     *
     * @param mov Movimiento a insertar.
     * @return El ID generado por Room tras la inserción.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Mov mov, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    /**
     * Elimina un movimiento de la base de datos.
     *
     * @param mov Entidad [Mov] que se desea eliminar.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Mov mov, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Actualiza un movimiento previamente existente en la base de datos.
     *
     * @param mov Movimiento con los valores actualizados.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Mov mov, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Obtiene un movimiento por su ID local.
     *
     * Esta función es esencial para operaciones de actualización sincronizada, ya que permite
     * conservar el `remote_id` durante merges o sincronizaciones remotas.
     *
     * @param id Identificador local del movimiento.
     * @return La entidad [Mov] correspondiente, o `null` si no existe.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.data.entity.Mov> $completion) {
        return null;
    }
    
    /**
     * Devuelve un flujo reactivo con la lista completa de movimientos almacenados.
     *
     * Este flujo se actualiza automáticamente cada vez que la tabla `mov` cambia,
     * lo que permite que la UI sea completamente reactiva sin necesidad de refrescar manualmente.
     *
     * @return Un [Flow] que emite listas de [Mov].
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.arcaneia.spendwise.data.entity.Mov>> getAllFlow() {
        return null;
    }
    
    /**
     * Obtiene el balance del mes actual, calculado como:
     * **Ingresos − Gastos**.
     *
     * @return Un flujo con el balance actualizado automáticamente.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Double> getBalanceMesActual() {
        return null;
    }
    
    /**
     * Obtiene la lista de años en los que existen movimientos registrados.
     *
     * @return Un flujo con una lista de strings correspondientes a los años (`"YYYY"`).
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getYearsWithValues() {
        return null;
    }
    
    /**
     * Obtiene los meses asociados a un año específico en los que existen movimientos.
     *
     * @param year Año en formato `"YYYY"`.
     * @return Un flujo con la lista de meses (`"MM"`).
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getMonthsFromYear(@org.jetbrains.annotations.NotNull()
    java.lang.String year) {
        return null;
    }
    
    /**
     * Obtiene todos los movimientos pertenecientes a un año y mes específicos,
     * incluyendo la información de la categoría mediante un JOIN.
     *
     * @param year Año en formato `"YYYY"`.
     * @param month Mes en formato `"MM"`.
     * @return Un flujo de listas de [MovWithCategory].
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.arcaneia.spendwise.data.entity.MovWithCategory>> getMovementsForYearMonth(@org.jetbrains.annotations.NotNull()
    java.lang.String year, @org.jetbrains.annotations.NotNull()
    java.lang.String month) {
        return null;
    }
}