//[app](../../index.md)/[com.arcaneia.spendwise.screens](index.md)/[IncomeScreen](-income-screen.md)

# IncomeScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [IncomeScreen](-income-screen.md)(navController: [NavController](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.html), movViewModel: [MovViewModel](../com.arcaneia.spendwise.data.model/-mov-view-model/index.md), categoriaViewModel: [CategoriaViewModel](../com.arcaneia.spendwise.data.model/-categoria-view-model/index.md))

Pantalla encargada de registrar un nuevo ingreso en la aplicación.

Esta vista permite al usuario:

- 
   Introducir un importe numérico.
- 
   Seleccionar una categoría existente.
- 
   Añadir una descripción opcional.
- 
   Guardar el ingreso utilizando [MovViewModel](../com.arcaneia.spendwise.data.model/-mov-view-model/index.md).

La pantalla aplica validaciones básicas:

- 
   Solo se permiten números y decimales en el importe.
- 
   La categoría es obligatoria.

Una vez guardado el ingreso:

- 
   Se muestra un mensaje `Toast` confirmando la operación.
- 
   Se vuelve a la pantalla anterior usando `navController.popBackStack()`.

Para evitar dobles inserciones accidentales, se usa la bandera `isSaving` que deshabilita múltiples pulsaciones del botón.

#### Parameters

androidJvm

| | |
|---|---|
| navController | Controlador de navegación para cambiar de pantallas. |
| movViewModel | ViewModel encargado de gestionar movimientos. |
| categoriaViewModel | ViewModel encargado de cargar las categorías existentes. |
