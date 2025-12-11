package com.arcaneia.spendwise.apis.data.model;

/**
 * Fuente de datos remota encargada de gestionar todas las operaciones
 * relacionadas con los **movimientos recurrentes** (`mov_recur`) en el
 * servidor PocketBase.
 *
 * Esta clase actúa como intermediaria entre la capa de sincronización/repositorio
 * y las llamadas HTTP realizadas mediante Retrofit, proporcionando funciones para:
 *
 * - Obtener todos los movimientos recurrentes remotos del usuario.
 * - Crear nuevos movimientos recurrentes en el servidor.
 * - Actualizar registros existentes.
 * - Eliminar movimientos recurrentes remotos.
 *
 * Internamente maneja la autenticación utilizando el token almacenado en
 * `TokenDataStore`, construyendo el encabezado `Bearer <token>` requerido
 * por PocketBase para todas las peticiones protegidas.
 *
 * ### Uso
 * Esta clase es utilizada típicamente por:
 * - `MovRecurSyncRepository`: para sincronización remota ↔ local.
 * - ViewModels que requieran crear, editar o borrar movimientos recurrentes directamente.
 *
 * @property context Contexto necesario para acceder al `TokenDataStore`.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u0007H\u0082@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\rJ\u001e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001e\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/arcaneia/spendwise/apis/data/model/MovRecurRemoteDataSource;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "authHeader", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchAll", "", "Lcom/arcaneia/spendwise/apis/data/model/MovRecurRecord;", "userId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "movRecur", "Lcom/arcaneia/spendwise/data/entity/MovRecur;", "(Ljava/lang/String;Lcom/arcaneia/spendwise/data/entity/MovRecur;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "movRecurPBId", "delete", "", "app_debug"})
public final class MovRecurRemoteDataSource {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    public MovRecurRemoteDataSource(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Genera el encabezado de autenticación para las peticiones HTTP.
     *
     * Obtiene el token almacenado en `TokenDataStore` y lo devuelve en formato
     * `"Bearer <token>"`, obligatorio para acceder a las colecciones protegidas
     * de PocketBase.
     *
     * @return Cadena de autenticación para usar en la API.
     * @throws IllegalArgumentException Si no existe un token almacenado.
     */
    private final java.lang.Object authHeader(kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    /**
     * Descarga todos los movimientos recurrentes pertenecientes al usuario dado.
     *
     * Realiza una consulta filtrada mediante el campo `user` en PocketBase.
     *
     * @param userId Identificador remoto del usuario propietario.
     * @return Lista de registros remotos [MovRecurRecord].
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchAll(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.arcaneia.spendwise.apis.data.model.MovRecurRecord>> $completion) {
        return null;
    }
    
    /**
     * Crea un nuevo movimiento recurrente en el servidor PocketBase.
     *
     * Mapea los campos del modelo local [MovRecur] al formato requerido
     * por el backend, incluyendo:
     * - Conversión de enums (`Recurrence`, `TypeMov`) a String.
     * - Inclusión del ID del usuario propietario.
     *
     * @param userId ID remoto del usuario propietario del registro.
     * @param movRecur Entidad local que se desea subir.
     * @return El registro creado por el servidor.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object create(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.MovRecur movRecur, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.apis.data.model.MovRecurRecord> $completion) {
        return null;
    }
    
    /**
     * Actualiza un movimiento recurrente existente en PocketBase.
     *
     * Solo se envían los campos que pueden ser modificados, respetando la
     * estructura aceptada por la API REST.
     *
     * @param movRecurPBId ID remoto del movimiento recurrente que se actualizará.
     * @param movRecur Entidad local con los cambios aplicados.
     * @return El registro actualizado devuelto por el servidor.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object update(@org.jetbrains.annotations.NotNull()
    java.lang.String movRecurPBId, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.MovRecur movRecur, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.apis.data.model.MovRecurRecord> $completion) {
        return null;
    }
    
    /**
     * Elimina un movimiento recurrente remoto identificado por su ID.
     *
     * @param movRecurPBId ID remoto del registro a eliminar.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    java.lang.String movRecurPBId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}