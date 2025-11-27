package com.arcaneia.spendwise.navigation

/**
 * Clase sellada que define todas las rutas de navegación de la aplicación.
 *
 * Cada pantalla de la app se representa como un objeto dentro de esta clase,
 * garantizando que solo existan rutas válidas y centralizando su definición.
 *
 * Al utilizar una `sealed class`, se asegura que las rutas solo puedan ser
 * creadas desde este archivo, evitando inconsistencias y errores tipográficos
 * en el sistema de navegación.
 *
 * @property route Cadena que representa la ruta única utilizada por Navigation Compose.
 */
sealed class AppScreens(val route: String) {

    /** Pantalla principal del resumen general. */
    object MainScreen : AppScreens("main_screen")

    /** Pantalla del historial de movimientos. */
    object HistoryScreen : AppScreens("history_screen")

    /** Pantalla inicial de carga (splash). */
    object SplashScreen : AppScreens("splash_screen")

    /** Pantalla para registrar o visualizar gastos. */
    object ExpenseScreen : AppScreens("expense_screen")

    /** Pantalla para registrar o visualizar ingresos. */
    object IncomeScreen : AppScreens("income_screen")

    /** Pantalla de gestión de categorías. */
    object CategoryScreen : AppScreens("category_screen")

    /** Pantalla de configuración de la aplicación. */
    object SettingScreen : AppScreens("setting_screen")

    /** Pantalla con el historial de movimientos recurrentes. */
    object MovRecurHistoryScreen : AppScreens("movRecurHistory_screen")

    /** Pantalla para crear un nuevo movimiento recurrente. */
    object NewMovRecurScreen : AppScreens("newMovRecur_screen")
}