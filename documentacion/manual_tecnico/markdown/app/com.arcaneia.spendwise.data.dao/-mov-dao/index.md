//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[MovDao](index.md)

# MovDao

[androidJvm]\
interface [MovDao](index.md)

DAO (Data Access Object) responsable de gestionar todas las operaciones relacionadas con la entidad [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md), incluyendo:

- 
   Operaciones CRUD básicas.
- 
   Consultas avanzadas orientadas a estadísticas.
- 
   Integración completa con PocketBase para sincronización remota.
- 
   Soporte para notificaciones de movimientos recurrentes.

Este DAO constituye la principal capa de acceso a datos de los movimientos simples dentro de la base de datos local Room.

## Functions

| Name | Summary |
|---|---|
| [attachRemoteId](attach-remote-id.md) | [androidJvm]<br>abstract suspend fun [attachRemoteId](attach-remote-id.md)(localId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html), remoteId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))<br>Asocia un ID remoto asignado por PocketBase a un movimiento local. |
| [delete](delete.md) | [androidJvm]<br>abstract suspend fun [delete](delete.md)(mov: [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md))<br>Elimina un movimiento. |
| [deleteByRemoteId](delete-by-remote-id.md) | [androidJvm]<br>abstract suspend fun [deleteByRemoteId](delete-by-remote-id.md)(remoteId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))<br>Elimina un movimiento local cuyo `remote_id` coincide con un registro eliminado en PocketBase. |
| [getAll](get-all.md) | [androidJvm]<br>abstract suspend fun [getAll](get-all.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)&gt;<br>Devuelve todos los movimientos locales sin filtros. |
| [getAllFlow](get-all-flow.md) | [androidJvm]<br>abstract fun [getAllFlow](get-all-flow.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)&gt;&gt;<br>Devuelve todos los movimientos como flujo reactivo. |
| [getAllWithRemoteId](get-all-with-remote-id.md) | [androidJvm]<br>abstract suspend fun [getAllWithRemoteId](get-all-with-remote-id.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)&gt;<br>Obtiene todos los movimientos que ya han sido sincronizados, es decir, aquellos que tienen un `remote_id` asignado. |
| [getBalanceMesActual](get-balance-mes-actual.md) | [androidJvm]<br>abstract fun [getBalanceMesActual](get-balance-mes-actual.md)(): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-double/index.html)&gt;<br>Calcula el balance total del mes actual: |
| [getById](get-by-id.md) | [androidJvm]<br>abstract suspend fun [getById](get-by-id.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)): [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)?<br>Obtiene un movimiento por su ID local. |
| [getByRemoteId](get-by-remote-id.md) | [androidJvm]<br>abstract suspend fun [getByRemoteId](get-by-remote-id.md)(remoteId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)?<br>Obtiene un movimiento local usando su ID remoto. |
| [getByRenewHash](get-by-renew-hash.md) | [androidJvm]<br>abstract suspend fun [getByRenewHash](get-by-renew-hash.md)(hash: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)?<br>Busca un movimiento por su `renew_hash`, usado para detectar duplicados durante la sincronización de renovaciones recurrentes. |
| [getMonthsFromYear](get-months-from-year.md) | [androidJvm]<br>abstract fun [getMonthsFromYear](get-months-from-year.md)(year: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt;&gt;<br>Obtiene los meses que contienen movimientos para un año específico. |
| [getMovementsForYearMonth](get-movements-for-year-month.md) | [androidJvm]<br>abstract fun [getMovementsForYearMonth](get-movements-for-year-month.md)(year: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), month: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovWithCategory](../../com.arcaneia.spendwise.data.entity/-mov-with-category/index.md)&gt;&gt;<br>Obtiene todos los movimientos del año y mes especificados, acompañados del nombre de su categoría mediante JOIN. |
| [getPendingNotifications](get-pending-notifications.md) | [androidJvm]<br>abstract suspend fun [getPendingNotifications](get-pending-notifications.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)&gt;<br>Obtiene todos los movimientos que aún no han sido notificados. |
| [getPendingToUpload](get-pending-to-upload.md) | [androidJvm]<br>abstract suspend fun [getPendingToUpload](get-pending-to-upload.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)&gt;<br>Devuelve todos los movimientos que aún no han sido subidos al servidor, es decir, aquellos cuyo `remote_id` es `NULL`. |
| [getYearsWithValues](get-years-with-values.md) | [androidJvm]<br>abstract fun [getYearsWithValues](get-years-with-values.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt;&gt;<br>Obtiene la lista de años en los que existen movimientos registrados. |
| [insert](insert.md) | [androidJvm]<br>abstract suspend fun [insert](insert.md)(mov: [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-long/index.html)<br>Inserta un nuevo movimiento en la base de datos local. |
| [markAsNotified](mark-as-notified.md) | [androidJvm]<br>abstract suspend fun [markAsNotified](mark-as-notified.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html))<br>Marca un movimiento como notificado. |
| [update](update.md) | [androidJvm]<br>abstract suspend fun [update](update.md)(mov: [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md))<br>Actualiza un movimiento existente. |
