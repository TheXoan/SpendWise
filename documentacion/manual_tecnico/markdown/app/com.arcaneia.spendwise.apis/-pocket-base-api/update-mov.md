//[app](../../../index.md)/[com.arcaneia.spendwise.apis](../index.md)/[PocketBaseApi](index.md)/[updateMov](update-mov.md)

# updateMov

[androidJvm]\

@PATCH(value = &quot;api/collections/mov/records/{id}&quot;)

abstract suspend fun [updateMov](update-mov.md)(@Header(value = &quot;Authorization&quot;)auth: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @Path(value = &quot;id&quot;)id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @Bodybody: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @[JvmSuppressWildcards](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.jvm/-jvm-suppress-wildcards/index.html)[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-any/index.html)?&gt;): &lt;Error class: unknown class&gt;

Actualiza un movimiento simple existente.

#### Return

Movimiento actualizado como MovRecord.

#### Parameters

androidJvm

| | |
|---|---|
| auth | Token de autenticación. |
| id | ID remoto del movimiento. |
| body | Campos modificados. |
