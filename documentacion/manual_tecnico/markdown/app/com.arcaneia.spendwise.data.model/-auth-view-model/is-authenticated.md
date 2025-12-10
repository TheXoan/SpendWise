//[app](../../../index.md)/[com.arcaneia.spendwise.data.model](../index.md)/[AuthViewModel](index.md)/[isAuthenticated](is-authenticated.md)

# isAuthenticated

[androidJvm]\
var [isAuthenticated](is-authenticated.md): [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt;

Estado observable que indica si el usuario está autenticado.

- 
   `false` → La app permanece en la SplashScreen.
- 
   `true` → La app navega a la pantalla principal.

Solo puede modificarse desde dentro del ViewModel, lo que expone un patrón de encapsulación adecuado.
