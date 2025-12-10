//[app](../../../index.md)/[com.arcaneia.spendwise.data.model](../index.md)/[CategoriaViewModel](index.md)

# CategoriaViewModel

[androidJvm]\
class [CategoriaViewModel](index.md)(repository: [CategoriaRepository](../../com.arcaneia.spendwise.data.repository/-categoria-repository/index.md), remoteDataSource: [CategoriaRemoteDataSource](../../com.arcaneia.spendwise.apis.data.model/-categoria-remote-data-source/index.md), appContext: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

ViewModel encargado de gestionar la lógica de categorías dentro de la aplicación.

Esta clase implementa un flujo de trabajo **offline-first con sincronización instantánea**:

- 
   Cualquier cambio (insert/update/delete) se aplica inmediatamente a la base de datos local.
- 
   Automáticamente se intenta sincronizar la operación con PocketBase.
- 
   Los cambios locales se observan en tiempo real mediante un `StateFlow`.

El ViewModel actúa como capa intermedia entre la UI, el repositorio local y la API remota, asegurando consistencia entre los datos locales y los remotos sin bloquear la interfaz.

## Constructors

| | |
|---|---|
| [CategoriaViewModel](-categoria-view-model.md) | [androidJvm]<br>constructor(repository: [CategoriaRepository](../../com.arcaneia.spendwise.data.repository/-categoria-repository/index.md), remoteDataSource: [CategoriaRemoteDataSource](../../com.arcaneia.spendwise.apis.data.model/-categoria-remote-data-source/index.md), appContext: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Properties

| Name | Summary |
|---|---|
| [categorias](categorias.md) | [androidJvm]<br>val [categorias](categorias.md): StateFlow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)&gt;&gt;<br>Flujo de solo lectura que expone la lista de categorías a la UI. |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../-mov-view-model/index.md#383812252%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-mov-view-model/index.md#383812252%2FFunctions%2F-912451524)(closeable: [AutoCloseable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-auto-closeable/index.html))<br>fun [addCloseable](../-mov-view-model/index.md#1722490497%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), closeable: [AutoCloseable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-auto-closeable/index.html)) |
| [deleteById](delete-by-id.md) | [androidJvm]<br>fun [deleteById](delete-by-id.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)): Job<br>Elimina una categoría localmente y, si corresponde, también remotamente. |
| [getCloseable](../-mov-view-model/index.md#1102255800%2FFunctions%2F-912451524) | [androidJvm]<br>fun &lt;[T](../-mov-view-model/index.md#1102255800%2FFunctions%2F-912451524) : [AutoCloseable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-auto-closeable/index.html)&gt; [getCloseable](../-mov-view-model/index.md#1102255800%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [T](../-mov-view-model/index.md#1102255800%2FFunctions%2F-912451524)? |
| [insert](insert.md) | [androidJvm]<br>fun [insert](insert.md)(cat: [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)): Job<br>Inserta una categoría **localmente e inmediatamente**, y luego intenta sincronizarla con el servidor. |
| [update](update.md) | [androidJvm]<br>fun [update](update.md)(cat: [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)): Job<br>Actualiza una categoría tanto local como remotamente. |
