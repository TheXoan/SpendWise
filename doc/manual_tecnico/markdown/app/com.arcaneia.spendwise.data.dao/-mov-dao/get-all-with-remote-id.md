//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[MovDao](index.md)/[getAllWithRemoteId](get-all-with-remote-id.md)

# getAllWithRemoteId

[androidJvm]\
abstract suspend fun [getAllWithRemoteId](get-all-with-remote-id.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)&gt;

Obtiene todos los movimientos que ya han sido sincronizados, es decir, aquellos que tienen un `remote_id` asignado.

#### Return

Lista completa de movimientos sincronizados.
