package com.arcaneia.spendwise.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.arcaneia.spendwise.navigation.AppScreens
import com.arcaneia.spendwise.ui.theme.SelectedItemBottomBar

/**
 * Representa un elemento individual en la barra de navegación inferior.
 *
 * @property route Ruta de navegación asociada al ítem.
 * @property label Texto descriptivo que se muestra bajo el icono.
 * @property icon Composable que renderiza el icono del ítem.
 */
data class BottomItem(
    val route: String,
    val label: String,
    val icon: @Composable () -> Unit
)

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
@Composable
fun SpendWiseBottomBar(
    navController: NavController,
    currentDestination: NavDestination?
){
    // Lista de ítems que se mostrarán en la barra inferior
    val items = listOf(
        BottomItem(AppScreens.MainScreen.route, "Inicio") {
            androidx.compose.material3.Icon(Icons.Filled.Home, null)
        },
        BottomItem(AppScreens.CategoryScreen.route, "Categorías") {
            androidx.compose.material3.Icon(Icons.Filled.Category, null)
        },
        BottomItem(AppScreens.HistoryScreen.route, "Historial") {
            androidx.compose.material3.Icon(Icons.Filled.History, null)
        },
        BottomItem(AppScreens.MovRecurHistoryScreen.route, "Mov. Recur") {
            androidx.compose.material3.Icon(Icons.Filled.CalendarMonth, null)
        },
        BottomItem(AppScreens.SettingScreen.route, "Ajustes") {
            androidx.compose.material3.Icon(Icons.Filled.Settings, null)
        },
    )

    NavigationBar(
        containerColor = Color.Black
    ) {
        items.forEach { item ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == item.route } == true

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        // Mantiene un solo destino en la parte superior y RESTAURA estado
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = item.icon,
                label = {
                    Text(
                        text = item.label,
                        color = Color.White
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = SelectedItemBottomBar
                )
            )
        }
    }
}