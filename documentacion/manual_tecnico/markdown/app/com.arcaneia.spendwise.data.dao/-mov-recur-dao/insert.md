//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[MovRecurDao](index.md)/[insert](insert.md)

# insert

[androidJvm]\
abstract suspend fun [insert](insert.md)(movRecur: [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-long/index.html)

Inserta un movimiento recurrente en la base de datos. Si ya existe otro con el mismo ID, será reemplazado (`OnConflictStrategy.REPLACE`).

#### Return

El ID autogenerado del registro insertado.

#### Parameters

androidJvm

| | |
|---|---|
| movRecur | Instancia del movimiento recurrente que se desea insertar. |
