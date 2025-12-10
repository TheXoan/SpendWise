//[app](../../index.md)/[com.arcaneia.spendwise.screens](index.md)/[MovRecurHistoryScreen](-mov-recur-history-screen.md)

# MovRecurHistoryScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [MovRecurHistoryScreen](-mov-recur-history-screen.md)(navController: [NavController](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.html), movRecurViewModel: [MovRecurViewModel](../com.arcaneia.spendwise.data.model/-mov-recur-view-model/index.md))

Pantalla que muestra la lista de movimientos recurrentes configurados por el usuario.

Esta pantalla:

- 
   Observa el flujo de movimientos recurrentes mediante [MovRecurViewModel](../com.arcaneia.spendwise.data.model/-mov-recur-view-model/index.md).
- 
   Muestra cada movimiento en una lista desplazable.
- 
   Permite editar o eliminar un movimiento mediante un diálogo contextual.
- 
   Permite crear nuevos movimientos recurrentes mediante un botón inferior.

Navega hacia:

- 
   `newMovRecur_screen` → pantalla de creación de movimientos recurrentes.

#### Parameters

androidJvm

| | |
|---|---|
| navController | Controlador de navegación. |
| movRecurViewModel | ViewModel para gestionar los movimientos recurrentes. |
