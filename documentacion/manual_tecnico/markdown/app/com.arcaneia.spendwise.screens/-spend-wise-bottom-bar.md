//[app](../../index.md)/[com.arcaneia.spendwise.screens](index.md)/[SpendWiseBottomBar](-spend-wise-bottom-bar.md)

# SpendWiseBottomBar

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [SpendWiseBottomBar](-spend-wise-bottom-bar.md)(navController: [NavController](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.html), currentDestination: [NavDestination](https://developer.android.com/reference/kotlin/androidx/navigation/NavDestination.html)?)

Barra de navegación inferior (BottomBar) de la aplicación.

Esta barra:

- 
   Cambia de color el ítem seleccionado.
- 
   Navega entre diferentes pantallas usando rutas definidas en [AppScreens](../com.arcaneia.spendwise.navigation/-app-screens/index.md).
- 
   Mantiene el estado de navegación (restoreState / saveState).
- 
   Limita la pila de navegación mediante `popUpTo` hacia el destino inicial.

#### Parameters

androidJvm

| | |
|---|---|
| navController | Controlador de navegación principal. |
| currentDestination | Destino actual utilizado para determinar qué elemento está seleccionado. |
