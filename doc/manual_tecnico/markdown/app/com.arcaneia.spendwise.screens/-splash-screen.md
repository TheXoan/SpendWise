//[app](../../index.md)/[com.arcaneia.spendwise.screens](index.md)/[SplashScreen](-splash-screen.md)

# SplashScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [SplashScreen](-splash-screen.md)(navController: [NavController](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.html), authViewModel: [AuthViewModel](../com.arcaneia.spendwise.data.model/-auth-view-model/index.md), context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))

## SplashScreen

Pantalla inicial que cumpre co rol de **punto de entrada seguro** á aplicación. Executa o proceso de sincronización completo antes de mostrar a interface principal.

### 🎯 Funcionalidades principais

- 
   Mostra o logotipo e unha animación de carga.
- 
   Observa o estado de autenticación mediante [AuthViewModel](../com.arcaneia.spendwise.data.model/-auth-view-model/index.md).
- 
   Crea e inicializa todas as dependencias necesarias:
- 
   DAOs de Room mediante [AppDatabase](../com.arcaneia.spendwise.data.database/-app-database/index.md)
- 
   Fontes de datos remotas (PocketBase) para:
- - 
      Categorías → [CategoriaRemoteDataSource](../com.arcaneia.spendwise.apis.data.model/-categoria-remote-data-source/index.md)
   - 
      Movimentos recorrentes → [MovRecurRemoteDataSource](../com.arcaneia.spendwise.apis.data.model/-mov-recur-remote-data-source/index.md)
   - 
      Movimentos simples → [MovRemoteDataSource](../com.arcaneia.spendwise.apis.data.model/-mov-remote-data-source/index.md)
- 
   Repositorios de sincronización:
- - 
      [CategoriaSyncRepository](../com.arcaneia.spendwise.apis.data.model/-categoria-sync-repository/index.md)
   - 
      [MovRecurSyncRepository](../com.arcaneia.spendwise.apis.data.model/-mov-recur-sync-repository/index.md)
   - 
      [MovSyncRepository](../com.arcaneia.spendwise.apis.data.model/-mov-sync-repository/index.md)
- 
   Executa a sincronización completa antes da navegación.
- 
   Navega automaticamente a [AppScreens.MainScreen](../com.arcaneia.spendwise.navigation/-app-screens/-main-screen/index.md) cando todo está listo.

### 🔄 Proceso de sincronización

Unha vez que `authViewModel.isAuthenticated.value` é `true`, lánzanse:

1. 
   `categoriaSyncRepository.sync()`
2. 
   `movRecurSyncRepository.sync()`
3. 
   `movSyncRepository.sync()`

Estas operacións aseguran que Room estea perfectamente aliñado con PocketBase antes de cargar a primeira pantalla da app.

### 🕒 Control de esperas

Tras completar a sincronización:

- 
   Agárdase 500 ms para dar suavidade á transición.
- 
   Navegase ao destino principal eliminando o Splash do backstack.

### 🎨 UI

A interface é sinxela e centrada:

- 
   Logotipo grande centrado.
- 
   Nome da aplicación.
- 
   Mensaxe de verificación de identidade.
- 
   CircularProgressIndicator de carga.

#### Parameters

androidJvm

| | |
|---|---|
| navController | Controlador de navegación responsable de redirixir á pantalla principal unha vez completado o proceso. |
| authViewModel | ViewModel encargado da autenticación e de expoñer o estado `isAuthenticated`. |
| context | Contexto necesario para acceder á base de datos e fontes de datos remotas. |
