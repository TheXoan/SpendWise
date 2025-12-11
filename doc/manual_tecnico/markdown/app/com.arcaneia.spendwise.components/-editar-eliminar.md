//[app](../../index.md)/[com.arcaneia.spendwise.components](index.md)/[EditarEliminar](-editar-eliminar.md)

# EditarEliminar

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [EditarEliminar](-editar-eliminar.md)(title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), onEdit: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html), onDelete: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html), onDismiss: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html))

Muestra un cuadro de diálogo con opciones para **editar** o **eliminar** un elemento, junto con la posibilidad de **cerrar** el diálogo.

Este componente utiliza `AlertDialog` de Jetpack Compose y presenta un título, un texto descriptivo y dos botones de acción principales.

#### Parameters

androidJvm

| | |
|---|---|
| title | Título que se mostrará en la parte superior del diálogo. |
| onEdit | Acción que se ejecutará cuando el usuario presione el botón **Editar**. |
| onDelete | Acción que se ejecutará cuando el usuario presione el botón **Eliminar**. |
| onDismiss | Acción que se invoca cuando el diálogo se cierra sin seleccionar ninguna acción. |
