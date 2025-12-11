package com.arcaneia.spendwise.screens;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u00a8\u0006\u0006"}, d2 = {"SplashScreen", "", "navController", "Landroidx/navigation/NavController;", "authViewModel", "Lcom/arcaneia/spendwise/data/model/AuthViewModel;", "app_debug"})
public final class SplashScreenKt {
    
    /**
     * Pantalla inicial (Splash Screen) que muestra el logotipo de la aplicación y
     * verifica si el usuario está autenticado antes de continuar.
     *
     * Esta pantalla:
     * - Se muestra al inicio de la app.
     * - Observa el estado de autenticación desde [AuthViewModel].
     * - Ejecuta la sincronización de categorías mediante [categoriaSyncRepository] si el usuario está autenticado.
     * - Espera 2 segundos para permitir carga inicial, sincronización y transición visual suave.
     * - Redirige automáticamente a [AppScreens.MainScreen] eliminando el Splash del backstack.
     *
     * @param navController Controlador de navegación utilizado para mover al usuario a la pantalla principal.
     * @param authViewModel ViewModel encargado de gestionar la autenticación del usuario.
     */
    @androidx.compose.runtime.Composable()
    public static final void SplashScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.AuthViewModel authViewModel) {
    }
}