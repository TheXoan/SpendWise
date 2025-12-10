//[app](../../index.md)/[com.arcaneia.spendwise.screens](index.md)/[TransaccionItem](-transaccion-item.md)

# TransaccionItem

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [TransaccionItem](-transaccion-item.md)(movWithCategory: [MovWithCategory](../com.arcaneia.spendwise.data.entity/-mov-with-category/index.md), onClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html))

Elemento visual que representa un movimiento dentro de la lista del historial.

Muestra:

- 
   Descripción del movimiento
- 
   Fecha formateada
- 
   Categoría
- 
   Importe con color verde (ingreso) o rojo (gasto)

#### Parameters

androidJvm

| | |
|---|---|
| movWithCategory | Movimiento junto con el nombre de su categoría. |
| onClick | Acción al pulsar el elemento (abrir opciones). |
