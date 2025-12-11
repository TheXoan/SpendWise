//[app](../../index.md)/[com.arcaneia.spendwise.data.datastore](index.md)/[tokenDataStore](token-data-store.md)

# tokenDataStore

[androidJvm]\
val [Context](https://developer.android.com/reference/kotlin/android/content/Context.html).[tokenDataStore](token-data-store.md): [DataStore](https://developer.android.com/reference/kotlin/androidx/datastore/core/DataStore.html)&lt;[Preferences](https://developer.android.com/reference/kotlin/androidx/datastore/preferences/core/Preferences.html)&gt;

DataStore de preferencias utilizado para almacenar información de sesión del usuario.

Este archivo define una instancia de DataStore asociada al contexto, donde se guardan:

- 
   El token de autenticación.
- 
   El ID del usuario.

El almacenamiento se realiza de forma asíncrona y segura, usando flujos (`Flow`) para exponer los datos reaccionando a cambios en tiempo real.
