//[app](../../../index.md)/[com.arcaneia.spendwise.data.datastore](../index.md)/[PermissionsDataStore](index.md)/[isGranted](is-granted.md)

# isGranted

[androidJvm]\
fun [isGranted](is-granted.md)(key: [Preferences.Key](https://developer.android.com/reference/kotlin/androidx/datastore/preferences/core/Preferences.Key.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt;): Flow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt;

Obtiene el estado de un permiso almacenado bajo la clave indicada.

#### Return

Un `Flow<Boolean>` que emite `true` o `false` dependiendo del estado del permiso.     Si la clave no existe, devuelve `false` por defecto.

#### Parameters

androidJvm

| | |
|---|---|
| key | Clave del permiso almacenado en DataStore. |
