package com.arcaneia.spendwise.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.arcaneia.spendwise.apis.data.model.CategoriaSyncRepository
import com.arcaneia.spendwise.data.model.*
import com.arcaneia.spendwise.screens.CategoryScreen
import com.arcaneia.spendwise.screens.ExpenseScreen
import com.arcaneia.spendwise.screens.HistoryScreen
import com.arcaneia.spendwise.screens.IncomeScreen
import com.arcaneia.spendwise.screens.MainScreen
import com.arcaneia.spendwise.screens.MovRecurHistoryScreen
import com.arcaneia.spendwise.screens.NewMovRecurScreen
import com.arcaneia.spendwise.screens.SettingScreen
import com.arcaneia.spendwise.screens.SpendWiseBottomBar
import com.arcaneia.spendwise.screens.SplashScreen
import com.arcaneia.spendwise.data.model.AuthViewModel
import com.arcaneia.spendwise.screens.LoginScreen

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
@Composable
fun AppNavigation(
    authViewModel: AuthViewModel,
    movViewModel: MovViewModel,
    categoriaViewModel: CategoriaViewModel,
    movRecurViewModel: MovRecurViewModel,
    loginViewModel: LoginViewModel,
    categoriaSyncRepository: CategoriaSyncRepository
) {

    // Único controlador de navegación
    val navController = rememberNavController()

    // Estado del backstack para conocer la ruta actual
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Destino actual
    val destination = backStackEntry?.destination

    // Rutas que deben mostrar la BottomBar
    val bottomBarRoutes = setOf(
        AppScreens.MainScreen.route,
        AppScreens.HistoryScreen.route,
        AppScreens.ExpenseScreen.route,
        AppScreens.IncomeScreen.route,
        AppScreens.CategoryScreen.route,
        AppScreens.SettingScreen.route,
        AppScreens.NewMovRecurScreen.route,
        AppScreens.MovRecurHistoryScreen.route,
    )

    // Determina si la pantalla actual pertenece a las rutas con BottomBar
    val showBottomBar = destination.isInRoutes(bottomBarRoutes)

    /**
     * Scaffold principal que contiene la única BottomBar de la app.
     * Esto evita crear BottomBars repetidas en cada pantalla independiente.
     */
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                SpendWiseBottomBar(
                    navController = navController,
                    currentDestination = destination
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            //            startDestination = AppScreens.SplashScreen.route,
            startDestination = AppScreens.LoginScreen.route,
            //            startDestination = AppScreens.MainScreen.route,
            modifier = Modifier.padding(
                PaddingValues(
                    top = innerPadding.calculateTopPadding(),
                    start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                    end = innerPadding.calculateEndPadding(LayoutDirection.Ltr),
                    bottom = innerPadding.calculateBottomPadding() / 2
                )
            )
        ) {
            composable(AppScreens.SplashScreen.route) {
                SplashScreen(navController, authViewModel)
            }
            composable(AppScreens.MainScreen.route) {
                MainScreen(navController = navController, movViewModel = movViewModel)
            }
            composable(AppScreens.HistoryScreen.route) {
                HistoryScreen(navController, movViewModel = movViewModel, categoriaViewModel = categoriaViewModel)
            }
            composable(AppScreens.ExpenseScreen.route) {
                ExpenseScreen(navController, movViewModel = movViewModel, categoriaViewModel = categoriaViewModel)
            }
            composable(AppScreens.IncomeScreen.route) {
                IncomeScreen(navController, movViewModel = movViewModel, categoriaViewModel = categoriaViewModel)
            }
            composable(AppScreens.CategoryScreen.route) {
                CategoryScreen(navController, categoriaViewModel = categoriaViewModel)
            }
            composable(AppScreens.SettingScreen.route) {
                SettingScreen(navController)
            }
            composable(AppScreens.MovRecurHistoryScreen.route) {
                MovRecurHistoryScreen(navController, movRecurViewModel = movRecurViewModel)
            }
            composable(AppScreens.NewMovRecurScreen.route) {
                NewMovRecurScreen(navController, movRecurViewModel = movRecurViewModel)
            }
            composable(AppScreens.LoginScreen.route) {
                LoginScreen(
                    navController = navController,
                    loginViewModel = loginViewModel,
                    onLoginSuccess = {
                        navController.navigate(AppScreens.SplashScreen.route) {
                            popUpTo(AppScreens.LoginScreen.route) { inclusive = true }
                        }
                    }
                )
            }

            // Ejemplo de pantalla sin BottomBar:
            // composable(AppScreens.Detail.route) { DetailScreen(navController) }
        }
    }
}

/**
 * Función de extensión para determinar si un destino pertenece a una lista de rutas
 * específicas. Utiliza la jerarquía del destino para comprobar rutas anidadas.
 *
 * @param routes Conjunto de rutas válidas.
 * @return `true` si la ruta actual pertenece a [routes], de lo contrario `false`.
 */
private fun NavDestination?.isInRoutes(routes: Set<String>): Boolean =
    this?.hierarchy?.any { it.route in routes } == true