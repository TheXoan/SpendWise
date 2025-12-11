package com.arcaneia.spendwise.data.dao;

/**
 * DAO (Data Access Object) para gestionar todas las operaciones relacionadas
 * con la entidad [Categoria] dentro de la base de datos Room.
 *
 * Incluye operaciones CRUD básicas y un conjunto de métodos especializados
 * para la sincronización con el backend PocketBase, permitiendo mapear
 * categorías locales con sus equivalentes remotos mediante `remote_id`.
 *
 * Este DAO está optimizado para funcionar en entornos offline-first,
 * garantizando que cada categoría pueda ser insertada, actualizada,
 * consultada o marcada como sincronizada dependiendo del estado
 * de la base de datos local y remota.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\t\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000bH\'J\u0016\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0016\u001a\u00020\u0017H\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u001e\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0017H\u00a7@\u00a2\u0006\u0002\u0010\u001c\u00a8\u0006\u001d\u00c0\u0006\u0003"}, d2 = {"Lcom/arcaneia/spendwise/data/dao/CategoriaDao;", "", "insert", "", "categoria", "Lcom/arcaneia/spendwise/data/entity/Categoria;", "(Lcom/arcaneia/spendwise/data/entity/Categoria;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "delete", "getAllCategories", "Lkotlinx/coroutines/flow/Flow;", "", "deleteById", "categoriaId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "id", "getPendingToUpload", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByRemoteId", "remoteId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllWithRemoteId", "attachRemoteId", "localId", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface CategoriaDao {
    
    /**
     * Inserta una nueva categoría en la base de datos.
     *
     * Si ocurre un conflicto (por ejemplo, IDs repetidos),
     * el registro se reemplaza usando `OnConflictStrategy.REPLACE`.
     *
     * @param categoria Entidad a insertar.
     * @return El ID autogenerado de la fila insertada.
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Categoria categoria, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * Actualiza los datos de una categoría existente.
     *
     * @param categoria Categoría con los nuevos valores.
     */
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Categoria categoria, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Elimina una categoría de la base de datos.
     *
     * @param categoria Entidad que se desea eliminar.
     */
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Categoria categoria, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Recupera todas las categorías excepto la de ID 1,
     * que corresponde a la categoría reservada **"Recurrente"**.
     *
     * @return Un flujo reactivo con la lista de categorías.
     */
    @androidx.room.Query(value = "SELECT * FROM categoria WHERE id != 1 ORDER BY id ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.arcaneia.spendwise.data.entity.Categoria>> getAllCategories();
    
    /**
     * Elimina una categoría según su ID.
     *
     * @param categoriaId ID de la categoría que se desea eliminar.
     */
    @androidx.room.Query(value = "DELETE FROM categoria WHERE id = :categoriaId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteById(int categoriaId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Busca una categoría por su ID local.
     *
     * @param id Identificador autogenerado por Room.
     * @return Categoría encontrada, o `null` si no existe.
     */
    @androidx.room.Query(value = "SELECT * FROM categoria WHERE id = :id LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.data.entity.Categoria> $completion);
    
    /**
     * Devuelve todas las categorías locales que aún no se han subido al servidor,
     * es decir, aquellas cuyo `remote_id` es null.
     *
     * Esta lista se utiliza durante la fase de *subida* en procesos de sincronización.
     *
     * @return Lista de categorías pendientes de sincronización.
     */
    @androidx.room.Query(value = "SELECT * FROM categoria WHERE remote_id IS NULL")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPendingToUpload(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.arcaneia.spendwise.data.entity.Categoria>> $completion);
    
    /**
     * Busca una categoría local mediante su identificador remoto (`remote_id`).
     *
     * Se usa durante el proceso de fusión (merge) entre los datos remotos
     * y locales para evitar duplicados.
     *
     * @param remoteId ID asignado por PocketBase.
     * @return La categoría local correspondiente o `null` si no existe.
     */
    @androidx.room.Query(value = "SELECT * FROM categoria WHERE remote_id = :remoteId LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByRemoteId(@org.jetbrains.annotations.NotNull()
    java.lang.String remoteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.data.entity.Categoria> $completion);
    
    /**
     * Obtiene todas las categorías que ya tienen asignado un `remote_id`,
     * lo cual indica que ya han sido sincronizadas con el servidor.
     *
     * Esta lista es fundamental para detectar eliminaciones remotas.
     *
     * @return Lista de categorías sincronizadas.
     */
    @androidx.room.Query(value = "SELECT * FROM categoria WHERE remote_id IS NOT NULL")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllWithRemoteId(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.arcaneia.spendwise.data.entity.Categoria>> $completion);
    
    /**
     * Asigna un `remote_id` a una categoría almacenada en la base de datos local.
     *
     * Este método se usa tras insertar la categoría en PocketBase,
     * permitiendo enlazar la fila local con su identificación remota.
     *
     * @param localId ID autogenerado en Room.
     * @param remoteId ID remoto asignado por PocketBase.
     */
    @androidx.room.Query(value = "UPDATE categoria SET remote_id = :remoteId WHERE id = :localId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object attachRemoteId(int localId, @org.jetbrains.annotations.NotNull()
    java.lang.String remoteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}