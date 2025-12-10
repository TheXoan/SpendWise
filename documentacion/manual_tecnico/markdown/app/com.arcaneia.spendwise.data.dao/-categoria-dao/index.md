//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[CategoriaDao](index.md)

# CategoriaDao

[androidJvm]\
interface [CategoriaDao](index.md)

DAO (Data Access Object) para gestionar todas las operaciones relacionadas con la entidad [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md) dentro de la base de datos Room.

Incluye operaciones CRUD básicas y un conjunto de métodos especializados para la sincronización con el backend PocketBase, permitiendo mapear categorías locales con sus equivalentes remotos mediante `remote_id`.

Este DAO está optimizado para funcionar en entornos offline-first, garantizando que cada categoría pueda ser insertada, actualizada, consultada o marcada como sincronizada dependiendo del estado de la base de datos local y remota.

## Functions

| Name | Summary |
|---|---|
| [attachRemoteId](attach-remote-id.md) | [androidJvm]<br>abstract suspend fun [attachRemoteId](attach-remote-id.md)(localId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html), remoteId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))<br>Asigna un `remote_id` a una categoría almacenada en la base de datos local. |
| [delete](delete.md) | [androidJvm]<br>abstract suspend fun [delete](delete.md)(categoria: [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md))<br>Elimina una categoría de la base de datos. |
| [deleteById](delete-by-id.md) | [androidJvm]<br>abstract suspend fun [deleteById](delete-by-id.md)(categoriaId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html))<br>Elimina una categoría según su ID. |
| [getAllCategories](get-all-categories.md) | [androidJvm]<br>abstract fun [getAllCategories](get-all-categories.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)&gt;&gt;<br>Recupera todas las categorías excepto la de ID 1, que corresponde a la categoría reservada **&quot;Recurrente&quot;**. |
| [getAllWithRemoteId](get-all-with-remote-id.md) | [androidJvm]<br>abstract suspend fun [getAllWithRemoteId](get-all-with-remote-id.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)&gt;<br>Obtiene todas las categorías que ya tienen asignado un `remote_id`, lo cual indica que ya han sido sincronizadas con el servidor. |
| [getById](get-by-id.md) | [androidJvm]<br>abstract suspend fun [getById](get-by-id.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)): [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)?<br>Busca una categoría por su ID local. |
| [getByRemoteId](get-by-remote-id.md) | [androidJvm]<br>abstract suspend fun [getByRemoteId](get-by-remote-id.md)(remoteId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)?<br>Busca una categoría local mediante su identificador remoto (`remote_id`). |
| [getPendingToUpload](get-pending-to-upload.md) | [androidJvm]<br>abstract suspend fun [getPendingToUpload](get-pending-to-upload.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)&gt;<br>Devuelve todas las categorías locales que aún no se han subido al servidor, es decir, aquellas cuyo `remote_id` es null. |
| [insert](insert.md) | [androidJvm]<br>abstract suspend fun [insert](insert.md)(categoria: [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-long/index.html)<br>Inserta una nueva categoría en la base de datos. |
| [update](update.md) | [androidJvm]<br>abstract suspend fun [update](update.md)(categoria: [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md))<br>Actualiza los datos de una categoría existente. |
