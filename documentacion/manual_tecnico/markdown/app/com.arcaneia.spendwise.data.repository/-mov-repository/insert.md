//[app](../../../index.md)/[com.arcaneia.spendwise.data.repository](../index.md)/[MovRepository](index.md)/[insert](insert.md)

# insert

[androidJvm]\
suspend fun [insert](insert.md)(mov: [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-long/index.html)

Inserta un nuevo movimiento en la base de datos local.

A diferencia de otras inserciones, devuelve explícitamente el ID autogenerado para permitir que el ViewModel pueda asociarle posteriormente un `remote_id` cuando se sincronice con PocketBase.

#### Return

El ID generado por Room tras la inserción.

#### Parameters

androidJvm

| | |
|---|---|
| mov | Movimiento a insertar. |
