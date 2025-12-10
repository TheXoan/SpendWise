//[app](../../../index.md)/[com.arcaneia.spendwise.apis](../index.md)/[PocketBaseApi](index.md)/[createCategoria](create-categoria.md)

# createCategoria

[androidJvm]\

@POST(value = &quot;api/collections/categoria/records&quot;)

abstract suspend fun [createCategoria](create-categoria.md)(@Header(value = &quot;Authorization&quot;)auth: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @Bodybody: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @[JvmSuppressWildcards](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.jvm/-jvm-suppress-wildcards/index.html)[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-any/index.html)?&gt;): [CategoriaRecord](../../com.arcaneia.spendwise.apis.data.model/-categoria-record/index.md)

Crea una nueva categoría en el servidor.

#### Return

El registro creado como [CategoriaRecord](../../com.arcaneia.spendwise.apis.data.model/-categoria-record/index.md).

#### Parameters

androidJvm

| | |
|---|---|
| auth | Token de autenticación. |
| body | Datos de la categoría como un mapa llave/valor. |
