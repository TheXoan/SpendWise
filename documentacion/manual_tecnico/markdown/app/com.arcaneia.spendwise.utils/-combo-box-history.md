//[app](../../index.md)/[com.arcaneia.spendwise.utils](index.md)/[ComboBoxHistory](-combo-box-history.md)

# ComboBoxHistory

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [ComboBoxHistory](-combo-box-history.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, label: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), options: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt;, selected: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?, onSelected: ([String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html), enabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) = true)

ComboBox reutilizable para la selección de **años o meses** dentro de la pantalla de historial.

Este componente es genérico y permite recibir una lista de valores de tipo `String`, que pueden representar tanto años (e.g., `"2023"`, `"2024"`) como meses numéricos (e.g., `"01"`, `"02"`).

## Características principales

- 
   Traducción automática de números de mes a su nombre correspondiente según el **idioma del dispositivo** utilizando `Locale.getDefault()`.
- 
   Detección inteligente:
- 
   Si el valor está entre **1 y 12**, se interpreta como mes y se traduce.
- 
   Si el valor no representa un mes válido (e.g. un año), se muestra tal cual.
- 
   Componente visual estilizado según el tema de la aplicación.
- 
   Manejo interno del estado de expansión del menú desplegable.
- 
   Admite modo deshabilitado (`enabled = false`).

## Parámetros

#### Parameters

androidJvm

| | |
|---|---|
| modifier | Modificador opcional para personalizar la apariencia externa del componente. |
| label | Texto mostrado como placeholder cuando no hay selección. |
| options | Lista de elementos a mostrar en el menú desplegable. Pueden ser meses o años. |
| selected | Elemento actualmente seleccionado. Puede ser nulo. |
| onSelected | Callback invocado cuando el usuario selecciona un valor. Devuelve el valor elegido. |
| enabled | Permite habilitar o deshabilitar la interacción con el ComboBox. |
