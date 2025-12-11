//[app](../../../index.md)/[com.arcaneia.spendwise.data.repository](../index.md)/[MovRepository](index.md)/[getById](get-by-id.md)

# getById

[androidJvm]\
suspend fun [getById](get-by-id.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)): [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)?

Obtiene un movimiento por su ID local.

Esta función es esencial para operaciones de actualización sincronizada, ya que permite conservar el `remote_id` durante merges o sincronizaciones remotas.

#### Return

La entidad [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md) correspondiente, o `null` si no existe.

#### Parameters

androidJvm

| | |
|---|---|
| id | Identificador local del movimiento. |
