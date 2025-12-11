package com.arcaneia.spendwise.data.model;

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
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000fJ\u000e\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000fJ\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0019"}, d2 = {"Lcom/arcaneia/spendwise/data/model/MovRecurViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/arcaneia/spendwise/data/repository/MovRecurRepository;", "remoteDataSource", "Lcom/arcaneia/spendwise/apis/data/model/MovRecurRemoteDataSource;", "dao", "Lcom/arcaneia/spendwise/data/dao/MovRecurDao;", "appContext", "Landroid/content/Context;", "<init>", "(Lcom/arcaneia/spendwise/data/repository/MovRecurRepository;Lcom/arcaneia/spendwise/apis/data/model/MovRecurRemoteDataSource;Lcom/arcaneia/spendwise/data/dao/MovRecurDao;Landroid/content/Context;)V", "_movRecur", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/arcaneia/spendwise/data/entity/MovRecur;", "movRecur", "Lkotlinx/coroutines/flow/StateFlow;", "getMovRecur", "()Lkotlinx/coroutines/flow/StateFlow;", "insert", "Lkotlinx/coroutines/Job;", "m", "update", "delete", "app_debug"})
public final class MovRecurViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.arcaneia.spendwise.data.repository.MovRecurRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.arcaneia.spendwise.apis.data.model.MovRecurRemoteDataSource remoteDataSource = null;
    @org.jetbrains.annotations.NotNull()
    private final com.arcaneia.spendwise.data.dao.MovRecurDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context appContext = null;
    
    /**
     * Flujo interno con la lista de movimientos recurrentes almacenados en Room.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.arcaneia.spendwise.data.entity.MovRecur>> _movRecur = null;
    
    /**
     * Flujo observable por la UI que expone la lista de movimientos recurrentes.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.arcaneia.spendwise.data.entity.MovRecur>> movRecur = null;
    
    public MovRecurViewModel(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.repository.MovRecurRepository repository, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.apis.data.model.MovRecurRemoteDataSource remoteDataSource, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.dao.MovRecurDao dao, @org.jetbrains.annotations.NotNull()
    android.content.Context appContext) {
        super();
    }
    
    /**
     * Flujo observable por la UI que expone la lista de movimientos recurrentes.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.arcaneia.spendwise.data.entity.MovRecur>> getMovRecur() {
        return null;
    }
    
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
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insert(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.MovRecur m) {
        return null;
    }
    
    /**
     * Actualiza un movimiento recurrente tanto en la base de datos local como en PocketBase.
     *
     * - Primero se obtiene el registro actual para preservar el `remote_id`.
     * - Se actualiza en Room.
     * - Si ya estaba sincronizado, se aplica la actualización en el servidor.
     *
     * @param m Movimiento recurrente con los valores actualizados.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job update(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.MovRecur m) {
        return null;
    }
    
    /**
     * Elimina un movimiento recurrente tanto local como remotamente.
     *
     * Flujo:
     * - Si el movimiento tiene `remote_id`, se elimina en PocketBase.
     * - Luego se elimina de Room.
     *
     * @param m Movimiento recurrente que se desea eliminar.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job delete(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.MovRecur m) {
        return null;
    }
}