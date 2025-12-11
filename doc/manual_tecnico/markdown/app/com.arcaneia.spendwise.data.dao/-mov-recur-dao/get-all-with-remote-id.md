//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[MovRecurDao](index.md)/[getAllWithRemoteId](get-all-with-remote-id.md)

# getAllWithRemoteId

[androidJvm]\
abstract suspend fun [getAllWithRemoteId](get-all-with-remote-id.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)&gt;

Obtiene todos los movimientos recurrentes que ya tienen un ID remoto asignado. Esta lista se utiliza para comparar contra el servidor y detectar borrados remotos.

#### Return

Una lista de movimientos [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md) sincronizados.
