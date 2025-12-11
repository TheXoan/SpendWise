//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[MovRecurDao](index.md)/[getPendingToUpload](get-pending-to-upload.md)

# getPendingToUpload

[androidJvm]\
abstract suspend fun [getPendingToUpload](get-pending-to-upload.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)&gt;

Obtiene todos los movimientos recurrentes locales que aún no fueron subidos, es decir, aquellos cuyo `remote_id` es null.

Se utiliza en la fase de subida de la sincronización.

#### Return

Una lista de movimientos [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md) pendientes de subir.
