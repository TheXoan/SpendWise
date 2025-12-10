//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[CategoriaRemoteDataSource](index.md)

# CategoriaRemoteDataSource

[androidJvm]\
class [CategoriaRemoteDataSource](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))

Data source encargado de gestionar las operaciones remotas relacionadas con las categorías.

Esta clase se comunica con la API usando [RetrofitClient](../../com.arcaneia.spendwise.apis/-retrofit-client/index.md) para obtener, crear, actualizar y eliminar categorías asociadas a un usuario. Requiere un token válido almacenado en [TokenDataStore](../../com.arcaneia.spendwise.data.datastore/-token-data-store/index.md) para autenticar las solicitudes.

## Constructors

| | |
|---|---|
| [CategoriaRemoteDataSource](-categoria-remote-data-source.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Functions

| Name | Summary |
|---|---|
| [create](create.md) | [androidJvm]<br>suspend fun [create](create.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), categoria: [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)): [CategoriaRecord](../-categoria-record/index.md)<br>Crea una nueva categoría asociada al usuario especificado. |
| [delete](delete.md) | [androidJvm]<br>suspend fun [delete](delete.md)(categoriaPBId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))<br>Elimina la categoría asociada al identificador proporcionado. |
| [fetchAll](fetch-all.md) | [androidJvm]<br>suspend fun [fetchAll](fetch-all.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[CategoriaRecord](../-categoria-record/index.md)&gt;<br>Obtiene todas las categorías asociadas al usuario especificado. |
| [update](update.md) | [androidJvm]<br>suspend fun [update](update.md)(categoriaPBId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), categoria: [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)): [CategoriaRecord](../-categoria-record/index.md)<br>Actualiza los datos de una categoría existente. |
