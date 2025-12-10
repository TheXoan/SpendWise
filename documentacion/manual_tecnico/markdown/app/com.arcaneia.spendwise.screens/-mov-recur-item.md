//[app](../../index.md)/[com.arcaneia.spendwise.screens](index.md)/[MovRecurItem](-mov-recur-item.md)

# MovRecurItem

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [MovRecurItem](-mov-recur-item.md)(mov: [MovRecur](../com.arcaneia.spendwise.data.entity/-mov-recur/index.md), onClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html))

Tarjeta que muestra la información principal de un movimiento recurrente.

Muestra:

- 
   Nombre
- 
   Fecha de inicio
- 
   Fecha de renovación
- 
   Periodo (semanal/mensual/anual)
- 
   Importe con color según tipo

#### Parameters

androidJvm

| | |
|---|---|
| mov | Objeto del movimiento recurrente. |
| onClick | Acción al pulsar el elemento. |
