//[app](../../../index.md)/[com.arcaneia.spendwise.data.model](../index.md)/[AuthViewModel](index.md)

# AuthViewModel

[androidJvm]\
class [AuthViewModel](index.md) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

ViewModel responsable de gestionar el estado de autenticación de la aplicación.

Este ViewModel expone un estado observable que permite bloquear o permitir la navegación en función de si el usuario ya pasó la pantalla de carga/autenticación.

`isAuthenticated` se utiliza directamente en la `SplashScreen` para desencadenar la navegación a la pantalla principal una vez que su valor se establece en `true`.

## Constructors

| | |
|---|---|
| [AuthViewModel](-auth-view-model.md) | [androidJvm]<br>constructor()<br>Crea un [AuthViewModel](index.md) inicializando el estado como no autenticado. |

## Properties

| Name | Summary |
|---|---|
| [isAuthenticated](is-authenticated.md) | [androidJvm]<br>var [isAuthenticated](is-authenticated.md): [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt;<br>Estado observable que indica si el usuario está autenticado. |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../-mov-view-model/index.md#383812252%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-mov-view-model/index.md#383812252%2FFunctions%2F-912451524)(closeable: [AutoCloseable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-auto-closeable/index.html))<br>fun [addCloseable](../-mov-view-model/index.md#1722490497%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), closeable: [AutoCloseable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-auto-closeable/index.html)) |
| [getCloseable](../-mov-view-model/index.md#1102255800%2FFunctions%2F-912451524) | [androidJvm]<br>fun &lt;[T](../-mov-view-model/index.md#1102255800%2FFunctions%2F-912451524) : [AutoCloseable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-auto-closeable/index.html)&gt; [getCloseable](../-mov-view-model/index.md#1102255800%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [T](../-mov-view-model/index.md#1102255800%2FFunctions%2F-912451524)? |
| [setAuthenticated](set-authenticated.md) | [androidJvm]<br>fun [setAuthenticated](set-authenticated.md)(success: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html))<br>Actualiza el estado de autenticación. |
