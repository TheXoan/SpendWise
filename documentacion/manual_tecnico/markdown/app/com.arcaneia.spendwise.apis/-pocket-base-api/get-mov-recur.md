//[app](../../../index.md)/[com.arcaneia.spendwise.apis](../index.md)/[PocketBaseApi](index.md)/[getMovRecur](get-mov-recur.md)

# getMovRecur

[androidJvm]\

@GET(value = &quot;api/collections/mov_recur/records&quot;)

abstract suspend fun [getMovRecur](get-mov-recur.md)(@Header(value = &quot;Authorization&quot;)auth: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @Query(value = &quot;filter&quot;)filter: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null): [PocketBaseListResponse](../../com.arcaneia.spendwise.apis.data.model/-pocket-base-list-response/index.md)&lt;[MovRecurRecord](../../com.arcaneia.spendwise.apis.data.model/-mov-recur-record/index.md)&gt;

Obtiene la lista de movimientos recurrentes desde PocketBase.

#### Return

Respuesta paginada con registros [MovRecurRecord](../../com.arcaneia.spendwise.apis.data.model/-mov-recur-record/index.md).

#### Parameters

androidJvm

| | |
|---|---|
| auth | Token de autenticación en formato Bearer. |
| filter | Filtro de servidor opcional. |
