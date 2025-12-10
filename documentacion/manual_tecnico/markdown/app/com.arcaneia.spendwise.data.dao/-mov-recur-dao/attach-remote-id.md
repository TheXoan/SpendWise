//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[MovRecurDao](index.md)/[attachRemoteId](attach-remote-id.md)

# attachRemoteId

[androidJvm]\
abstract suspend fun [attachRemoteId](attach-remote-id.md)(localId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html), remoteId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))

Asigna un identificador remoto (`remote_id`) a un movimiento recurrente que fue previamente insertado localmente.

#### Parameters

androidJvm

| | |
|---|---|
| localId | ID de Room del movimiento recurrente. |
| remoteId | ID asignado por PocketBase. |
