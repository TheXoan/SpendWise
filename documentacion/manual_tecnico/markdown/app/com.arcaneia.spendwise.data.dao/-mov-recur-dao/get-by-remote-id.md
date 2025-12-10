//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[MovRecurDao](index.md)/[getByRemoteId](get-by-remote-id.md)

# getByRemoteId

[androidJvm]\
abstract suspend fun [getByRemoteId](get-by-remote-id.md)(remoteId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)?

Busca un movimiento recurrente local usando su ID remoto (PocketBase ID). Se utiliza en la fase de fusión (merge) de la sincronización.

#### Return

El movimiento [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md) local que coincide, o `null`.

#### Parameters

androidJvm

| | |
|---|---|
| remoteId | El identificador de PocketBase del movimiento recurrente. |
