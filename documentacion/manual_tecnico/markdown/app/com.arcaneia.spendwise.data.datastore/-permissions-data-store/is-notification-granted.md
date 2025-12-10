//[app](../../../index.md)/[com.arcaneia.spendwise.data.datastore](../index.md)/[PermissionsDataStore](index.md)/[isNotificationGranted](is-notification-granted.md)

# isNotificationGranted

[androidJvm]\
val [isNotificationGranted](is-notification-granted.md): Flow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt;

Flujo que expone en tiempo real si el permiso de notificaciones ha sido otorgado.

Internamente reutiliza [isGranted](is-granted.md) para consultar la clave [PermissionsKeys.NOTIFICATION](../-permissions-keys/-n-o-t-i-f-i-c-a-t-i-o-n.md).
