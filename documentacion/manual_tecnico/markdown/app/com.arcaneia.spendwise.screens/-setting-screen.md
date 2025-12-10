//[app](../../index.md)/[com.arcaneia.spendwise.screens](index.md)/[SettingScreen](-setting-screen.md)

# SettingScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [SettingScreen](-setting-screen.md)(navController: [NavController](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.html))

Pantalla de configuración de la aplicación.

Incluye:

- 
   Opciones de exportación e importación de datos mediante ZIP.
- 
   Control sobre el permiso de notificaciones persistido en DataStore.
- 
   Acceso directo a la página de permisos del sistema.

Funciones principales:

- 
   La importación usa SAF (System Access Framework) con `OpenDocument`.
- 
   La exportación usa `CreateDocument` para generar un archivo ZIP.
- 
   El permiso de notificaciones se solicita con `RequestPermission`.

#### Parameters

androidJvm

| | |
|---|---|
| navController | Control de navegación, no utilizado directamente en esta pantalla     pero necesario para mantener la coherencia del sistema de navegación. |
