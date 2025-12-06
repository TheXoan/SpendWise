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
import com.arcaneia.spendwise.data.model.AuthViewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import com.arcaneia.spendwise.apis.data.model.CategoriaRemoteDataSource
import com.arcaneia.spendwise.apis.data.model.CategoriaSyncRepository

/**
 * Actividad principal de la aplicación.
 *
 * Se encarga de:
 * - Inicializar la base de datos y los repositorios.
 * - Crear manualmente los ViewModels utilizados globalmente por la app.
 * - Configurar y mostrar la UI de Jetpack Compose.
 * - Ejecutar autenticación biométrica previa antes de mostrar contenido sensible.
 *
 * Es el punto de entrada de toda la navegación y el contenedor raíz del ciclo de vida.
 */
class MainActivity : AppCompatActivity() {

    /**
     * ViewModel que controla el estado de autenticación entre SplashScreen y MainScreen.
     *
     * Se inicializa en `onCreate()` antes de montar la UI.
     */
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Se inicializa el ViewModel encargado del estado de autenticación.
         */
        authViewModel = AuthViewModel()

        // --- Inicialización de base de datos y repositorios ---
        /**
         * Instancia principal de la base de datos Room utilizada en toda la app.
         */
        val db = AppDatabase.getDatabase(application)

        /**
         * Repositorios responsables de gestionar las operaciones con Mov, Categoria y movimientos recurrentes.
         */
        val movRepository = MovRepository(db.movDao())
        val categoriaRepository = CategoriaRepository(db.categoriaDao())
        val movRecurRepository = MovRecurRepository(
            movRecurDao = db.movRecurDao(),
            movDao = db.movDao()
        )

        /**
         * DataSource remoto encargado de comunicarse con PocketBase.
         */
        val categoriaRemoteDataSource = CategoriaRemoteDataSource(this)

        // --- ViewModels principales usados en la app ---
        /**
         * Inicialización manual de ViewModels necesarios para las pantallas principales.
         * (Se realiza sin ViewModelProvider porque no dependen del ciclo de vida del Activity).
         */
        val movViewModel = MovViewModel(movRepository)
        val categoriaViewModel = CategoriaViewModel(categoriaRepository, categoriaRemoteDataSource)
        val movRecurViewModel = MovRecurViewModel(movRecurRepository)
        val loginViewModel = LoginViewModel()

        /**
         * Repositorio que sincroniza categorías entre la base local y PocketBase.
         */
        val categoriaSyncRepository = CategoriaSyncRepository(
            local = db.categoriaDao(),
            remote = CategoriaRemoteDataSource(
                this
            ),
            context = this
        )

        // --- Renderizado de UI con Jetpack Compose ---
        /**
         * Monta el contenido de la aplicación aplicando el tema visual y la navegación principal.
         */
        setContent {
            SpendWiseTheme(darkTheme = true) {
                AppNavigation(
                    authViewModel = authViewModel,
                    movViewModel = movViewModel,
                    categoriaViewModel = categoriaViewModel,
                    movRecurViewModel = movRecurViewModel,
                    loginViewModel = loginViewModel,
                    categoriaSyncRepository = categoriaSyncRepository
                )
            }
        }

        // Lanzamos autenticación biométrica antes de dar acceso
        authenticateUser()
    }

    /**
     * Inicia el proceso de autenticación biométrica o mediante PIN/credenciales del dispositivo.
     *
     * Flujo:
     * 1. Verifica si el dispositivo puede usar biometría o credenciales locales.
     * 2. Si no puede, muestra un mensaje y no continúa con la autenticación.
     * 3. Si es posible, configura un `BiometricPrompt` para iniciar la autenticación.
     * 4. Gestiona los callbacks para éxito, error o fallos.
     *
     * Si la autenticación es correcta → activa la navegación normal.
     * Si falla → la app se cierra inmediatamente por seguridad.
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
                    applicationContext.getString(R.string.device_no_sensor)
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                    applicationContext.getString(R.string.configure_system_authentication)
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                    applicationContext.getString(R.string.sensor_not_available)
                else -> applicationContext.getString(R.string.auth_error)
            }
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
            return
        }

        // Executor para callbacks biométricos
        val executor = ContextCompat.getMainExecutor(this)

        /**
         * Configuración del prompt biométrico y manejo de callbacks.
         */
        val prompt = BiometricPrompt(
            this,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {

                /**
                 * Callback cuando la autenticación es exitosa.
                 *
                 * - Marca al usuario como autenticado.
                 * - Muestra un mensaje de éxito.
                 */
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    authViewModel.setAuthenticated(true)
                    Toast.makeText(
                        applicationContext,
                        applicationContext.getString(R.string.auth_success),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                /**
                 * Callback cuando ocurre un error en la autenticación.
                 *
                 * - Muestra el mensaje de error.
                 * - Termina completamente la aplicación si la autenticación no se completa.
                 */
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    Toast.makeText(
                        applicationContext,
                        applicationContext.getString(R.string.error) + ": " + "$errString",
                        Toast.LENGTH_SHORT
                    ).show()

                    authViewModel.setAuthenticated(false)

                    // Cerrar la app si el usuario aborta la autenticación
                    finishAndRemoveTask()
                    kotlin.system.exitProcess(0)
                }

                /**
                 * Callback cuando la autenticación falla pero sin error crítico.
                 *
                 * Se muestra un mensaje, pero la autenticación continúa activa.
                 */
                override fun onAuthenticationFailed() {
                    Toast.makeText(
                        applicationContext,
                        applicationContext.getString(R.string.auth_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )

        // Configuración del prompt
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(applicationContext.getString(R.string.auth_required))
            .setSubtitle(applicationContext.getString(R.string.use_authenticated_method))
            .setAllowedAuthenticators(
                BiometricManager.Authenticators.BIOMETRIC_STRONG or
                        BiometricManager.Authenticators.DEVICE_CREDENTIAL
            )
            .build()

        prompt.authenticate(promptInfo)
    }
}