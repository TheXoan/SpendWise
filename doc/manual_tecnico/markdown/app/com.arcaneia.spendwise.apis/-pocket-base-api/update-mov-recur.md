//[app](../../../index.md)/[com.arcaneia.spendwise.apis](../index.md)/[PocketBaseApi](index.md)/[updateMovRecur](update-mov-recur.md)

# updateMovRecur

[androidJvm]\

@PATCH(value = &quot;api/collections/mov_recur/records/{id}&quot;)

abstract suspend fun [updateMovRecur](update-mov-recur.md)(@Header(value = &quot;Authorization&quot;)auth: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @Path(value = &quot;id&quot;)id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @Bodybody: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @[JvmSuppressWildcards](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.jvm/-jvm-suppress-wildcards/index.html)[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-any/index.html)?&gt;): [MovRecurRecord](../../com.arcaneia.spendwise.apis.data.model/-mov-recur-record/index.md)

Actualiza un movimiento recurrente existente.

#### Return

El registro actualizado como [MovRecurRecord](../../com.arcaneia.spendwise.apis.data.model/-mov-recur-record/index.md).

#### Parameters

androidJvm

| | |
|---|---|
| auth | Token de autenticación. |
| id | ID remoto del movimiento recurrente. |
| body | Campos a modificar. |
