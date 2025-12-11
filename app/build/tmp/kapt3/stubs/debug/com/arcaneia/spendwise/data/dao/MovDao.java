package com.arcaneia.spendwise.data.dao;

/**
 * DAO (Data Access Object) responsable de gestionar todas las operaciones
 * relacionadas con la entidad [Mov], incluyendo:
 *
 * - Operaciones CRUD básicas.
 * - Consultas avanzadas orientadas a estadísticas.
 * - Integración completa con PocketBase para sincronización remota.
 * - Soporte para notificaciones de movimientos recurrentes.
 *
 * Este DAO constituye la principal capa de acceso a datos de los movimientos
 * simples dentro de la base de datos local Room.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0010\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\t\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\'J\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u000bH\'J\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u000b2\u0006\u0010\u0011\u001a\u00020\u000fH\'J$\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000e0\u000b2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000fH\'J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u001e\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u001bJ\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001a\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u001dJ\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010 \u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u001dJ\u0018\u0010!\u001a\u0004\u0018\u00010\u00052\u0006\u0010\"\u001a\u00020\u0019H\u00a7@\u00a2\u0006\u0002\u0010#J\u0014\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\u000bH\'J\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010&\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\u0019H\u00a7@\u00a2\u0006\u0002\u0010#J\u0018\u0010\'\u001a\u0004\u0018\u00010\u00052\u0006\u0010(\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u001d\u00a8\u0006)\u00c0\u0006\u0003"}, d2 = {"Lcom/arcaneia/spendwise/data/dao/MovDao;", "", "insert", "", "mov", "Lcom/arcaneia/spendwise/data/entity/Mov;", "(Lcom/arcaneia/spendwise/data/entity/Mov;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "delete", "getBalanceMesActual", "Lkotlinx/coroutines/flow/Flow;", "", "getYearsWithValues", "", "", "getMonthsFromYear", "year", "getMovementsForYearMonth", "Lcom/arcaneia/spendwise/data/entity/MovWithCategory;", "month", "getPendingToUpload", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "attachRemoteId", "localId", "", "remoteId", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByRemoteId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllWithRemoteId", "getAll", "deleteByRemoteId", "getById", "id", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllFlow", "getPendingNotifications", "markAsNotified", "getByRenewHash", "hash", "app_debug"})
@androidx.room.Dao()
public abstract interface MovDao {
    
    /**
     * Inserta un nuevo movimiento en la base de datos local.
     *
     * @param mov Instancia a insertar.
     * @return El ID autogenerado por Room.
     */
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Mov mov, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * Actualiza un movimiento existente.
     *
     * @param mov Movimiento modificado.
     */
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Mov mov, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Elimina un movimiento.
     *
     * @param mov Instancia a eliminar.
     */
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Mov mov, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Calcula el balance total del mes actual:
     *
     * **Ingresos − Gastos**, considerando únicamente movimientos cuyo
     * `data_mov` pertenezca al mes corriente.
     *
     * @return Un [Flow] con el valor del balance actualizado en tiempo real.
     */
    @androidx.room.Query(value = "\n        SELECT \n            (IFNULL(SUM(CASE WHEN tipo = \'INGRESO\' THEN importe END), 0) -\n             IFNULL(SUM(CASE WHEN tipo = \'GASTO\' THEN importe END), 0))\n        FROM mov\n        WHERE strftime(\'%Y-%m\', data_mov) = strftime(\'%Y-%m\', \'now\', \'localtime\')\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Double> getBalanceMesActual();
    
    /**
     * Obtiene la lista de años en los que existen movimientos registrados.
     *
     * @return Un [Flow] con años únicos ordenados de forma descendente.
     */
    @androidx.room.Query(value = "SELECT DISTINCT strftime(\'%Y\', data_mov) AS year FROM mov ORDER BY year DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getYearsWithValues();
    
    /**
     * Obtiene los meses que contienen movimientos para un año específico.
     *
     * @param year Año en formato YYYY.
     * @return Un [Flow] con meses únicos ordenados ascendentemente.
     */
    @androidx.room.Query(value = "\n        SELECT DISTINCT strftime(\'%m\', data_mov) AS month\n        FROM mov\n        WHERE strftime(\'%Y\', data_mov) = :year\n        ORDER BY month ASC\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getMonthsFromYear(@org.jetbrains.annotations.NotNull()
    java.lang.String year);
    
    /**
     * Obtiene todos los movimientos del año y mes especificados,
     * acompañados del nombre de su categoría mediante JOIN.
     *
     * @param year Año objetivo.
     * @param month Mes objetivo.
     * @return [Flow] con movimientos enriquecidos con su categoría.
     */
    @androidx.room.Query(value = "\n        SELECT mov.*, categoria.nome AS categoriaNome\n        FROM mov\n        INNER JOIN categoria ON mov.categoria_id = categoria.id\n        WHERE strftime(\'%Y\', mov.data_mov) = :year\n          AND strftime(\'%m\', mov.data_mov) = :month\n        ORDER BY mov.data_mov DESC\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.arcaneia.spendwise.data.entity.MovWithCategory>> getMovementsForYearMonth(@org.jetbrains.annotations.NotNull()
    java.lang.String year, @org.jetbrains.annotations.NotNull()
    java.lang.String month);
    
    /**
     * Devuelve todos los movimientos que aún no han sido subidos al servidor,
     * es decir, aquellos cuyo `remote_id` es `NULL`.
     *
     * @return Lista de movimientos pendientes de sincronizar.
     */
    @androidx.room.Query(value = "SELECT * FROM mov WHERE remote_id IS NULL")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPendingToUpload(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.arcaneia.spendwise.data.entity.Mov>> $completion);
    
    /**
     * Asocia un ID remoto asignado por PocketBase a un movimiento local.
     *
     * @param localId ID local del movimiento.
     * @param remoteId ID remoto generado en el servidor.
     */
    @androidx.room.Query(value = "UPDATE mov SET remote_id = :remoteId WHERE id = :localId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object attachRemoteId(int localId, @org.jetbrains.annotations.NotNull()
    java.lang.String remoteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Obtiene un movimiento local usando su ID remoto.
     *
     * @param remoteId Identificador remoto del movimiento.
     * @return El movimiento encontrado o `null`.
     */
    @androidx.room.Query(value = "SELECT * FROM mov WHERE remote_id = :remoteId LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByRemoteId(@org.jetbrains.annotations.NotNull()
    java.lang.String remoteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.data.entity.Mov> $completion);
    
    /**
     * Obtiene todos los movimientos que ya han sido sincronizados,
     * es decir, aquellos que tienen un `remote_id` asignado.
     *
     * @return Lista completa de movimientos sincronizados.
     */
    @androidx.room.Query(value = "SELECT * FROM mov WHERE remote_id IS NOT NULL")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllWithRemoteId(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.arcaneia.spendwise.data.entity.Mov>> $completion);
    
    /**
     * Devuelve todos los movimientos locales sin filtros.
     *
     * Usado en sincronización y exportación.
     */
    @androidx.room.Query(value = "SELECT * FROM mov")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.arcaneia.spendwise.data.entity.Mov>> $completion);
    
    /**
     * Elimina un movimiento local cuyo `remote_id` coincide
     * con un registro eliminado en PocketBase.
     *
     * @param remoteId ID remoto a eliminar.
     */
    @androidx.room.Query(value = "DELETE FROM mov WHERE remote_id = :remoteId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteByRemoteId(@org.jetbrains.annotations.NotNull()
    java.lang.String remoteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Obtiene un movimiento por su ID local.
     */
    @androidx.room.Query(value = "SELECT * FROM mov WHERE id = :id LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.data.entity.Mov> $completion);
    
    /**
     * Devuelve todos los movimientos como flujo reactivo.
     */
    @androidx.room.Query(value = "SELECT * FROM mov")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.arcaneia.spendwise.data.entity.Mov>> getAllFlow();
    
    /**
     * Obtiene todos los movimientos que aún no han sido notificados.
     *
     * Usado por [RenewMovsRecurWorker] para mostrar notificaciones locales.
     *
     * @return Lista de movimientos sin notificar.
     */
    @androidx.room.Query(value = "SELECT * FROM mov WHERE notificado = 0 ORDER BY data_mov ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPendingNotifications(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.arcaneia.spendwise.data.entity.Mov>> $completion);
    
    /**
     * Marca un movimiento como notificado.
     *
     * @param id ID local del movimiento.
     */
    @androidx.room.Query(value = "UPDATE mov SET notificado = 1 WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markAsNotified(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Busca un movimiento por su `renew_hash`, usado para detectar duplicados
     * durante la sincronización de renovaciones recurrentes.
     *
     * @param hash Valor del hash único generado en la renovación.
     * @return Movimiento encontrado o `null`.
     */
    @androidx.room.Query(value = "SELECT * FROM mov WHERE renew_hash = :hash LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByRenewHash(@org.jetbrains.annotations.NotNull()
    java.lang.String hash, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.data.entity.Mov> $completion);
}