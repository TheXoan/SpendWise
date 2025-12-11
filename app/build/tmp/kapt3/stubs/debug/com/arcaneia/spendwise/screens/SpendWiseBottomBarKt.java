package com.arcaneia.spendwise.screens;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007\u00a8\u0006\u0006"}, d2 = {"SpendWiseBottomBar", "", "navController", "Landroidx/navigation/NavController;", "currentDestination", "Landroidx/navigation/NavDestination;", "app_debug"})
public final class SpendWiseBottomBarKt {
    
    /**
     * Barra de navegación inferior (BottomBar) de la aplicación.
     *
     * Esta barra:
     * - Cambia de color el ítem seleccionado.
     * - Navega entre diferentes pantallas usando rutas definidas en [AppScreens].
     * - Mantiene el estado de navegación (restoreState / saveState).
     * - Limita la pila de navegación mediante `popUpTo` hacia el destino inicial.
     *
     * @param navController Controlador de navegación principal.
     * @param currentDestination Destino actual utilizado para determinar qué elemento está seleccionado.
     */
    @androidx.compose.runtime.Composable()
    public static final void SpendWiseBottomBar(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.Nullable()
    androidx.navigation.NavDestination currentDestination) {
    }
}