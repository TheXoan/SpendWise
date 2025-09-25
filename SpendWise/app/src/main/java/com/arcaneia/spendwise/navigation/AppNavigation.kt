package com.arcaneia.spendwise.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arcaneia.spendwise.screens.LogScreen
import com.arcaneia.spendwise.screens.MainScreen

@Composable
fun AppNavigation() {

    // Gestiona el estado de navegación entre pantallas
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route) {

        composable(route = AppScreens.MainScreen.route) {
            MainScreen(navController)
        }
        composable(route = AppScreens.LogScreen.route) {
            LogScreen(navController)
        }
    }
}