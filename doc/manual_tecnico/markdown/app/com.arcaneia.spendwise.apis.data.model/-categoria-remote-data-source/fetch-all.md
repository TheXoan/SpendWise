//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[CategoriaRemoteDataSource](index.md)/[fetchAll](fetch-all.md)

# fetchAll

[androidJvm]\
suspend fun [fetchAll](fetch-all.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[CategoriaRecord](../-categoria-record/index.md)&gt;

Obtiene todas las categorías asociadas al usuario especificado.

Realiza una solicitud GET a la API aplicando un filtro por `userId`.

#### Return

Lista de [CategoriaRecord](../-categoria-record/index.md) obtenidos desde la API.

#### Parameters

androidJvm

| | |
|---|---|
| userId | Identificador del usuario. |
