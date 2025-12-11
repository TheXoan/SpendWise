//[app](../../index.md)/[com.arcaneia.spendwise.data.datastore](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [PermissionsDataStore](-permissions-data-store/index.md) | [androidJvm]<br>class [PermissionsDataStore](-permissions-data-store/index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Clase encargada de gestionar la lectura y escritura de permisos persistentes usando DataStore. |
| [PermissionsKeys](-permissions-keys/index.md) | [androidJvm]<br>object [PermissionsKeys](-permissions-keys/index.md)<br>Objeto que contiene todas las claves utilizadas para almacenar permisos en DataStore. |
| [TokenDataStore](-token-data-store/index.md) | [androidJvm]<br>object [TokenDataStore](-token-data-store/index.md)<br>Objeto que gestiona la lectura y escritura del token y userId del usuario. |

## Properties

| Name | Summary |
|---|---|
| [permissionsDataStore](permissions-data-store.md) | [androidJvm]<br>val [Context](https://developer.android.com/reference/kotlin/android/content/Context.html).[permissionsDataStore](permissions-data-store.md): [DataStore](https://developer.android.com/reference/kotlin/androidx/datastore/core/DataStore.html)&lt;[Preferences](https://developer.android.com/reference/kotlin/androidx/datastore/preferences/core/Preferences.html)&gt;<br>DataStore global utilizado para almacenar el estado de permisos de la aplicación. |
| [tokenDataStore](token-data-store.md) | [androidJvm]<br>val [Context](https://developer.android.com/reference/kotlin/android/content/Context.html).[tokenDataStore](token-data-store.md): [DataStore](https://developer.android.com/reference/kotlin/androidx/datastore/core/DataStore.html)&lt;[Preferences](https://developer.android.com/reference/kotlin/androidx/datastore/preferences/core/Preferences.html)&gt;<br>DataStore de preferencias utilizado para almacenar información de sesión del usuario. |
