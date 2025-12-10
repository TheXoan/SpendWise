//[app](../../index.md)/[com.arcaneia.spendwise.components](index.md)/[RecurrenceSpinner](-recurrence-spinner.md)

# RecurrenceSpinner

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [RecurrenceSpinner](-recurrence-spinner.md)(selectedRecurrence: [Recurrence](../com.arcaneia.spendwise.data.model/-recurrence/index.md)?, onRecurrenceSelected: ([Recurrence](../com.arcaneia.spendwise.data.model/-recurrence/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html))

Componente que muestra un selector desplegable (`Spinner`) para elegir una opción de la enumeración [Recurrence](../com.arcaneia.spendwise.data.model/-recurrence/index.md).

El botón principal muestra la periodicidad actualmente seleccionada o un texto predeterminado si no se ha seleccionado ninguna. Al hacer clic, se despliega un menú con todas las opciones disponibles.

#### Parameters

androidJvm

| | |
|---|---|
| selectedRecurrence | Valor actualmente seleccionado de tipo [Recurrence](../com.arcaneia.spendwise.data.model/-recurrence/index.md), o `null` si aún no hay selección. |
| onRecurrenceSelected | Callback que se ejecuta cuando el usuario selecciona una opción del menú, devolviendo la instancia seleccionada. |
