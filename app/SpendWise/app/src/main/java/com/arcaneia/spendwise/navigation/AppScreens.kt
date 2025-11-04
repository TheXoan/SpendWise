package com.arcaneia.spendwise.navigation


// Creada *sealed* para que se acceda ÚNICAMENTE a los objetos aquí creados.
sealed class AppScreens(val route: String) {
    object MainScreen: AppScreens("main_screen")
    object HistoryScreen: AppScreens("history_screen")
    object SplashScreen: AppScreens("splash_screen")
    object ExpenseScreen: AppScreens("expense_screen")
    object IncomeScreen: AppScreens("income_screen")
    object CategoryScreen: AppScreens("category_screen")
}