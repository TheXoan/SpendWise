//[app](../../../index.md)/[com.arcaneia.spendwise.apis](../index.md)/[PocketBaseApi](index.md)/[updateCategoria](update-categoria.md)

# updateCategoria

[androidJvm]\

@PATCH(value = &quot;api/collections/categoria/records/{id}&quot;)

abstract suspend fun [updateCategoria](update-categoria.md)(@Header(value = &quot;Authorization&quot;)auth: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @Path(value = &quot;id&quot;)id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @Bodybody: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @[JvmSuppressWildcards](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.jvm/-jvm-suppress-wildcards/index.html)[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-any/index.html)?&gt;): [CategoriaRecord](../../com.arcaneia.spendwise.apis.data.model/-categoria-record/index.md)

Actualiza una categoría existente en PocketBase.

#### Return

La categoría actualizada como [CategoriaRecord](../../com.arcaneia.spendwise.apis.data.model/-categoria-record/index.md).

#### Parameters

androidJvm

| | |
|---|---|
| auth | Token de autenticación. |
| id | Identificador remoto de la categoría. |
| body | Mapa con los campos a modificar. |
