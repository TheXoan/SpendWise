//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[CategoriaDao](index.md)/[attachRemoteId](attach-remote-id.md)

# attachRemoteId

[androidJvm]\
abstract suspend fun [attachRemoteId](attach-remote-id.md)(localId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html), remoteId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))

Asigna un `remote_id` a una categoría almacenada en la base de datos local.

Este método se usa tras insertar la categoría en PocketBase, permitiendo enlazar la fila local con su identificación remota.

#### Parameters

androidJvm

| | |
|---|---|
| localId | ID autogenerado en Room. |
| remoteId | ID remoto asignado por PocketBase. |
