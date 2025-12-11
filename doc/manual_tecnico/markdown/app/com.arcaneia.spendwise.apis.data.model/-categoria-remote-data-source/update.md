//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[CategoriaRemoteDataSource](index.md)/[update](update.md)

# update

[androidJvm]\
suspend fun [update](update.md)(categoriaPBId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), categoria: [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)): [CategoriaRecord](../-categoria-record/index.md)

Actualiza los datos de una categoría existente.

Envía un cuerpo con los valores modificados al endpoint correspondiente.

#### Return

El registro actualizado como [CategoriaRecord](../-categoria-record/index.md).

#### Parameters

androidJvm

| | |
|---|---|
| categoriaPBId | Identificador de la categoría en PocketBase. |
| categoria | Nuevos datos de la categoría. |
