//[app](../../index.md)/[com.arcaneia.spendwise.screens](index.md)/[ExpenseScreen](-expense-screen.md)

# ExpenseScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [ExpenseScreen](-expense-screen.md)(navController: [NavController](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.html), movViewModel: [MovViewModel](../com.arcaneia.spendwise.data.model/-mov-view-model/index.md), categoriaViewModel: [CategoriaViewModel](../com.arcaneia.spendwise.data.model/-categoria-view-model/index.md))

Pantalla para registrar un nuevo gasto en la aplicación.

Esta vista permite al usuario:

- 
   Introducir un importe numérico.
- 
   Seleccionar una categoría existente.
- 
   Añadir una descripción opcional del gasto.
- 
   Guardar el movimiento en la base de datos mediante [MovViewModel](../com.arcaneia.spendwise.data.model/-mov-view-model/index.md).

La pantalla incorpora validaciones básicas:

- 
   El importe solo admite números y decimales.
- 
   La categoría es obligatoria.

Una vez guardado el gasto, se muestra un `Toast` confirmando la operación y se regresa a la pantalla anterior mediante `navController.popBackStack()`.

#### Parameters

androidJvm

| | |
|---|---|
| navController | Controlador de navegación para movernos entre pantallas. |
| movViewModel | ViewModel responsable de manejar operaciones relacionadas con movimientos. |
| categoriaViewModel | ViewModel para gestionar la lista de categorías disponibles. |
