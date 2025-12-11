package com.arcaneia.spendwise.navigation;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\u001a8\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007\u001a\u001c\u0010\u000e\u001a\u00020\u000f*\u0004\u0018\u00010\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002\u00a8\u0006\u0014"}, d2 = {"AppNavigation", "", "authViewModel", "Lcom/arcaneia/spendwise/data/model/AuthViewModel;", "movViewModel", "Lcom/arcaneia/spendwise/data/model/MovViewModel;", "categoriaViewModel", "Lcom/arcaneia/spendwise/data/model/CategoriaViewModel;", "movRecurViewModel", "Lcom/arcaneia/spendwise/data/model/MovRecurViewModel;", "loginViewModel", "Lcom/arcaneia/spendwise/data/model/LoginViewModel;", "categoriaSyncRepository", "Lcom/arcaneia/spendwise/apis/data/model/CategoriaSyncRepository;", "isInRoutes", "", "Landroidx/navigation/NavDestination;", "routes", "", "", "app_debug"})
public final class AppNavigationKt {
    
    /**
     * Función principal que gestiona la navegación global de la aplicación.
     *
     * Este Composable centraliza todo el flujo de pantallas mediante un `NavController`
     * y un único `Scaffold` que envuelve al contenido. De esta forma:
     *
     * - Se mantiene *una sola instancia* de la BottomBar en toda la app.
     * - Las pantallas que forman parte de [bottomBarRoutes] mostrarán la BottomBar automáticamente.
     * - El padding del `Scaffold` se transmite correctamente a cada pantalla usando `Modifier.padding`.
     *
     * Además, maneja el flujo de pantallas inicial (Splash, Login o Main) y asegura una navegación coherente
     * al borrar el backstack cuando es necesario (por ejemplo, tras iniciar sesión).
     *
     * @param authViewModel ViewModel encargado del estado de autenticación.
     * @param movViewModel ViewModel principal para gestionar movimientos (ingresos y gastos).
     * @param categoriaViewModel ViewModel encargado de gestionar categorías locales y remotas.
     * @param movRecurViewModel ViewModel para la gestión de movimientos recurrentes.
     * @param loginViewModel ViewModel encargado de la lógica del inicio de sesión.
     * @param categoriaSyncRepository Repositorio que administra la sincronización local-remota de categorías.
     */
    @androidx.compose.runtime.Composable()
    public static final void AppNavigation(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.AuthViewModel authViewModel, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.MovViewModel movViewModel, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.CategoriaViewModel categoriaViewModel, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.MovRecurViewModel movRecurViewModel, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.LoginViewModel loginViewModel, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.apis.data.model.CategoriaSyncRepository categoriaSyncRepository) {
    }
    
    /**
     * Función de extensión para determinar si un destino pertenece a una lista de rutas
     * específicas. Utiliza la jerarquía del destino para comprobar rutas anidadas.
     *
     * @param routes Conjunto de rutas válidas.
     * @return `true` si la ruta actual pertenece a [routes], de lo contrario `false`.
     */
    private static final boolean isInRoutes(androidx.navigation.NavDestination $this$isInRoutes, java.util.Set<java.lang.String> routes) {
        return false;
    }
}