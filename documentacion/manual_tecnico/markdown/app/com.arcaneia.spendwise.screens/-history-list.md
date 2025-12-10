//[app](../../index.md)/[com.arcaneia.spendwise.screens](index.md)/[HistoryList](-history-list.md)

# HistoryList

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [HistoryList](-history-list.md)(transacciones: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovWithCategory](../com.arcaneia.spendwise.data.entity/-mov-with-category/index.md)&gt;, modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, viewModel: [MovViewModel](../com.arcaneia.spendwise.data.model/-mov-view-model/index.md), categoriaViewModel: [CategoriaViewModel](../com.arcaneia.spendwise.data.model/-categoria-view-model/index.md))

Lista de movimientos filtrados según los parámetros seleccionados.

Permite:

- 
   Mostrar un mensaje cuando no hay datos.
- 
   Mostrar cada transacción usando [TransaccionItem](-transaccion-item.md).
- 
   Abrir un menú contextual para editar o eliminar.
- 
   Mostrar un diálogo para editar la transacción seleccionada.

#### Parameters

androidJvm

| | |
|---|---|
| transacciones | Lista de movimientos con categoría. |
| modifier | Modificador para personalización externa. |
| viewModel | ViewModel para realizar operaciones CRUD sobre movimientos. |
| categoriaViewModel | ViewModel para gestionar categorías. |
