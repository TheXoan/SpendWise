//[app](../../../index.md)/[com.arcaneia.spendwise.data.repository](../index.md)/[MovRepository](index.md)/[getAllFlow](get-all-flow.md)

# getAllFlow

[androidJvm]\
fun [getAllFlow](get-all-flow.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)&gt;&gt;

Devuelve un flujo reactivo con la lista completa de movimientos almacenados.

Este flujo se actualiza automáticamente cada vez que la tabla `mov` cambia, lo que permite que la UI sea completamente reactiva sin necesidad de refrescar manualmente.

#### Return

Un Flow que emite listas de [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md).
