//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[MovRecurDao](index.md)/[getAllMovRecur](get-all-mov-recur.md)

# getAllMovRecur

[androidJvm]\
abstract fun [getAllMovRecur](get-all-mov-recur.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)&gt;&gt;

Obtiene todos los movimientos recurrentes ordenados por la fecha de la próxima renovación (`data_rnv`) en orden ascendente.

#### Return

Un Flow de [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html) de [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md) que emite la lista completa cada vez que cambia la tabla.
