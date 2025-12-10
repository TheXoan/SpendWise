//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[CategoriaDao](index.md)/[getAllWithRemoteId](get-all-with-remote-id.md)

# getAllWithRemoteId

[androidJvm]\
abstract suspend fun [getAllWithRemoteId](get-all-with-remote-id.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)&gt;

Obtiene todas las categorías que ya tienen asignado un `remote_id`, lo cual indica que ya han sido sincronizadas con el servidor.

Esta lista es fundamental para detectar eliminaciones remotas.

#### Return

Lista de categorías sincronizadas.
