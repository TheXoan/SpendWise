//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[MovRecurRemoteDataSource](index.md)/[update](update.md)

# update

[androidJvm]\
suspend fun [update](update.md)(movRecurPBId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), movRecur: [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)): [MovRecurRecord](../-mov-recur-record/index.md)

Actualiza un movimiento recurrente existente en PocketBase.

Solo se envían los campos que pueden ser modificados, respetando la estructura aceptada por la API REST.

#### Return

El registro actualizado devuelto por el servidor.

#### Parameters

androidJvm

| | |
|---|---|
| movRecurPBId | ID remoto del movimiento recurrente que se actualizará. |
| movRecur | Entidad local con los cambios aplicados. |
