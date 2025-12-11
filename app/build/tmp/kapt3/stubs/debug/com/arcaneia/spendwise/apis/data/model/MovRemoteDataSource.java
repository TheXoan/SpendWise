package com.arcaneia.spendwise.apis.data.model;

/**
 * Fuente de datos remota encargada de gestionar la comunicación entre la aplicación
 * y la colección `mov` de PocketBase.
 *
 * Esta clase encapsula todas las operaciones CRUD remotas relacionadas con movimientos
 * simples, proporcionando un acceso seguro, tipado y centralizado a las llamadas HTTP.
 * Utiliza Retrofit para interactuar con la API y DataStore para obtener el token de
 * autenticación almacenado en el dispositivo.
 *
 * ---
 *
 * ## 🔐 Autenticación
 * Cada operación realiza una llamada al método privado [authHeader], el cual:
 * - Recupera el token actual desde [TokenDataStore].
 * - Lanza una excepción si el token no existe (evitando llamadas inválidas al servidor).
 * - Devuelve el header `Bearer <token>` requerido por PocketBase.
 *
 * ---
 *
 * ## 🔄 Sincronización y compatibilidad
 * Esta fuente de datos es totalmente compatible con:
 * - **IDs remotos** (`categoriaPBId`, `movRecurPBId`), necesarios para el mapeo relacional.
 * - **renew_hash**, usado para evitar duplicados cuando un movimiento se genera desde
 *  una renovación recurrente.
 *
 * Las operaciones remotas se limitan a enviar y recibir datos; el mapeo hacia Room y
 * el merge final se realiza en `MovSyncRepository`.
 *
 * ---
 *
 * ## Métodos principales
 *
 * ### fetchAll(userId)
 * Obtiene todos los movimientos del usuario autenticado mediante un filtro en PocketBase.
 *
 * ### create(...)
 * Envía un nuevo movimiento al servidor.
 * Admite valores opcionales como `descricion` o `mov_recur_id`.
 *
 * ### update(...)
 * Actualiza un movimiento existente en PocketBase utilizando su ID remoto.
 *
 * ### delete(movPBId)
 * Elimina un movimiento remoto de forma definitiva.
 *
 * ---
 *
 * @property context Contexto necesario para acceder a DataStore y recursos del sistema.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u0007H\u0082@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\rJ0\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007H\u0086@\u00a2\u0006\u0002\u0010\u0013J0\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/arcaneia/spendwise/apis/data/model/MovRemoteDataSource;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "authHeader", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchAll", "", "LMovRecord;", "userId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "mov", "Lcom/arcaneia/spendwise/data/entity/Mov;", "categoriaPBId", "movRecurPBId", "(Ljava/lang/String;Lcom/arcaneia/spendwise/data/entity/Mov;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "movPBId", "delete", "", "app_debug"})
public final class MovRemoteDataSource {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    public MovRemoteDataSource(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Construye el header de autenticación `Bearer <token>` requerido por PocketBase.
     *
     * Recupera el token de DataStore y lanza una excepción si está vacío.
     *
     * @return Cadena con el header de autorización.
     * @throws IllegalArgumentException si no hay token disponible.
     */
    private final java.lang.Object authHeader(kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    /**
     * Obtiene todos los movimientos remotos pertenecientes al usuario especificado.
     *
     * Esta llamada aplica un filtro en el servidor:
     * ```
     * user='<userId>'
     * ```
     *
     * @param userId ID remoto del usuario autenticado.
     * @return Lista de [MovRecord] correspondientes al usuario.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchAll(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<MovRecord>> $completion) {
        return null;
    }
    
    /**
     * Crea un nuevo movimiento remoto en PocketBase.
     *
     * El cuerpo enviado incluye:
     * - tipo, importe, fecha y categoría.
     * - mov_recur_id (si proviene de una renovación).
     * - renew_hash para evitar duplicados.
     *
     * @param userId ID del usuario propietario.
     * @param mov Objeto [Mov] local a sincronizar.
     * @param categoriaPBId ID remoto de la categoría asociada.
     * @param movRecurPBId ID remoto del movimiento recurrente (si lo hubiera).
     *
     * @return El movimiento remoto creado como [MovRecord].
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object create(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Mov mov, @org.jetbrains.annotations.NotNull()
    java.lang.String categoriaPBId, @org.jetbrains.annotations.Nullable()
    java.lang.String movRecurPBId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super MovRecord> $completion) {
        return null;
    }
    
    /**
     * Actualiza un movimiento ya existente en PocketBase.
     *
     * Solo se envían los campos necesarios para mantener consistencia con el modelo local.
     *
     * @param movPBId ID remoto del movimiento a actualizar.
     * @param mov Objeto [Mov] local con los valores actualizados.
     * @param categoriaPBId ID remoto de la categoría asociada.
     * @param movRecurPBId ID remoto del movimiento recurrente (si corresponde).
     *
     * @return Registro actualizado como [MovRecord].
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object update(@org.jetbrains.annotations.NotNull()
    java.lang.String movPBId, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Mov mov, @org.jetbrains.annotations.NotNull()
    java.lang.String categoriaPBId, @org.jetbrains.annotations.Nullable()
    java.lang.String movRecurPBId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super MovRecord> $completion) {
        return null;
    }
    
    /**
     * Elimina un movimiento remoto en PocketBase.
     *
     * @param movPBId ID remoto del movimiento que se desea eliminar.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    java.lang.String movPBId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}