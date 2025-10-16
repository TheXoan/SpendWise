package com.arcaneia.spendwise.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.arcaneia.spendwise.screens.LogScreen
import com.arcaneia.spendwise.screens.MainScreen
import com.arcaneia.spendwise.screens.SpendWiseBottomBar

@Composable
fun AppNavigation() {

    // Único control de navegación. Controla la navegación de todas las vistas
    val navController = rememberNavController()

    // Para saber la ruta actual y decidir si mostramos la BottomBar
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Destino actual de la navegación
    val destination = backStackEntry?.destination

    // Rutas que SÍ muestran la BottomBar
    val bottomBarRoutes = setOf(
        AppScreens.MainScreen.route,
        AppScreens.LogScreen.route,
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
            startDestination = AppScreens.MainScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppScreens.MainScreen.route) { MainScreen(navController) }
            composable(AppScreens.LogScreen.route) { LogScreen(navController) }

            // Ejemplo de pantalla sin BottomBar:
            // composable(AppScreens.Detail.route) { DetailScreen(navController) }
        }
    }
}

private fun NavDestination?.isInRoutes(routes: Set<String>): Boolean =
    this?.hierarchy?.any { it.route in routes } == true
