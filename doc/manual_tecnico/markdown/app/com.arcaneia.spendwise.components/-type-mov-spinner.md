//[app](../../index.md)/[com.arcaneia.spendwise.components](index.md)/[TypeMovSpinner](-type-mov-spinner.md)

# TypeMovSpinner

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [TypeMovSpinner](-type-mov-spinner.md)(selectedTypeMov: [TypeMov](../com.arcaneia.spendwise.data.model/-type-mov/index.md)?, onSelectedTypeMov: ([TypeMov](../com.arcaneia.spendwise.data.model/-type-mov/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html))

Componente que muestra un selector desplegable (`Spinner`) para elegir una opción de la enumeración [TypeMov](../com.arcaneia.spendwise.data.model/-type-mov/index.md).

El botón principal muestra el tipo de movimiento actualmente seleccionado o un texto predeterminado si aún no hay selección. Al hacer clic, se despliega un menú con todas las opciones disponibles.

#### Parameters

androidJvm

| | |
|---|---|
| selectedTypeMov | Valor actualmente seleccionado de tipo [TypeMov](../com.arcaneia.spendwise.data.model/-type-mov/index.md), o `null` si aún no hay selección. |
| onSelectedTypeMov | Callback que se ejecuta cuando el usuario selecciona una opción del menú, devolviendo la instancia seleccionada. |
