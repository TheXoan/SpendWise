package com.arcaneia.spendwise.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.arcaneia.spendwise.navigation.AppScreens
import com.arcaneia.spendwise.ui.theme.SelectedItemBottomBar

data class BottomItem(val route: String, val label: String, val icon: @Composable () -> Unit)

@Composable
fun SpendWiseBottomBar(
    navController: NavController,
    currentDestination: NavDestination?
){
    val items = listOf(
        BottomItem(AppScreens.MainScreen.route, "Home") { androidx.compose.material3.Icon(Icons.Filled.Home, null) },
        BottomItem(AppScreens.HistoryScreen.route, "Historial") { androidx.compose.material3.Icon(Icons.Filled.History, null) },
        BottomItem(AppScreens.CategoryScreen.route, "Categorías") { androidx.compose.material3.Icon(Icons.Filled.Category, null) },
    )

    NavigationBar(
        containerColor = Color.Black
    ) {
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
                label = { Text(text = item.label,
                    color = Color.White
                ) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = SelectedItemBottomBar
                )
            )
        }
    }
}