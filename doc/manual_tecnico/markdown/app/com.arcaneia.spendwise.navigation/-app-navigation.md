//[app](../../index.md)/[com.arcaneia.spendwise.navigation](index.md)/[AppNavigation](-app-navigation.md)

# AppNavigation

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [AppNavigation](-app-navigation.md)(authViewModel: [AuthViewModel](../com.arcaneia.spendwise.data.model/-auth-view-model/index.md), movViewModel: [MovViewModel](../com.arcaneia.spendwise.data.model/-mov-view-model/index.md), categoriaViewModel: [CategoriaViewModel](../com.arcaneia.spendwise.data.model/-categoria-view-model/index.md), movRecurViewModel: [MovRecurViewModel](../com.arcaneia.spendwise.data.model/-mov-recur-view-model/index.md), loginViewModel: [LoginViewModel](../com.arcaneia.spendwise.data.model/-login-view-model/index.md), categoriaSyncRepository: [CategoriaSyncRepository](../com.arcaneia.spendwise.apis.data.model/-categoria-sync-repository/index.md))

Función principal que gestiona la navegación global de la aplicación.

Este Composable centraliza todo el flujo de pantallas mediante un `NavController` y un único `Scaffold` que envuelve al contenido. De esta forma:

- 
   Se mantiene *una sola instancia* de la BottomBar en toda la app.
- 
   Las pantallas que forman parte de bottomBarRoutes mostrarán la BottomBar automáticamente.
- 
   El padding del `Scaffold` se transmite correctamente a cada pantalla usando `Modifier.padding`.

Además, maneja el flujo de pantallas inicial (Splash, Login o Main) y asegura una navegación coherente al borrar el backstack cuando es necesario (por ejemplo, tras iniciar sesión).

#### Parameters

androidJvm

| | |
|---|---|
| authViewModel | ViewModel encargado del estado de autenticación. |
| movViewModel | ViewModel principal para gestionar movimientos (ingresos y gastos). |
| categoriaViewModel | ViewModel encargado de gestionar categorías locales y remotas. |
| movRecurViewModel | ViewModel para la gestión de movimientos recurrentes. |
| loginViewModel | ViewModel encargado de la lógica del inicio de sesión. |
| categoriaSyncRepository | Repositorio que administra la sincronización local-remota de categorías. |
