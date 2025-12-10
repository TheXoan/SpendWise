//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[CategoriaRemoteDataSource](index.md)/[create](create.md)

# create

[androidJvm]\
suspend fun [create](create.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), categoria: [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)): [CategoriaRecord](../-categoria-record/index.md)

Crea una nueva categoría asociada al usuario especificado.

Envía los datos de la categoría a la API y devuelve el registro creado.

#### Return

El registro creado como [CategoriaRecord](../-categoria-record/index.md).

#### Parameters

androidJvm

| | |
|---|---|
| userId | Identificador del usuario propietario. |
| categoria | Datos de la categoría a crear. |
