//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[CategoriaDao](index.md)/[getPendingToUpload](get-pending-to-upload.md)

# getPendingToUpload

[androidJvm]\
abstract suspend fun [getPendingToUpload](get-pending-to-upload.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)&gt;

Devuelve todas las categorías locales que aún no se han subido al servidor, es decir, aquellas cuyo `remote_id` es null.

Esta lista se utiliza durante la fase de *subida* en procesos de sincronización.

#### Return

Lista de categorías pendientes de sincronización.
