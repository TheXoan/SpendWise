//[app](../../index.md)/[com.arcaneia.spendwise.data.datastore](index.md)/[permissionsDataStore](permissions-data-store.md)

# permissionsDataStore

[androidJvm]\
val [Context](https://developer.android.com/reference/kotlin/android/content/Context.html).[permissionsDataStore](permissions-data-store.md): [DataStore](https://developer.android.com/reference/kotlin/androidx/datastore/core/DataStore.html)&lt;[Preferences](https://developer.android.com/reference/kotlin/androidx/datastore/preferences/core/Preferences.html)&gt;

DataStore global utilizado para almacenar el estado de permisos de la aplicación.

Se crea mediante `preferencesDataStore`, el cual genera automáticamente un DataStore de tipo Preferences asociado al contexto.

Nombre del archivo interno: `"permissions_prefs"`.
