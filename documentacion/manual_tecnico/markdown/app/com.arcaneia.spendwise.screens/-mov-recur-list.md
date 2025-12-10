//[app](../../index.md)/[com.arcaneia.spendwise.screens](index.md)/[MovRecurList](-mov-recur-list.md)

# MovRecurList

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [MovRecurList](-mov-recur-list.md)(movsRecur: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovRecur](../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)&gt;, modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, viewModel: [MovRecurViewModel](../com.arcaneia.spendwise.data.model/-mov-recur-view-model/index.md))

Lista de movimientos recurrentes con soporte para:

- 
   Mostrar lista o mensaje vacío.
- 
   Abrir menú para editar/eliminar.
- 
   Abrir diálogo de edición.

#### Parameters

androidJvm

| | |
|---|---|
| movsRecur | Lista de movimientos recurrentes. |
| modifier | Modificador opcional. |
| viewModel | ViewModel encargado de las operaciones sobre la lista. |
