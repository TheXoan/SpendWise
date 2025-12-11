//[app](../../index.md)/[com.arcaneia.spendwise.screens](index.md)/[SplashScreen](-splash-screen.md)

# SplashScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [SplashScreen](-splash-screen.md)(navController: [NavController](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.html), authViewModel: [AuthViewModel](../com.arcaneia.spendwise.data.model/-auth-view-model/index.md))

Pantalla inicial (Splash Screen) que muestra el logotipo de la aplicación y verifica si el usuario está autenticado antes de continuar.

Esta pantalla:

- 
   Se muestra al inicio de la app.
- 
   Observa el estado de autenticación desde [AuthViewModel](../com.arcaneia.spendwise.data.model/-auth-view-model/index.md).
- 
   Ejecuta la sincronización de categorías mediante categoriaSyncRepository si el usuario está autenticado.
- 
   Espera 2 segundos para permitir carga inicial, sincronización y transición visual suave.
- 
   Redirige automáticamente a [AppScreens.MainScreen](../com.arcaneia.spendwise.navigation/-app-screens/-main-screen/index.md) eliminando el Splash del backstack.

#### Parameters

androidJvm

| | |
|---|---|
| navController | Controlador de navegación utilizado para mover al usuario a la pantalla principal. |
| authViewModel | ViewModel encargado de gestionar la autenticación del usuario. |
