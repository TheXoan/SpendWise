package com.arcaneia.spendwise.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arcaneia.spendwise.navigation.AppScreens

data class BottomItem(val route: String, val label: String, val icon: @Composable () -> Unit)

@Composable
fun SpendWiseBottomBar(
    navController: NavController,
    currentDestination: NavDestination?
){
    val items = listOf(
        BottomItem(
            AppScreens.MainScreen.route, "Home") { androidx.compose.material3.Icon(Icons.Filled.Home, null) },
        BottomItem(AppScreens.LogScreen.route, "Historial") { androidx.compose.material3.Icon(Icons.Filled.List, null) },
    )

    NavigationBar {
        items.forEach { item ->
            val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
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
                label = { Text(item.label) }
            )
        }
    }
}