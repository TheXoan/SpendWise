//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[MovRecurDao](index.md)/[getById](get-by-id.md)

# getById

[androidJvm]\
abstract suspend fun [getById](get-by-id.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)): [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)?

Busca un movimiento recurrente por su ID local (clave primaria de Room).

#### Return

El movimiento [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md) correspondiente, o `null` si no se encuentra.

#### Parameters

androidJvm

| | |
|---|---|
| id | El ID local (Int) del movimiento recurrente. |
