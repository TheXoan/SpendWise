//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[CategoriaDao](index.md)/[getByRemoteId](get-by-remote-id.md)

# getByRemoteId

[androidJvm]\
abstract suspend fun [getByRemoteId](get-by-remote-id.md)(remoteId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)?

Busca una categoría local mediante su identificador remoto (`remote_id`).

Se usa durante el proceso de fusión (merge) entre los datos remotos y locales para evitar duplicados.

#### Return

La categoría local correspondiente o `null` si no existe.

#### Parameters

androidJvm

| | |
|---|---|
| remoteId | ID asignado por PocketBase. |
