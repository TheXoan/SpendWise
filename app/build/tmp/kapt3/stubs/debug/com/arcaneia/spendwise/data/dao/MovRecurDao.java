package com.arcaneia.spendwise.data.dao;

/**
 * DAO (Data Access Object) encargado de gestionar las operaciones relacionadas con
 * movimientos recurrentes representados por la entidad [MovRecur].
 *
 * Incluye funciones para insertar, actualizar, eliminar y consultar movimientos
 * recurrentes, así como obtener aquellos que deben renovarse según su fecha programada
 * y métodos auxiliares para la sincronización con el servidor.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\t\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000bH\'J\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0018\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u001e\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u001c\u00a8\u0006\u001d\u00c0\u0006\u0003"}, d2 = {"Lcom/arcaneia/spendwise/data/dao/MovRecurDao;", "", "insert", "", "movRecur", "Lcom/arcaneia/spendwise/data/entity/MovRecur;", "(Lcom/arcaneia/spendwise/data/entity/MovRecur;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "", "update", "getAllMovRecur", "Lkotlinx/coroutines/flow/Flow;", "", "getMovsToRenew", "today", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPendingToUpload", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByRemoteId", "remoteId", "getAllWithRemoteId", "attachRemoteId", "localId", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface MovRecurDao {
    
    /**
     * Inserta un movimiento recurrente en la base de datos.
     * Si ya existe otro con el mismo ID, será reemplazado (`OnConflictStrategy.REPLACE`).
     *
     * @param movRecur Instancia del movimiento recurrente que se desea insertar.
     * @return El ID autogenerado del registro insertado.
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.MovRecur movRecur, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * Elimina un movimiento recurrente existente.
     *
     * @param movRecur Instancia de [MovRecur] que debe eliminarse.
     */
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.MovRecur movRecur, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Actualiza los datos de un movimiento recurrente existente.
     *
     * @param movRecur Instancia con los campos actualizados.
     */
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.MovRecur movRecur, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Obtiene todos los movimientos recurrentes ordenados por la fecha de la próxima renovación
     * (`data_rnv`) en orden ascendente.
     *
     * @return Un [Flow] de [List] de [MovRecur] que emite la lista completa cada vez que cambia la tabla.
     */
    @androidx.room.Query(value = "SELECT * FROM mov_recur ORDER BY data_rnv ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.arcaneia.spendwise.data.entity.MovRecur>> getAllMovRecur();
    
    /**
     * Obtiene todos los movimientos recurrentes cuya fecha de renovación (`data_rnv`) sea
     * anterior o igual a la fecha indicada.
     *
     * Esta función es crucial para determinar qué movimientos deben renovarse en el día actual
     * o han vencido.
     *
     * @param today Fecha límite en formato `"YYYY-MM-DD"`.
     * @return Una lista con los movimientos recurrentes [MovRecur] que deben renovarse.
     */
    @androidx.room.Query(value = "SELECT * FROM mov_recur WHERE data_rnv <= :today")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMovsToRenew(@org.jetbrains.annotations.NotNull()
    java.lang.String today, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.arcaneia.spendwise.data.entity.MovRecur>> $completion);
    
    /**
     * Busca un movimiento recurrente por su ID local (clave primaria de Room).
     *
     * @param id El ID local (Int) del movimiento recurrente.
     * @return El movimiento [MovRecur] correspondiente, o `null` si no se encuentra.
     */
    @androidx.room.Query(value = "SELECT * FROM mov_recur WHERE id = :id LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.data.entity.MovRecur> $completion);
    
    /**
     * Obtiene todos los movimientos recurrentes locales que aún no fueron subidos,
     * es decir, aquellos cuyo `remote_id` es null.
     *
     * Se utiliza en la fase de subida de la sincronización.
     *
     * @return Una lista de movimientos [MovRecur] pendientes de subir.
     */
    @androidx.room.Query(value = "SELECT * FROM mov_recur WHERE remote_id IS NULL")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPendingToUpload(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.arcaneia.spendwise.data.entity.MovRecur>> $completion);
    
    /**
     * Busca un movimiento recurrente local usando su ID remoto (PocketBase ID).
     * Se utiliza en la fase de fusión (merge) de la sincronización.
     *
     * @param remoteId El identificador de PocketBase del movimiento recurrente.
     * @return El movimiento [MovRecur] local que coincide, o `null`.
     */
    @androidx.room.Query(value = "SELECT * FROM mov_recur WHERE remote_id = :remoteId LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByRemoteId(@org.jetbrains.annotations.NotNull()
    java.lang.String remoteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.data.entity.MovRecur> $completion);
    
    /**
     * Obtiene todos los movimientos recurrentes que ya tienen un ID remoto asignado.
     * Esta lista se utiliza para comparar contra el servidor y detectar borrados remotos.
     *
     * @return Una lista de movimientos [MovRecur] sincronizados.
     */
    @androidx.room.Query(value = "SELECT * FROM mov_recur WHERE remote_id IS NOT NULL")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllWithRemoteId(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.arcaneia.spendwise.data.entity.MovRecur>> $completion);
    
    /**
     * Asigna un identificador remoto (`remote_id`) a un movimiento recurrente
     * que fue previamente insertado localmente.
     *
     * @param localId ID de Room del movimiento recurrente.
     * @param remoteId ID asignado por PocketBase.
     */
    @androidx.room.Query(value = "UPDATE mov_recur SET remote_id = :remoteId WHERE id = :localId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object attachRemoteId(int localId, @org.jetbrains.annotations.NotNull()
    java.lang.String remoteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}