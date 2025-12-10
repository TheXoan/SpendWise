//[app](../../../index.md)/[com.arcaneia.spendwise.permission](../index.md)/[PermissionManager](index.md)/[GetNotificationPermission](-get-notification-permission.md)

# GetNotificationPermission

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [GetNotificationPermission](-get-notification-permission.md)()

Composable que solicita automáticamente el permiso de notificaciones al iniciarse.

Funciona del siguiente modo:

- 
   En **Android 13+ (API 33)**: lanza un diálogo de sistema solicitando el permiso `POST_NOTIFICATIONS`.
- 
   En versiones anteriores: guarda automáticamente el permiso como concedido.

Sin importar el resultado, este se guarda en DataStore mediante [PermissionsDataStore](../../com.arcaneia.spendwise.data.datastore/-permissions-data-store/index.md).

Este composable no dibuja nada en pantalla: solo gestiona la solicitud del permiso.
