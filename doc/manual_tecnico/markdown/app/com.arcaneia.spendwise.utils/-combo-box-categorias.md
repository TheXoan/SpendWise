//[app](../../index.md)/[com.arcaneia.spendwise.utils](index.md)/[ComboBoxCategorias](-combo-box-categorias.md)

# ComboBoxCategorias

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [ComboBoxCategorias](-combo-box-categorias.md)(viewModel: [CategoriaViewModel](../com.arcaneia.spendwise.data.model/-categoria-view-model/index.md), onCategoriaSeleccionada: ([Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html), internalShape: [Shape](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Shape.html))

ComboBox especializado para mostrar y seleccionar una **categoría** proveniente del `CategoriaViewModel`.

Este componente observa automáticamente el flujo de categorías y muestra todas las disponibles. Al seleccionar una categoría, se devuelve su **ID**, permitiendo integrar fácilmente esta selección dentro de la lógica del ViewModel o de la capa de datos.

## Características principales

- 
   Lectura automática de categorías desde un `StateFlow` del `CategoriaViewModel`.
- 
   Muestra el nombre de cada categoría y mantiene el estado de selección internamente.
- 
   Devuelve únicamente el **ID de la categoría** seleccionada.
- 
   Estilizado acorde al tema de la interfaz.
- 
   Permite personalizar la forma del campo mediante `internalShape`.

## Parámetros

#### Parameters

androidJvm

| | |
|---|---|
| viewModel | Instancia del `CategoriaViewModel` desde donde se obtienen las categorías. |
| onCategoriaSeleccionada | Callback invocado al seleccionar una categoría, devolviendo su ID. |
| internalShape | Forma personalizada (e.g., `RoundedCornerShape`) para el campo de texto. |
