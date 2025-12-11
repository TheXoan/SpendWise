//[app](../../../index.md)/[com.arcaneia.spendwise.apis](../index.md)/[PocketBaseApi](index.md)/[getCategorias](get-categorias.md)

# getCategorias

[androidJvm]\

@GET(value = &quot;api/collections/categoria/records&quot;)

abstract suspend fun [getCategorias](get-categorias.md)(@Header(value = &quot;Authorization&quot;)auth: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), @Query(value = &quot;filter&quot;)filter: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null): [PocketBaseListResponse](../../com.arcaneia.spendwise.apis.data.model/-pocket-base-list-response/index.md)&lt;[CategoriaRecord](../../com.arcaneia.spendwise.apis.data.model/-categoria-record/index.md)&gt;

Obtiene una lista de categorías desde PocketBase.

#### Return

Un objeto paginado con registros [CategoriaRecord](../../com.arcaneia.spendwise.apis.data.model/-categoria-record/index.md).

#### Parameters

androidJvm

| | |
|---|---|
| auth | Token de autenticación en formato `"Bearer <token>"`. |
| filter | Filtro opcional escrito en sintaxis de PocketBase. |
