//[app](../../../index.md)/[com.arcaneia.spendwise.apis](../index.md)/[PocketBaseApi](index.md)/[deleteCategoria](delete-categoria.md)

# deleteCategoria

[androidJvm]\

@DELETE(value = &quot;api/collections/categoria/records/{id}&quot;)

abstract suspend fun [deleteCategoria](delete-categoria.md)(@Header(value = &quot;Authorization&quot;)auth: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @Path(value = &quot;id&quot;)id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))

Elimina una categoría remota.

#### Parameters

androidJvm

| | |
|---|---|
| auth | Token de autenticación. |
| id | ID remoto de la categoría a borrar. |
