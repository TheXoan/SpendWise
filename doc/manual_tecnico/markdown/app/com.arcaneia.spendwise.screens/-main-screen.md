//[app](../../index.md)/[com.arcaneia.spendwise.screens](index.md)/[MainScreen](-main-screen.md)

# MainScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [MainScreen](-main-screen.md)(navController: [NavController](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.html), movViewModel: [MovViewModel](../com.arcaneia.spendwise.data.model/-mov-view-model/index.md))

Pantalla principal de la aplicación, encargada de mostrar el balance del mes actual junto con accesos directos para registrar nuevos gastos e ingresos.

Esta pantalla:

- 
   Observa en tiempo real el balance mensual obtenido desde [MovViewModel](../com.arcaneia.spendwise.data.model/-mov-view-model/index.md).
- 
   Muestra el nombre de la aplicación como cabecera.
- 
   Presenta una tarjeta con el balance total de ingresos menos gastos del mes.
- 
   Proporciona botones para navegar a las pantallas de:
- 
   Registro de gastos (`expense_screen`)
- 
   Registro de ingresos (`income_screen`)

La pantalla está diseñada para ser el punto central de interacción del usuario, ofreciendo una visión clara del estado financiero actual y acciones rápidas.

#### Parameters

androidJvm

| | |
|---|---|
| navController | Controlador de navegación para redirigir a otras pantallas. |
| movViewModel | ViewModel utilizado para obtener el balance mensual. |
