package com.arcaneia.spendwise.screens;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u00a8\u0006\b"}, d2 = {"SplashScreen", "", "navController", "Landroidx/navigation/NavController;", "authViewModel", "Lcom/arcaneia/spendwise/data/model/AuthViewModel;", "context", "Landroid/content/Context;", "app_debug"})
public final class SplashScreenKt {
    
    /**
     * ## SplashScreen
     *
     * Pantalla inicial que cumpre co rol de **punto de entrada seguro** á aplicación.
     * Executa o proceso de sincronización completo antes de mostrar a interface principal.
     *
     * ### 🎯 Funcionalidades principais
     *
     * - Mostra o logotipo e unha animación de carga.
     * - Observa o estado de autenticación mediante [AuthViewModel].
     * - Crea e inicializa todas as dependencias necesarias:
     *  - DAOs de Room mediante [AppDatabase]
     *  - Fontes de datos remotas (PocketBase) para:
     *    - Categorías → [CategoriaRemoteDataSource]
     *    - Movimentos recorrentes → [MovRecurRemoteDataSource]
     *    - Movimentos simples → [MovRemoteDataSource]
     *  - Repositorios de sincronización:
     *    - [CategoriaSyncRepository]
     *    - [MovRecurSyncRepository]
     *    - [MovSyncRepository]
     * - Executa a sincronización completa antes da navegación.
     * - Navega automaticamente a [AppScreens.MainScreen] cando todo está listo.
     *
     * ### 🔄 Proceso de sincronización
     *
     * Unha vez que `authViewModel.isAuthenticated.value` é `true`, lánzanse:
     *
     * 1. `categoriaSyncRepository.sync()`
     * 2. `movRecurSyncRepository.sync()`
     * 3. `movSyncRepository.sync()`
     *
     * Estas operacións aseguran que Room estea perfectamente aliñado con PocketBase
     * antes de cargar a primeira pantalla da app.
     *
     * ### 🕒 Control de esperas
     *
     * Tras completar a sincronización:
     *
     * - Agárdase 500 ms para dar suavidade á transición.
     * - Navegase ao destino principal eliminando o Splash do backstack.
     *
     * ### 🎨 UI
     *
     * A interface é sinxela e centrada:
     *
     * - Logotipo grande centrado.
     * - Nome da aplicación.
     * - Mensaxe de verificación de identidade.
     * - CircularProgressIndicator de carga.
     *
     * @param navController Controlador de navegación responsable de redirixir á pantalla principal unha vez completado o proceso.
     * @param authViewModel ViewModel encargado da autenticación e de expoñer o estado `isAuthenticated`.
     * @param context Contexto necesario para acceder á base de datos e fontes de datos remotas.
     */
    @androidx.compose.runtime.Composable()
    public static final void SplashScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.AuthViewModel authViewModel, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
}