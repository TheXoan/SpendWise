package com.arcaneia.spendwise.data.datastore;

/**
 * Clase encargada de gestionar la lectura y escritura de permisos persistentes usando DataStore.
 *
 * Permite consultar y actualizar permisos específicos, incluyendo un acceso directo
 * para el permiso de notificaciones.
 *
 * @property context Contexto necesario para acceder al DataStore de preferencias.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0011"}, d2 = {"Lcom/arcaneia/spendwise/data/datastore/PermissionsDataStore;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "isGranted", "Lkotlinx/coroutines/flow/Flow;", "", "key", "Landroidx/datastore/preferences/core/Preferences$Key;", "isNotificationGranted", "()Lkotlinx/coroutines/flow/Flow;", "setNotificationGranted", "", "value", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class PermissionsDataStore {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    /**
     * Flujo que expone en tiempo real si el permiso de notificaciones ha sido otorgado.
     *
     * Internamente reutiliza [isGranted] para consultar la clave [PermissionsKeys.NOTIFICATION].
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isNotificationGranted = null;
    
    public PermissionsDataStore(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Obtiene el estado de un permiso almacenado bajo la clave indicada.
     *
     * @param key Clave del permiso almacenado en DataStore.
     * @return Un `Flow<Boolean>` que emite `true` o `false` dependiendo del estado del permiso.
     *        Si la clave no existe, devuelve `false` por defecto.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isGranted(@org.jetbrains.annotations.NotNull()
    androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> key) {
        return null;
    }
    
    /**
     * Flujo que expone en tiempo real si el permiso de notificaciones ha sido otorgado.
     *
     * Internamente reutiliza [isGranted] para consultar la clave [PermissionsKeys.NOTIFICATION].
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isNotificationGranted() {
        return null;
    }
    
    /**
     * Guarda el estado del permiso de notificaciones en DataStore.
     *
     * @param value `true` si el permiso ha sido otorgado, `false` de lo contrario.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setNotificationGranted(boolean value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}