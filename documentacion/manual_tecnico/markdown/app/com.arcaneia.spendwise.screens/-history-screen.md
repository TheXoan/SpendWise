//[app](../../index.md)/[com.arcaneia.spendwise.screens](index.md)/[HistoryScreen](-history-screen.md)

# HistoryScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [HistoryScreen](-history-screen.md)(navController: [NavController](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.html), movViewModel: [MovViewModel](../com.arcaneia.spendwise.data.model/-mov-view-model/index.md), categoriaViewModel: [CategoriaViewModel](../com.arcaneia.spendwise.data.model/-categoria-view-model/index.md))

Pantalla que muestra el historial filtrado de movimientos económicos.

Esta vista permite al usuario:

- 
   Seleccionar un año y mes para filtrar los movimientos.
- 
   Visualizar la lista de transacciones correspondientes.
- 
   Editar o eliminar movimientos mediante un menú contextual.

La pantalla observa distintos estados expuestos por [MovViewModel](../com.arcaneia.spendwise.data.model/-mov-view-model/index.md), incluyendo la lista de años, meses y movimientos disponibles.

#### Parameters

androidJvm

| | |
|---|---|
| navController | Controlador de navegación. |
| movViewModel | ViewModel encargado de gestionar movimientos. |
| categoriaViewModel | ViewModel encargado de gestionar categorías. |
