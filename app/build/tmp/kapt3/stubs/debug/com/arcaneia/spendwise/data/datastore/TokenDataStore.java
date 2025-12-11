package com.arcaneia.spendwise.data.datastore;

/**
 * Objeto que gestiona la lectura y escritura del token y userId del usuario.
 *
 * Este gestor permite:
 * - Guardar token + userId en la misma operación.
 * - Recuperar ambos como flujos (`Flow<String?>`).
 * - Limpiar la sesión eliminando ambos valores.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J&\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00102\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00102\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\u0012\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\u0013R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/arcaneia/spendwise/data/datastore/TokenDataStore;", "", "<init>", "()V", "TOKEN_KEY", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "USER_ID_KEY", "saveSession", "", "context", "Landroid/content/Context;", "token", "userId", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getToken", "Lkotlinx/coroutines/flow/Flow;", "getUserId", "clear", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class TokenDataStore {
    
    /**
     * Clave utilizada para almacenar el token JWT en DataStore.
     */
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> TOKEN_KEY = null;
    
    /**
     * Clave utilizada para almacenar el ID del usuario autenticado.
     */
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> USER_ID_KEY = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.arcaneia.spendwise.data.datastore.TokenDataStore INSTANCE = null;
    
    private TokenDataStore() {
        super();
    }
    
    /**
     * Guarda el token y el userId en una sola operación atómica.
     *
     * @param context Contexto necesario para acceder al DataStore.
     * @param token Token JWT devuelto por el backend.
     * @param userId Identificador del usuario autenticado.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveSession(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Devuelve un flujo que emite el token almacenado.
     *
     * @param context Contexto asociado al DataStore.
     * @return Un flujo que emite el token actual o null si no existe.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getToken(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    /**
     * Devuelve un flujo que emite el userId almacenado.
     *
     * @param context Contexto asociado al DataStore.
     * @return Un flujo que emite el userId actual o null si no está guardado.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getUserId(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    /**
     * Elimina tanto el token como el userId, cerrando la sesión local del usuario.
     *
     * @param context Contexto necesario para acceder al DataStore.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clear(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}