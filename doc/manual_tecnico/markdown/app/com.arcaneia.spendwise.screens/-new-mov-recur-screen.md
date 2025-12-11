//[app](../../index.md)/[com.arcaneia.spendwise.screens](index.md)/[NewMovRecurScreen](-new-mov-recur-screen.md)

# NewMovRecurScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [NewMovRecurScreen](-new-mov-recur-screen.md)(navController: [NavController](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.html), movRecurViewModel: [MovRecurViewModel](../com.arcaneia.spendwise.data.model/-mov-recur-view-model/index.md))

Pantalla para crear un nuevo movimiento recurrente.

Esta pantalla permite al usuario:

- 
   Introducir importe, nombre, tipo y periodicidad.
- 
   Seleccionar la fecha inicial mediante un DatePicker.
- 
   Guardar el movimiento recurrente en la base de datos.

Funcionalidad adicional:

- 
   Solicita el permiso de notificaciones usando [PermissionManager](../com.arcaneia.spendwise.permission/-permission-manager/index.md).
- 
   Calcula automáticamente la próxima fecha de renovación usando [calculateNextDate](../com.arcaneia.spendwise.utils/calculate-next-date.md).

#### Parameters

androidJvm

| | |
|---|---|
| navController | Controlador de navegación para volver atrás tras guardar. |
| movRecurViewModel | ViewModel encargado de gestionar los movimientos recurrentes. |
