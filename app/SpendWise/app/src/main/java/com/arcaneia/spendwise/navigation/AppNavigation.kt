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
import com.arcaneia.spendwise.data.model.*
import com.arcaneia.spendwise.screens.ExpenseScreen
import com.arcaneia.spendwise.screens.HistoryScreen
import com.arcaneia.spendwise.screens.IncomeScreen
import com.arcaneia.spendwise.screens.MainScreen
import com.arcaneia.spendwise.screens.SpendWiseBottomBar
import com.arcaneia.spendwise.screens.SplashScreen
import com.arcaneia.spendwise.viewmodel.AuthViewModel
@Composable
fun AppNavigation(
    authViewModel: AuthViewModel,
    movViewModel: MovViewModel,
    categoriaViewModel: CategoriaViewModel,
    movRecurViewModel: MovRecurViewModel
) {

    // Único control de navegación. Controla la navegación de todas las vistas
    val navController = rememberNavController()

    // Para saber la ruta actual y decidir si mostramos la BottomBar
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Destino actual de la navegación
    val destination = backStackEntry?.destination

    // Rutas que SÍ muestran la BottomBar
    val bottomBarRoutes = setOf(
        AppScreens.MainScreen.route,
        AppScreens.HistoryScreen.route,
        AppScreens.ExpenseScreen.route,
        AppScreens.IncomeScreen.route
    )
    //Comprueba que a la pantalla que queremos navegar está incluida en las rutas que muestran el bottombar (bottomBarRoutes)
    val showBottomBar = destination.isInRoutes(bottomBarRoutes)

    /**
     * Este Scaffold mostrará y gestionará la única BottomBar en el documento
     * para evitar crear varias BottomBar en cada pantalla
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
            startDestination = AppScreens.SplashScreen.route,
            modifier = Modifier.padding(
                PaddingValues(
                    top = innerPadding.calculateTopPadding(),
                    start = innerPadding.calculateStartPadding(
                        LayoutDirection.Ltr),
                    end = innerPadding.calculateEndPadding(LayoutDirection.Ltr),
                    bottom = innerPadding.calculateBottomPadding() / 2 // Reducir el espacio entre la bottombar y las pantallas
                )
            )

        ) {
            composable(AppScreens.SplashScreen.route) { SplashScreen(navController, authViewModel) }
            composable(AppScreens.MainScreen.route) {MainScreen(navController = navController, movViewModel = movViewModel)}
            composable(AppScreens.HistoryScreen.route) { HistoryScreen(navController,  movViewModel = movViewModel) }
            composable(AppScreens.ExpenseScreen.route) { ExpenseScreen(navController,  movViewModel = movViewModel, categoriaViewModel = categoriaViewModel) }
            composable(AppScreens.IncomeScreen.route) { IncomeScreen(navController,  movViewModel = movViewModel, categoriaViewModel = categoriaViewModel) }

            // Ejemplo de pantalla sin BottomBar:
            // composable(AppScreens.Detail.route) { DetailScreen(navController) }
        }
    }
}

private fun NavDestination?.isInRoutes(routes: Set<String>): Boolean =
    this?.hierarchy?.any { it.route in routes } == true