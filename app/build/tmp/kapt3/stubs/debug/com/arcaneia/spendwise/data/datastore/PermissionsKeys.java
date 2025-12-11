package com.arcaneia.spendwise.data.datastore;

/**
 * Objeto que contiene todas las claves utilizadas para almacenar permisos en DataStore.
 *
 * Actualmente incluye:
 * - [NOTIFICATION]: Indica si el permiso de notificaciones ha sido otorgado.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/arcaneia/spendwise/data/datastore/PermissionsKeys;", "", "<init>", "()V", "NOTIFICATION", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "getNOTIFICATION", "()Landroidx/datastore/preferences/core/Preferences$Key;", "app_debug"})
public final class PermissionsKeys {
    
    /**
     * Clave usada para almacenar el estado del permiso de notificaciones.
     */
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> NOTIFICATION = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.arcaneia.spendwise.data.datastore.PermissionsKeys INSTANCE = null;
    
    private PermissionsKeys() {
        super();
    }
    
    /**
     * Clave usada para almacenar el estado del permiso de notificaciones.
     */
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getNOTIFICATION() {
        return null;
    }
}