//[app](../../../index.md)/[com.arcaneia.spendwise.apis](../index.md)/[PocketBaseApi](index.md)/[deleteMov](delete-mov.md)

# deleteMov

[androidJvm]\

@DELETE(value = &quot;api/collections/mov/records/{id}&quot;)

abstract suspend fun [deleteMov](delete-mov.md)(@Header(value = &quot;Authorization&quot;)auth: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @Path(value = &quot;id&quot;)id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))

Elimina un movimiento simple remoto.

#### Parameters

androidJvm

| | |
|---|---|
| auth | Token de autenticación. |
| id | Identificador remoto del movimiento. |
