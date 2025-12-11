//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[MovRemoteDataSource](index.md)/[create](create.md)

# create

[androidJvm]\
suspend fun [create](create.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), mov: [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md), categoriaPBId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), movRecurPBId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?): [MovRecord](../../[root]/-mov-record/index.md)

Crea un nuevo movimiento remoto en PocketBase.

El cuerpo enviado incluye:

- 
   tipo, importe, fecha y categoría.
- 
   mov_recur_id (si proviene de una renovación).
- 
   renew_hash para evitar duplicados.

#### Return

El movimiento remoto creado como [MovRecord](../../[root]/-mov-record/index.md).

#### Parameters

androidJvm

| | |
|---|---|
| userId | ID del usuario propietario. |
| mov | Objeto [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md) local a sincronizar. |
| categoriaPBId | ID remoto de la categoría asociada. |
| movRecurPBId | ID remoto del movimiento recurrente (si lo hubiera). |
