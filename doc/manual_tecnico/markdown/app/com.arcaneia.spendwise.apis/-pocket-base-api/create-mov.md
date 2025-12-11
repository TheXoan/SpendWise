//[app](../../../index.md)/[com.arcaneia.spendwise.apis](../index.md)/[PocketBaseApi](index.md)/[createMov](create-mov.md)

# createMov

[androidJvm]\

@POST(value = &quot;api/collections/mov/records&quot;)

abstract suspend fun [createMov](create-mov.md)(@Header(value = &quot;Authorization&quot;)auth: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @Bodybody: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @[JvmSuppressWildcards](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.jvm/-jvm-suppress-wildcards/index.html)[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-any/index.html)?&gt;): [MovRecord](../../[root]/-mov-record/index.md)

Crea un nuevo movimiento simple.

#### Return

Registro creado como [MovRecord](../../[root]/-mov-record/index.md).

#### Parameters

androidJvm

| | |
|---|---|
| auth | Token de autenticación. |
| body | Mapa con los datos del movimiento. |
