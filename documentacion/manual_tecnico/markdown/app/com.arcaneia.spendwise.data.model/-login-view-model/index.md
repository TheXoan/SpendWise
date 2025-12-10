//[app](../../../index.md)/[com.arcaneia.spendwise.data.model](../index.md)/[LoginViewModel](index.md)

# LoginViewModel

[androidJvm]\
class [LoginViewModel](index.md) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

ViewModel encargado de gestionar la lógica del proceso de inicio de sesión.

Expone el estado necesario para la pantalla de login, incluyendo:

- 
   Email y contraseña ingresados.
- 
   Indicador de carga mientras se procesa la autenticación.
- 
   Mensajes de error si ocurre un problema.
- 
   Indicador de éxito cuando el login ha sido completado correctamente.

Este ViewModel interactúa directamente con la API mediante [RetrofitClient](../../com.arcaneia.spendwise.apis/-retrofit-client/index.md) y guarda la sesión del usuario en [TokenDataStore](../../com.arcaneia.spendwise.data.datastore/-token-data-store/index.md).

## Constructors

| | |
|---|---|
| [LoginViewModel](-login-view-model.md) | [androidJvm]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [email](email.md) | [androidJvm]<br>var [email](email.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Email ingresado por el usuario en la pantalla de login. |
| [errorMessage](error-message.md) | [androidJvm]<br>var [errorMessage](error-message.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?<br>Contiene el mensaje de error a mostrar en caso de fallo. |
| [isLoading](is-loading.md) | [androidJvm]<br>var [isLoading](is-loading.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)<br>Indica si se está procesando la solicitud de login. |
| [loginSuccess](login-success.md) | [androidJvm]<br>var [loginSuccess](login-success.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)<br>Indica si el usuario logró autenticarse exitosamente. |
| [password](password.md) | [androidJvm]<br>var [password](password.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Contraseña ingresada por el usuario. |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../-mov-view-model/index.md#383812252%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-mov-view-model/index.md#383812252%2FFunctions%2F-912451524)(closeable: [AutoCloseable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-auto-closeable/index.html))<br>fun [addCloseable](../-mov-view-model/index.md#1722490497%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), closeable: [AutoCloseable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-auto-closeable/index.html)) |
| [getCloseable](../-mov-view-model/index.md#1102255800%2FFunctions%2F-912451524) | [androidJvm]<br>fun &lt;[T](../-mov-view-model/index.md#1102255800%2FFunctions%2F-912451524) : [AutoCloseable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-auto-closeable/index.html)&gt; [getCloseable](../-mov-view-model/index.md#1102255800%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [T](../-mov-view-model/index.md#1102255800%2FFunctions%2F-912451524)? |
| [login](login.md) | [androidJvm]<br>fun [login](login.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Intenta iniciar sesión con las credenciales ingresadas. |
