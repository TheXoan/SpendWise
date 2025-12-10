//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[MovRecurDao](index.md)

# MovRecurDao

[androidJvm]\
interface [MovRecurDao](index.md)

DAO (Data Access Object) encargado de gestionar las operaciones relacionadas con movimientos recurrentes representados por la entidad [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md).

Incluye funciones para insertar, actualizar, eliminar y consultar movimientos recurrentes, así como obtener aquellos que deben renovarse según su fecha programada y métodos auxiliares para la sincronización con el servidor.

## Functions

| Name | Summary |
|---|---|
| [attachRemoteId](attach-remote-id.md) | [androidJvm]<br>abstract suspend fun [attachRemoteId](attach-remote-id.md)(localId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html), remoteId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))<br>Asigna un identificador remoto (`remote_id`) a un movimiento recurrente que fue previamente insertado localmente. |
| [delete](delete.md) | [androidJvm]<br>abstract suspend fun [delete](delete.md)(movRecur: [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md))<br>Elimina un movimiento recurrente existente. |
| [getAllMovRecur](get-all-mov-recur.md) | [androidJvm]<br>abstract fun [getAllMovRecur](get-all-mov-recur.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)&gt;&gt;<br>Obtiene todos los movimientos recurrentes ordenados por la fecha de la próxima renovación (`data_rnv`) en orden ascendente. |
| [getAllWithRemoteId](get-all-with-remote-id.md) | [androidJvm]<br>abstract suspend fun [getAllWithRemoteId](get-all-with-remote-id.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)&gt;<br>Obtiene todos los movimientos recurrentes que ya tienen un ID remoto asignado. Esta lista se utiliza para comparar contra el servidor y detectar borrados remotos. |
| [getById](get-by-id.md) | [androidJvm]<br>abstract suspend fun [getById](get-by-id.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)): [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)?<br>Busca un movimiento recurrente por su ID local (clave primaria de Room). |
| [getByRemoteId](get-by-remote-id.md) | [androidJvm]<br>abstract suspend fun [getByRemoteId](get-by-remote-id.md)(remoteId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)?<br>Busca un movimiento recurrente local usando su ID remoto (PocketBase ID). Se utiliza en la fase de fusión (merge) de la sincronización. |
| [getMovsToRenew](get-movs-to-renew.md) | [androidJvm]<br>abstract suspend fun [getMovsToRenew](get-movs-to-renew.md)(today: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)&gt;<br>Obtiene todos los movimientos recurrentes cuya fecha de renovación (`data_rnv`) sea anterior o igual a la fecha indicada. |
| [getPendingToUpload](get-pending-to-upload.md) | [androidJvm]<br>abstract suspend fun [getPendingToUpload](get-pending-to-upload.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)&gt;<br>Obtiene todos los movimientos recurrentes locales que aún no fueron subidos, es decir, aquellos cuyo `remote_id` es null. |
| [insert](insert.md) | [androidJvm]<br>abstract suspend fun [insert](insert.md)(movRecur: [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-long/index.html)<br>Inserta un movimiento recurrente en la base de datos. Si ya existe otro con el mismo ID, será reemplazado (`OnConflictStrategy.REPLACE`). |
| [update](update.md) | [androidJvm]<br>abstract suspend fun [update](update.md)(movRecur: [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md))<br>Actualiza los datos de un movimiento recurrente existente. |
