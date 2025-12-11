package com.arcaneia.spendwise.data.datastore;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"%\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0004\u0010\u0005\u00a8\u0006\b"}, d2 = {"permissionsDataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "Landroid/content/Context;", "getPermissionsDataStore", "(Landroid/content/Context;)Landroidx/datastore/core/DataStore;", "permissionsDataStore$delegate", "Lkotlin/properties/ReadOnlyProperty;", "app_debug"})
public final class PermissionsDataStoreKt {
    
    /**
     * DataStore global utilizado para almacenar el estado de permisos de la aplicación.
     *
     * Se crea mediante `preferencesDataStore`, el cual genera automáticamente un
     * DataStore de tipo Preferences asociado al contexto.
     *
     * Nombre del archivo interno: `"permissions_prefs"`.
     */
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.properties.ReadOnlyProperty permissionsDataStore$delegate = null;
    
    /**
     * DataStore global utilizado para almacenar el estado de permisos de la aplicación.
     *
     * Se crea mediante `preferencesDataStore`, el cual genera automáticamente un
     * DataStore de tipo Preferences asociado al contexto.
     *
     * Nombre del archivo interno: `"permissions_prefs"`.
     */
    @org.jetbrains.annotations.NotNull()
    public static final androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> getPermissionsDataStore(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$permissionsDataStore) {
        return null;
    }
}