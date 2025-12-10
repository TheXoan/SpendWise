//[app](../../../index.md)/[com.arcaneia.spendwise.data.model](../index.md)/[MovRecurViewModel](index.md)

# MovRecurViewModel

[androidJvm]\
class [MovRecurViewModel](index.md)(repository: [MovRecurRepository](../../com.arcaneia.spendwise.data.repository/-mov-recur-repository/index.md), remoteDataSource: [MovRecurRemoteDataSource](../../com.arcaneia.spendwise.apis.data.model/-mov-recur-remote-data-source/index.md), dao: [MovRecurDao](../../com.arcaneia.spendwise.data.dao/-mov-recur-dao/index.md), appContext: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

ViewModel encargado de gestionar los **movimientos recurrentes** (`MovRecur`) aplicando un enfoque **offline-first con sincronización instantánea**.

La lógica principal del ViewModel es:

- 
   Aplicar todos los cambios inmediatamente en la base de datos local (Room).
- 
   Sincronizar la operación con PocketBase en segundo plano.
- 
   Mantener un `StateFlow` con la lista de movimientos recurrentes para la UI.

De este modo, la interfaz se mantiene fluida y consistente incluso en modo offline.

### Flujo de sincronización aplicado:

- 
   **Insert:** local → remoto → actualización con `remote_id`.
- 
   **Update:** local → remoto (solo si tiene `remote_id`).
- 
   **Delete:** remoto → local.

## Constructors

| | |
|---|---|
| [MovRecurViewModel](-mov-recur-view-model.md) | [androidJvm]<br>constructor(repository: [MovRecurRepository](../../com.arcaneia.spendwise.data.repository/-mov-recur-repository/index.md), remoteDataSource: [MovRecurRemoteDataSource](../../com.arcaneia.spendwise.apis.data.model/-mov-recur-remote-data-source/index.md), dao: [MovRecurDao](../../com.arcaneia.spendwise.data.dao/-mov-recur-dao/index.md), appContext: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Properties

| Name | Summary |
|---|---|
| [movRecur](mov-recur.md) | [androidJvm]<br>val [movRecur](mov-recur.md): StateFlow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)&gt;&gt;<br>Flujo observable por la UI que expone la lista de movimientos recurrentes. |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../-mov-view-model/index.md#383812252%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-mov-view-model/index.md#383812252%2FFunctions%2F-912451524)(closeable: [AutoCloseable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-auto-closeable/index.html))<br>fun [addCloseable](../-mov-view-model/index.md#1722490497%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), closeable: [AutoCloseable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-auto-closeable/index.html)) |
| [delete](delete.md) | [androidJvm]<br>fun [delete](delete.md)(m: [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)): Job<br>Elimina un movimiento recurrente tanto local como remotamente. |
| [getCloseable](../-mov-view-model/index.md#1102255800%2FFunctions%2F-912451524) | [androidJvm]<br>fun &lt;[T](../-mov-view-model/index.md#1102255800%2FFunctions%2F-912451524) : [AutoCloseable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-auto-closeable/index.html)&gt; [getCloseable](../-mov-view-model/index.md#1102255800%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [T](../-mov-view-model/index.md#1102255800%2FFunctions%2F-912451524)? |
| [insert](insert.md) | [androidJvm]<br>fun [insert](insert.md)(m: [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)): Job<br>Inserta un movimiento recurrente **localmente de forma inmediata** y luego intenta sincronizarlo con el servidor PocketBase. |
| [update](update.md) | [androidJvm]<br>fun [update](update.md)(m: [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)): Job<br>Actualiza un movimiento recurrente tanto en la base de datos local como en PocketBase. |
