package com.arcaneia.spendwise.navigation

// Creada *sealed* para que se acceda ÚNICAMENTE a los objetos aquí creados.
sealed class AppScreens(val route: String) {
    object MainScreen: AppScreens("main_screen")
    object LogScreen: AppScreens("log_screen")
}