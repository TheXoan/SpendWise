package com.arcaneia.spendwise.screens

import android.content.Context
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
import com.arcaneia.spendwise.apis.data.model.CategoriaRemoteDataSource
import com.arcaneia.spendwise.apis.data.model.CategoriaSyncRepository
import com.arcaneia.spendwise.apis.data.model.MovRecurRemoteDataSource
import com.arcaneia.spendwise.apis.data.model.MovRecurSyncRepository
import com.arcaneia.spendwise.apis.data.model.MovRemoteDataSource
import com.arcaneia.spendwise.apis.data.model.MovSyncRepository
import com.arcaneia.spendwise.data.database.AppDatabase
import com.arcaneia.spendwise.navigation.AppScreens
import com.arcaneia.spendwise.data.model.AuthViewModel
import kotlinx.coroutines.delay

/**
 * ## SplashScreen
 *
 * Pantalla inicial que cumpre co rol de **punto de entrada seguro** á aplicación.
 * Executa o proceso de sincronización completo antes de mostrar a interface principal.
 *
 * ### 🎯 Funcionalidades principais
 *
 * - Mostra o logotipo e unha animación de carga.
 * - Observa o estado de autenticación mediante [AuthViewModel].
 * - Crea e inicializa todas as dependencias necesarias:
 *   - DAOs de Room mediante [AppDatabase]
 *   - Fontes de datos remotas (PocketBase) para:
 *     - Categorías → [CategoriaRemoteDataSource]
 *     - Movimentos recorrentes → [MovRecurRemoteDataSource]
 *     - Movimentos simples → [MovRemoteDataSource]
 *   - Repositorios de sincronización:
 *     - [CategoriaSyncRepository]
 *     - [MovRecurSyncRepository]
 *     - [MovSyncRepository]
 * - Executa a sincronización completa antes da navegación.
 * - Navega automaticamente a [AppScreens.MainScreen] cando todo está listo.
 *
 * ### 🔄 Proceso de sincronización
 *
 * Unha vez que `authViewModel.isAuthenticated.value` é `true`, lánzanse:
 *
 * 1. `categoriaSyncRepository.sync()`
 * 2. `movRecurSyncRepository.sync()`
 * 3. `movSyncRepository.sync()`
 *
 * Estas operacións aseguran que Room estea perfectamente aliñado con PocketBase
 * antes de cargar a primeira pantalla da app.
 *
 * ### 🕒 Control de esperas
 *
 * Tras completar a sincronización:
 *
 * - Agárdase 500 ms para dar suavidade á transición.
 * - Navegase ao destino principal eliminando o Splash do backstack.
 *
 * ### 🎨 UI
 *
 * A interface é sinxela e centrada:
 *
 * - Logotipo grande centrado.
 * - Nome da aplicación.
 * - Mensaxe de verificación de identidade.
 * - CircularProgressIndicator de carga.
 *
 * @param navController Controlador de navegación responsable de redirixir á pantalla principal unha vez completado o proceso.
 * @param authViewModel ViewModel encargado da autenticación e de expoñer o estado `isAuthenticated`.
 * @param context Contexto necesario para acceder á base de datos e fontes de datos remotas.
 */
@Composable
fun SplashScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    context: Context
) {
    val db = AppDatabase.getDatabase(context)

    // --- Dependencias de Categoría ---
    val categoriaDao = db.categoriaDao()
    val remoteCategoria = CategoriaRemoteDataSource(context)
    val categoriaSyncRepository = CategoriaSyncRepository(
        local = categoriaDao,
        remote = remoteCategoria,
        context = context
    )

    // --- Dependencias Mov. Recurrentes ---
    val movRecurDao = db.movRecurDao()
    val remoteMovRecur = MovRecurRemoteDataSource(context)
    val movRecurSyncRepository = MovRecurSyncRepository(
        local = movRecurDao,
        remote = remoteMovRecur,
        context = context
    )

    // --- Dependencias Mov. Simples ---
    val movDao = db.movDao()
    val remoteMov = MovRemoteDataSource(context)
    val movSyncRepository = MovSyncRepository(
        local = movDao,
        remote = remoteMov,
        categoriaDao = categoriaDao,
        movRecurDao = movRecurDao,
        context = context
    )

    /**
     * ## Sincronización e navegación
     *
     * Este efecto lánzase sempre que cambia `authViewModel.isAuthenticated.value`.
     *
     * Se o usuario está autenticado:
     *
     * 1. Execútanse todas as sincronizacións locais ↔ remotas.
     * 2. Espérase unha pequena pausa para mellorar a UX.
     * 3. Navegase á pantalla principal eliminando o Splash.
     */
    LaunchedEffect(authViewModel.isAuthenticated.value) {
        if (authViewModel.isAuthenticated.value) {

            // Sincronización completa antes de navegar
            categoriaSyncRepository.sync()
            movRecurSyncRepository.sync()
            movSyncRepository.sync()

            // Espera opcional para suavizar transición
            delay(500)

            // Navegación final
            navController.navigate(AppScreens.MainScreen.route) {
                popUpTo(AppScreens.SplashScreen.route) { inclusive = true }
            }
        }
    }

    /**
     * ## UI do SplashScreen
     *
     * Disposición vertical centrada cun layout minimalista:
     *
     * - Logotipo grande (350 dp).
     * - Nome da aplicación.
     * - Mensaxe indicativa de verificación.
     * - Indicador circular de carga.
     *
     * Non require interacción do usuario.
     */
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