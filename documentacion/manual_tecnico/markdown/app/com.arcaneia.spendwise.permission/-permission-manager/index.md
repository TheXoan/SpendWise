//[app](../../../index.md)/[com.arcaneia.spendwise.permission](../index.md)/[PermissionManager](index.md)

# PermissionManager

[androidJvm]\
class [PermissionManager](index.md)

Clase encargada de gestionar la solicitud de permisos relacionados con notificaciones dentro de la aplicación.

Utiliza un mecanismo basado en Compose (`rememberLauncherForActivityResult`) junto con DataStore para persistir si el permiso fue concedido o no.

Esta clase abstrae toda la lógica necesaria para solicitar el permiso `POST_NOTIFICATIONS` en Android 13+ y para marcarlo como concedido automáticamente en versiones anteriores donde dicho permiso no es requerido.

## Constructors

| | |
|---|---|
| [PermissionManager](-permission-manager.md) | [androidJvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [GetNotificationPermission](-get-notification-permission.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [GetNotificationPermission](-get-notification-permission.md)()<br>Composable que solicita automáticamente el permiso de notificaciones al iniciarse. |
