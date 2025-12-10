//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[MovDao](index.md)/[getPendingToUpload](get-pending-to-upload.md)

# getPendingToUpload

[androidJvm]\
abstract suspend fun [getPendingToUpload](get-pending-to-upload.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)&gt;

Devuelve todos los movimientos que aún no han sido subidos al servidor, es decir, aquellos cuyo `remote_id` es `NULL`.

#### Return

Lista de movimientos pendientes de sincronizar.
