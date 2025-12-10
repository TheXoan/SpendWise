//[app](../../index.md)/[com.arcaneia.spendwise.screens](index.md)/[EditMovDialog](-edit-mov-dialog.md)

# EditMovDialog

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [EditMovDialog](-edit-mov-dialog.md)(mov: [Mov](../com.arcaneia.spendwise.data.entity/-mov/index.md), onGuardar: ([Mov](../com.arcaneia.spendwise.data.entity/-mov/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html), onDismiss: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html), categoriaViewModel: [CategoriaViewModel](../com.arcaneia.spendwise.data.model/-categoria-view-model/index.md))

Diálogo que permite editar un movimiento existente.

Puede modificarse:

- 
   Nombre del movimiento
- 
   Importe
- 
   Fecha (mediante DatePicker)
- 
   Tipo (Ingreso/Gasto)
- 
   Categoría

#### Parameters

androidJvm

| | |
|---|---|
| mov | Movimiento a editar. |
| onGuardar | Callback que se ejecuta al guardar cambios. |
| onDismiss | Acción al cerrar el diálogo sin guardar. |
| categoriaViewModel | ViewModel para cargar categorías. |
