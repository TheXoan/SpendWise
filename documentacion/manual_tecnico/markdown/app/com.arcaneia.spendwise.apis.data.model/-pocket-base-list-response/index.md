//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[PocketBaseListResponse](index.md)

# PocketBaseListResponse

data class [PocketBaseListResponse](index.md)&lt;[T](index.md)&gt;(val page: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html), val perPage: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html), val totalItems: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html), val items: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[T](index.md)&gt;)

Representa una respuesta paginada estándar de PocketBase.

Esta estructura se usa para envolver listas de elementos obtenidos desde la API, proporcionando información de paginación junto con los datos retornados.

#### Parameters

androidJvm

| | |
|---|---|
| T | Tipo de elementos contenidos en la lista. |

## Constructors

| | |
|---|---|
| [PocketBaseListResponse](-pocket-base-list-response.md) | [androidJvm]<br>constructor(page: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html), perPage: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html), totalItems: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html), items: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[T](index.md)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [items](items.md) | [androidJvm]<br>val [items](items.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[T](index.md)&gt;<br>Lista de elementos de la página actual. |
| [page](page.md) | [androidJvm]<br>val [page](page.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)<br>Número de página actual. |
| [perPage](per-page.md) | [androidJvm]<br>val [perPage](per-page.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)<br>Cantidad de elementos por página. |
| [totalItems](total-items.md) | [androidJvm]<br>val [totalItems](total-items.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)<br>Número total de elementos disponibles en el servidor. |
