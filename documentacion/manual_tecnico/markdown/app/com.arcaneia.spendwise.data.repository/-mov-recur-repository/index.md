//[app](../../../index.md)/[com.arcaneia.spendwise.data.repository](../index.md)/[MovRecurRepository](index.md)

# MovRecurRepository

[androidJvm]\
class [MovRecurRepository](index.md)(movRecurDao: [MovRecurDao](../../com.arcaneia.spendwise.data.dao/-mov-recur-dao/index.md), movDao: [MovDao](../../com.arcaneia.spendwise.data.dao/-mov-dao/index.md), appContext: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))

Repositorio encargado de gestionar los **movimientos recurrentes** y su conversión automática a movimientos simples.

Este componente centraliza:

### 🔄 **Renovación automática de movimientos recurrentes**

Para cada registro de [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md) cuya fecha `data_rnv` sea menor o igual a la fecha actual:

1. 
   Se generan uno o varios [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md) locales según corresponda.
2. 
   Los movimientos generan un **renew_hash determinista**, lo cual permite:
3. - 
      Evitar duplicados entre dispositivos durante la sincronización.
   - 
      Garantizar que cada renovación sea única y rastreable.
4. 
   Se actualiza `data_rnv` avanzando tantas veces como sea necesario mediante `calculateNextDate()`.
5. 
   Se actualiza el movimiento recurrente tanto **localmente** como en **PocketBase** (si tiene `remote_id`).

### 🌐 **Sincronización remota**

Tras actualizar la fecha de renovación, si el movimiento recurrente tiene un ID remoto, se envía la actualización a PocketBase mediante [MovRecurRemoteDataSource](../../com.arcaneia.spendwise.apis.data.model/-mov-recur-remote-data-source/index.md).

Si la actualización remota falla (por ejemplo, sin conexión), el estado local quedará corregido y la próxima sincronización lo alineará con el servidor.

### 🧩 Dependencias

## Constructors

| | |
|---|---|
| [MovRecurRepository](-mov-recur-repository.md) | [androidJvm]<br>constructor(movRecurDao: [MovRecurDao](../../com.arcaneia.spendwise.data.dao/-mov-recur-dao/index.md), movDao: [MovDao](../../com.arcaneia.spendwise.data.dao/-mov-dao/index.md), appContext: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [androidJvm]<br>suspend fun [delete](delete.md)(movRecur: [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md))<br>Elimina un movimiento recurrente local. |
| [getAllMovRecur](get-all-mov-recur.md) | [androidJvm]<br>fun [getAllMovRecur](get-all-mov-recur.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)&gt;&gt;<br>Devuelve un flujo reactivo con todos los movimientos recurrentes almacenados en la base de datos. |
| [getPendingRenewalsCount](get-pending-renewals-count.md) | [androidJvm]<br>suspend fun [getPendingRenewalsCount](get-pending-renewals-count.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)<br>Devuelve cuántos movimientos recurrentes deberían renovarse hoy. |
| [insert](insert.md) | [androidJvm]<br>suspend fun [insert](insert.md)(movRecur: [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md))<br>Inserta un movimiento recurrente en la base de datos local. |
| [processRenewals](process-renewals.md) | [androidJvm]<br>suspend fun [processRenewals](process-renewals.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)&gt;<br>Procesa todas las renovaciones pendientes de movimientos recurrentes. |
| [update](update.md) | [androidJvm]<br>suspend fun [update](update.md)(movRecur: [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md))<br>Actualiza el contenido de un movimiento recurrente local. |
