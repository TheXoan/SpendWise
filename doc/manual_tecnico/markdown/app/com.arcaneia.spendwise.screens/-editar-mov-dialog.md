//[app](../../index.md)/[com.arcaneia.spendwise.screens](index.md)/[EditarMovDialog](-editar-mov-dialog.md)

# EditarMovDialog

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [EditarMovDialog](-editar-mov-dialog.md)(mov: [MovRecur](../com.arcaneia.spendwise.data.entity/-mov-recur/index.md), onGuardar: ([MovRecur](../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html), onDismiss: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html))

Diálogo completo para editar un movimiento recurrente.

Permite modificar:

- 
   Nombre
- 
   Importe
- 
   Fecha de inicio (DatePicker)
- 
   Periodicidad (mediante [RecurrenceSpinner](../com.arcaneia.spendwise.components/-recurrence-spinner.md))
- 
   Tipo de movimiento (mediante [TypeMovSpinner](../com.arcaneia.spendwise.components/-type-mov-spinner.md))

La fecha de renovación se actualiza usando [calculateNextDate](../com.arcaneia.spendwise.utils/calculate-next-date.md).

#### Parameters

androidJvm

| | |
|---|---|
| mov | Movimiento recurrente a editar. |
| onGuardar | Acción que recibe el movimiento actualizado. |
| onDismiss | Acción al cerrar el diálogo sin guardar. |
