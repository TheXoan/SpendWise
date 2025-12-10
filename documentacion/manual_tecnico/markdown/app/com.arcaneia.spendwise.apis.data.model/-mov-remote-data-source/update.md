//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[MovRemoteDataSource](index.md)/[update](update.md)

# update

[androidJvm]\
suspend fun [update](update.md)(movPBId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), mov: [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md), categoriaPBId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), movRecurPBId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?): [MovRecord](../../[root]/-mov-record/index.md)

Actualiza un movimiento ya existente en PocketBase.

Solo se envían los campos necesarios para mantener consistencia con el modelo local.

#### Return

Registro actualizado como [MovRecord](../../[root]/-mov-record/index.md).

#### Parameters

androidJvm

| | |
|---|---|
| movPBId | ID remoto del movimiento a actualizar. |
| mov | Objeto [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md) local con los valores actualizados. |
| categoriaPBId | ID remoto de la categoría asociada. |
| movRecurPBId | ID remoto del movimiento recurrente (si corresponde). |
