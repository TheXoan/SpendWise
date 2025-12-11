package com.arcaneia.spendwise.data.model;

/**
 * ViewModel encargado de gestionar la lógica de categorías dentro de la aplicación.
 *
 * Esta clase implementa un flujo de trabajo **offline-first con sincronización instantánea**:
 *
 * - Cualquier cambio (insert/update/delete) se aplica inmediatamente a la base de datos local.
 * - Automáticamente se intenta sincronizar la operación con PocketBase.
 * - Los cambios locales se observan en tiempo real mediante un `StateFlow`.
 *
 * El ViewModel actúa como capa intermedia entre la UI, el repositorio local y la API remota,
 * asegurando consistencia entre los datos locales y los remotos sin bloquear la interfaz.
 *
 * @property repository Repositorio local encargado del acceso a `CategoriaDao`.
 * @property remoteDataSource Cliente remoto encargado de realizar operaciones en PocketBase.
 * @property appContext Contexto necesario para acceder a `TokenDataStore` y obtener el `userId`.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\rJ\u000e\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\rJ\u000e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0019"}, d2 = {"Lcom/arcaneia/spendwise/data/model/CategoriaViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/arcaneia/spendwise/data/repository/CategoriaRepository;", "remoteDataSource", "Lcom/arcaneia/spendwise/apis/data/model/CategoriaRemoteDataSource;", "appContext", "Landroid/content/Context;", "<init>", "(Lcom/arcaneia/spendwise/data/repository/CategoriaRepository;Lcom/arcaneia/spendwise/apis/data/model/CategoriaRemoteDataSource;Landroid/content/Context;)V", "_categorias", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/arcaneia/spendwise/data/entity/Categoria;", "categorias", "Lkotlinx/coroutines/flow/StateFlow;", "getCategorias", "()Lkotlinx/coroutines/flow/StateFlow;", "insert", "Lkotlinx/coroutines/Job;", "cat", "update", "deleteById", "id", "", "app_debug"})
public final class CategoriaViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.arcaneia.spendwise.data.repository.CategoriaRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.arcaneia.spendwise.apis.data.model.CategoriaRemoteDataSource remoteDataSource = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context appContext = null;
    
    /**
     * Flujo interno que mantiene la lista de categorías observada desde Room.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.arcaneia.spendwise.data.entity.Categoria>> _categorias = null;
    
    /**
     * Flujo de solo lectura que expone la lista de categorías a la UI.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.arcaneia.spendwise.data.entity.Categoria>> categorias = null;
    
    public CategoriaViewModel(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.repository.CategoriaRepository repository, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.apis.data.model.CategoriaRemoteDataSource remoteDataSource, @org.jetbrains.annotations.NotNull()
    android.content.Context appContext) {
        super();
    }
    
    /**
     * Flujo de solo lectura que expone la lista de categorías a la UI.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.arcaneia.spendwise.data.entity.Categoria>> getCategorias() {
        return null;
    }
    
    /**
     * Inserta una categoría **localmente e inmediatamente**, y luego intenta sincronizarla
     * con el servidor.
     *
     * Flujo de trabajo:
     * 1. Se inserta en Room.
     * 2. Se crea el registro correspondiente en PocketBase.
     * 3. Se actualiza el `remote_id` en la base de datos local.
     *
     * @param cat Categoría que se desea insertar.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insert(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Categoria cat) {
        return null;
    }
    
    /**
     * Actualiza una categoría tanto local como remotamente.
     *
     * - Primero se actualiza en la base de datos local.
     * - Si ya fue sincronizada previamente (tiene `remote_id`), también se actualiza en PocketBase.
     *
     * Se preserva el `remote_id` original y solo se modifican los campos editables.
     *
     * @param cat Categoría con los nuevos valores a aplicar.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job update(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Categoria cat) {
        return null;
    }
    
    /**
     * Elimina una categoría localmente y, si corresponde, también remotamente.
     *
     * Flujo:
     * - Si la categoría tiene un `remote_id`, se elimina primero de PocketBase.
     * - Luego se elimina en Room.
     *
     * @param id Identificador local de la categoría que se desea eliminar.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteById(int id) {
        return null;
    }
}