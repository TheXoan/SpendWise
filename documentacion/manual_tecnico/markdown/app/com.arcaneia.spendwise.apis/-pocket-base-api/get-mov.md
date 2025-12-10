//[app](../../../index.md)/[com.arcaneia.spendwise.apis](../index.md)/[PocketBaseApi](index.md)/[getMov](get-mov.md)

# getMov

[androidJvm]\

@GET(value = &quot;api/collections/mov/records&quot;)

abstract suspend fun [getMov](get-mov.md)(@Header(value = &quot;Authorization&quot;)auth: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @Query(value = &quot;filter&quot;)filter: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null): [PocketBaseListResponse](../../com.arcaneia.spendwise.apis.data.model/-pocket-base-list-response/index.md)&lt;&lt;Error class: unknown class&gt;&gt;

Obtiene todos los movimientos simples desde PocketBase.

#### Return

Lista paginada de MovRecord.

#### Parameters

androidJvm

| | |
|---|---|
| auth | Token de autenticación. |
| filter | Filtro opcional. |
