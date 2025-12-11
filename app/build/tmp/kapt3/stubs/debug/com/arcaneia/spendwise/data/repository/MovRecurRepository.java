package com.arcaneia.spendwise.data.repository;

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
 *   - Evitar duplicados entre dispositivos durante la sincronización.
 *   - Garantizar que cada renovación sea única y rastreable.
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
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00130\u0012J\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0016J\u000e\u0010\u0017\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/arcaneia/spendwise/data/repository/MovRecurRepository;", "", "movRecurDao", "Lcom/arcaneia/spendwise/data/dao/MovRecurDao;", "movDao", "Lcom/arcaneia/spendwise/data/dao/MovDao;", "appContext", "Landroid/content/Context;", "<init>", "(Lcom/arcaneia/spendwise/data/dao/MovRecurDao;Lcom/arcaneia/spendwise/data/dao/MovDao;Landroid/content/Context;)V", "insert", "", "movRecur", "Lcom/arcaneia/spendwise/data/entity/MovRecur;", "(Lcom/arcaneia/spendwise/data/entity/MovRecur;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "update", "getAllMovRecur", "Lkotlinx/coroutines/flow/Flow;", "", "processRenewals", "Lcom/arcaneia/spendwise/data/entity/Mov;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPendingRenewalsCount", "", "app_debug"})
public final class MovRecurRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.arcaneia.spendwise.data.dao.MovRecurDao movRecurDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.arcaneia.spendwise.data.dao.MovDao movDao = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context appContext = null;
    
    public MovRecurRepository(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.dao.MovRecurDao movRecurDao, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.dao.MovDao movDao, @org.jetbrains.annotations.NotNull()
    android.content.Context appContext) {
        super();
    }
    
    /**
     * Inserta un movimiento recurrente en la base de datos local.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.MovRecur movRecur, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Elimina un movimiento recurrente local.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.MovRecur movRecur, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Actualiza el contenido de un movimiento recurrente local.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.MovRecur movRecur, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Devuelve un flujo reactivo con todos los movimientos recurrentes
     * almacenados en la base de datos.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.arcaneia.spendwise.data.entity.MovRecur>> getAllMovRecur() {
        return null;
    }
    
    /**
     * Procesa todas las renovaciones pendientes de movimientos recurrentes.
     *
     * Flujo completo:
     *
     * 1. Obtiene todos los `MovRecur` cuya `data_rnv` sea ≤ hoy.
     * 2. Por cada uno:
     *   - Genera movimientos simples ([Mov]) con fecha actual.
     *   - Crea un **renew_hash determinista**, único por fecha de renovación.
     *   - Inserta esos movimientos simples en la base local.
     *   - Avanza `data_rnv` tantas veces como sea necesario.
     *   - Actualiza el `MovRecur` en local.
     *   - Si tiene `remote_id`, actualiza también el registro en PocketBase.
     *
     * 3. Devuelve una lista con todos los movimientos generados.
     *
     * Este método **no notifica** al usuario.
     * La notificación se realiza posteriormente por el `RenewMovsRecurWorker`.
     *
     * @return Lista de movimientos simples creados durante el proceso.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object processRenewals(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.arcaneia.spendwise.data.entity.Mov>> $completion) {
        return null;
    }
    
    /**
     * Devuelve cuántos movimientos recurrentes deberían renovarse hoy.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getPendingRenewalsCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
}