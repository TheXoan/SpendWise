//[app](../../../index.md)/[com.arcaneia.spendwise.apis](../index.md)/[PocketBaseApi](index.md)/[createMovRecur](create-mov-recur.md)

# createMovRecur

[androidJvm]\

@POST(value = &quot;api/collections/mov_recur/records&quot;)

abstract suspend fun [createMovRecur](create-mov-recur.md)(@Header(value = &quot;Authorization&quot;)auth: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @Bodybody: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @[JvmSuppressWildcards](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.jvm/-jvm-suppress-wildcards/index.html)[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-any/index.html)?&gt;): [MovRecurRecord](../../com.arcaneia.spendwise.apis.data.model/-mov-recur-record/index.md)

Crea un nuevo movimiento recurrente en PocketBase.

#### Return

Registro creado como [MovRecurRecord](../../com.arcaneia.spendwise.apis.data.model/-mov-recur-record/index.md).

#### Parameters

androidJvm

| | |
|---|---|
| auth | Token de autenticación. |
| body | Datos del movimiento recurrente como mapa. |
