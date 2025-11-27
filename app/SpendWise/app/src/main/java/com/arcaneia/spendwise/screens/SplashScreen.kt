package com.arcaneia.spendwise.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arcaneia.spendwise.R
import com.arcaneia.spendwise.navigation.AppScreens
import com.arcaneia.spendwise.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

/**
 * Pantalla inicial (Splash Screen) que muestra el logotipo de la aplicación y
 * verifica si el usuario está autenticado antes de continuar.
 *
 * Esta pantalla:
 * - Se muestra al inicio de la app.
 * - Observa el estado de `isAuthenticated` desde [AuthViewModel].
 * - Espera 2 segundos para permitir carga inicial y transición visual.
 * - Navega automáticamente a [AppScreens.MainScreen] si la autenticación es válida.
 *
 * @param navController Controlador de navegación utilizado para redirigir a la pantalla principal.
 * @param authViewModel ViewModel encargado de gestionar la autenticación del usuario.
 */
@Composable
fun SplashScreen(navController: NavController, authViewModel: AuthViewModel) {

    // Inicia navegación en cuanto isAuthenticated.value sea true
    LaunchedEffect(authViewModel.isAuthenticated.value) {
        if (authViewModel.isAuthenticated.value) {
            delay(2000) // Animación visual y tiempo para cargar datos sin afectar UX

            // Navega a la pantalla principal y elimina la pantalla splash de la pila
            navController.navigate(AppScreens.MainScreen.route) {
                popUpTo(AppScreens.SplashScreen.route) { inclusive = true }
            }
        }
    }

    // UI principal del Splash
    Column(
        modifier = Modifier
            .fillMaxSize(), // ocupa toda la pantalla
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.clean_spendwise_logo),
            contentDescription = "SpendWise logo",
            modifier = Modifier
                .size(350.dp)
                .padding(end = 30.dp),
        )

        Text(
            "SpendWise",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "Verificando identidad...",
            fontSize = 16.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        CircularProgressIndicator()
    }
}