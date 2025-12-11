//[app](../../../index.md)/[com.arcaneia.spendwise.data.datastore](../index.md)/[PermissionsDataStore](index.md)

# PermissionsDataStore

[androidJvm]\
class [PermissionsDataStore](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))

Clase encargada de gestionar la lectura y escritura de permisos persistentes usando DataStore.

Permite consultar y actualizar permisos específicos, incluyendo un acceso directo para el permiso de notificaciones.

## Constructors

| | |
|---|---|
| [PermissionsDataStore](-permissions-data-store.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Properties

| Name | Summary |
|---|---|
| [isNotificationGranted](is-notification-granted.md) | [androidJvm]<br>val [isNotificationGranted](is-notification-granted.md): Flow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt;<br>Flujo que expone en tiempo real si el permiso de notificaciones ha sido otorgado. |

## Functions

| Name | Summary |
|---|---|
| [isGranted](is-granted.md) | [androidJvm]<br>fun [isGranted](is-granted.md)(key: [Preferences.Key](https://developer.android.com/reference/kotlin/androidx/datastore/preferences/core/Preferences.Key.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt;): Flow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt;<br>Obtiene el estado de un permiso almacenado bajo la clave indicada. |
| [setNotificationGranted](set-notification-granted.md) | [androidJvm]<br>suspend fun [setNotificationGranted](set-notification-granted.md)(value: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html))<br>Guarda el estado del permiso de notificaciones en DataStore. |
