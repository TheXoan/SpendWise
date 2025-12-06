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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arcaneia.spendwise.R
import com.arcaneia.spendwise.apis.data.model.CategoriaSyncRepository
import com.arcaneia.spendwise.navigation.AppScreens
import com.arcaneia.spendwise.data.model.AuthViewModel
import kotlinx.coroutines.delay

/**
 * Pantalla inicial (Splash Screen) que muestra el logotipo de la aplicación y
 * verifica si el usuario está autenticado antes de continuar.
 *
 * Esta pantalla:
 * - Se muestra al inicio de la app.
 * - Observa el estado de autenticación desde [AuthViewModel].
 * - Ejecuta la sincronización de categorías mediante [categoriaSyncRepository] si el usuario está autenticado.
 * - Espera 2 segundos para permitir carga inicial, sincronización y transición visual suave.
 * - Redirige automáticamente a [AppScreens.MainScreen] eliminando el Splash del backstack.
 *
 * @param navController Controlador de navegación utilizado para mover al usuario a la pantalla principal.
 * @param authViewModel ViewModel encargado de gestionar la autenticación del usuario.
 * @param categoriaSyncRepository Repositorio responsable de sincronizar las categorías locales con el servidor.
 */
@Composable
fun SplashScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    categoriaSyncRepository: CategoriaSyncRepository
) {

    // Inicia navegación cuando el usuario está autenticado
    LaunchedEffect(authViewModel.isAuthenticated.value) {
        if (authViewModel.isAuthenticated.value) {

            // Sincronizar categorías antes de entrar a la app
            categoriaSyncRepository.sync()

            delay(2000) // Animación / carga inicial para mejorar UX

            // Navega a la pantalla principal y elimina el Splash del backstack
            navController.navigate(AppScreens.MainScreen.route) {
                popUpTo(AppScreens.SplashScreen.route) { inclusive = true }
            }
        }
    }

    // UI principal del Splash
    Column(
        modifier = Modifier
            .fillMaxSize(),
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
            stringResource(id = R.string.identity_verification) + "...",
            fontSize = 16.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        CircularProgressIndicator()
    }
}