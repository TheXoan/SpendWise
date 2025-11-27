package com.arcaneia.spendwise

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.arcaneia.spendwise.data.database.AppDatabase
import com.arcaneia.spendwise.data.model.*
import com.arcaneia.spendwise.data.repository.*
import com.arcaneia.spendwise.navigation.AppNavigation
import com.arcaneia.spendwise.ui.theme.SpendWiseTheme
import com.arcaneia.spendwise.viewmodel.AuthViewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager

/**
 * Actividad principal de la aplicación.
 *
 * Se encarga de:
 * - Inicializar la base de datos y repositorios.
 * - Crear e inyectar manualmente los ViewModels.
 * - Cargar la UI basada en Jetpack Compose.
 * - Ejecutar la autenticación biométrica/patrón antes de mostrar el contenido.
 *
 * Esta actividad sirve como contenedor raíz de toda la navegación.
 */
class MainActivity : AppCompatActivity() {

    /** ViewModel que controla el estado de autenticación entre SplashScreen y MainScreen. */
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewModel que controlará el acceso a la app
        authViewModel = AuthViewModel()

        // --- Inicialización de base de datos y repositorios ---
        val db = AppDatabase.getDatabase(application)

        val movRepository = MovRepository(db.movDao())
        val categoriaRepository = CategoriaRepository(db.categoriaDao())
        val movRecurRepository = MovRecurRepository(
            movRecurDao = db.movRecurDao(),
            movDao = db.movDao()
        )

        // --- ViewModels principales usados en la app ---
        val movViewModel = MovViewModel(movRepository)
        val categoriaViewModel = CategoriaViewModel(categoriaRepository)
        val movRecurViewModel = MovRecurViewModel(movRecurRepository)

        // --- Renderizado de UI con Jetpack Compose ---
        setContent {
            SpendWiseTheme(darkTheme = true) {
                AppNavigation(
                    authViewModel = authViewModel,
                    movViewModel = movViewModel,
                    categoriaViewModel = categoriaViewModel,
                    movRecurViewModel = movRecurViewModel
                )
            }
        }

        // Lanzamos autenticación biométrica antes de dar acceso
        authenticateUser()
    }

    /**
     * Inicia el proceso de autenticación biométrica o mediante PIN/credenciales del dispositivo.
     *
     * - Si la autenticación es correcta → se activa la navegación interna (Splash → Home).
     * - Si falla → se cierra la app.
     */
    private fun authenticateUser() {
        val biometricManager = BiometricManager.from(this)

        val canAuth = biometricManager.canAuthenticate(
            BiometricManager.Authenticators.BIOMETRIC_STRONG or
                    BiometricManager.Authenticators.DEVICE_CREDENTIAL
        )

        // Validación del hardware biométrico o credenciales del dispositivo
        if (canAuth != BiometricManager.BIOMETRIC_SUCCESS) {
            val msg = when (canAuth) {
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                    "Tu dispositivo no tiene sensor biométrico."
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                    "Configura huella, rostro o PIN en tu dispositivo."
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                    "Sensor biométrico no disponible."
                else -> "Fallo de autenticación."
            }
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
            return
        }

        // Executor para callbacks biométricos
        val executor = ContextCompat.getMainExecutor(this)

        val prompt = BiometricPrompt(
            this,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    authViewModel.setAuthenticated(true)
                    Toast.makeText(
                        applicationContext,
                        "Autenticación correcta",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    Toast.makeText(
                        applicationContext,
                        "Error: $errString",
                        Toast.LENGTH_SHORT
                    ).show()

                    authViewModel.setAuthenticated(false)

                    // Cerrar la app si el usuario aborta la autenticación
                    finishAndRemoveTask()
                    kotlin.system.exitProcess(0)
                }

                override fun onAuthenticationFailed() {
                    Toast.makeText(
                        applicationContext,
                        "Fallo de autenticación",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )

        // Configuración del prompt
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticación requerida")
            .setSubtitle("Usa tu huella, rostro o PIN para acceder")
            .setAllowedAuthenticators(
                BiometricManager.Authenticators.BIOMETRIC_STRONG or
                        BiometricManager.Authenticators.DEVICE_CREDENTIAL
            )
            .build()

        prompt.authenticate(promptInfo)
    }
}