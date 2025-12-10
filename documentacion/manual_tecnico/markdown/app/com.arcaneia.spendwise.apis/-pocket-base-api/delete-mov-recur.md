//[app](../../../index.md)/[com.arcaneia.spendwise.apis](../index.md)/[PocketBaseApi](index.md)/[deleteMovRecur](delete-mov-recur.md)

# deleteMovRecur

[androidJvm]\

@DELETE(value = &quot;api/collections/mov_recur/records/{id}&quot;)

abstract suspend fun [deleteMovRecur](delete-mov-recur.md)(@Header(value = &quot;Authorization&quot;)auth: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @Path(value = &quot;id&quot;)id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))

Elimina un movimiento recurrente de PocketBase.

#### Parameters

androidJvm

| | |
|---|---|
| auth | Token de autenticación. |
| id | ID remoto del registro a eliminar. |
