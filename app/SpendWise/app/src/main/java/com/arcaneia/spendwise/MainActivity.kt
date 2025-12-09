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
import com.arcaneia.spendwise.apis.data.model.MovRecurRemoteDataSource
import com.arcaneia.spendwise.apis.data.model.MovRemoteDataSource

/**
 * Actividad principal de la aplicación y punto de entrada del sistema.
 *
 * Esta clase es responsable de:
 *
 * - Inicializar la base de datos local (Room) y los repositorios asociados.
 * - Crear manualmente los ViewModels globales utilizados por toda la app.
 * - Configurar y renderizar la interfaz mediante Jetpack Compose.
 * - Ejecutar autenticación biométrica previa para proteger los datos sensibles.
 * - Activar la navegación principal una vez superada la autenticación.
 *
 * `MainActivity` funciona como contenedor raíz del ciclo de vida y coordina
 * todos los componentes necesarios para iniciar la aplicación en un estado seguro.
 */
class MainActivity : AppCompatActivity() {

    /**
     * ViewModel encargado de gestionar el estado de autenticación de la aplicación.
     *
     * Se inicializa manualmente en `onCreate()` ya que se usa antes de montar la UI.
     */
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Inicialización del ViewModel que maneja el estado de autenticación.
         */
        authViewModel = AuthViewModel()

        // --- Inicialización de base de datos y repositorios ---

        /**
         * Instancia de la base de datos Room utilizada globalmente.
         */
        val db = AppDatabase.getDatabase(application)

        /**
         * Repositorios para gestionar Movimientos, Categorías y Movimientos Recurrentes.
         */
        val movRepository = MovRepository(db.movDao())
        val categoriaRepository = CategoriaRepository(db.categoriaDao())
        val movRecurRepository = MovRecurRepository(
            movRecurDao = db.movRecurDao(),
            movDao = db.movDao(), this
        )

        /**
         * DataSources remotos conectados con PocketBase.
         */
        val categoriaRemoteDataSource = CategoriaRemoteDataSource(this)
        val movRemoteDataSource = MovRemoteDataSource(this)
        val movRecurRemoteDataSource = MovRecurRemoteDataSource(this)

        /**
         * Repositorio dedicado exclusivamente a sincronizar categorías
         * entre la base de datos local y PocketBase.
         */
        val categoriaSyncRepository = CategoriaSyncRepository(
            local = db.categoriaDao(),
            remote = CategoriaRemoteDataSource(this),
            context = this
        )

        // --- Inicialización manual de ViewModels ---

        /**
         * ViewModels usados en las principales pantallas de la aplicación.
         *
         * Se crean manualmente ya que no dependen del ciclo de vida del Activity,
         * sino de la base de datos y la sincronización remota.
         */
        val movViewModel = MovViewModel(
            movRepository,
            movRemoteDataSource,
            db.categoriaDao(),
            db.movRecurDao(),
            this
        )
        val categoriaViewModel = CategoriaViewModel(categoriaRepository, categoriaRemoteDataSource, this)
        val movRecurViewModel = MovRecurViewModel(
            movRecurRepository,
            movRecurRemoteDataSource,
            db.movRecurDao(),
            this
        )
        val loginViewModel = LoginViewModel()

        // --- Renderizado de la UI mediante Jetpack Compose ---

        /**
         * Renderiza la UI inicial usando el tema definido y activa la navegación de la app.
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

        // Autenticación biométrica obligatoria
        authenticateUser()
    }

    /**
     * Ejecuta el proceso de autenticación biométrica o credenciales del dispositivo.
     *
     * ### Flujo interno:
     * 1. Verifica si el dispositivo soporta biometría o seguridad por PIN/patrón/contraseña.
     * 2. En caso de no estar disponible, muestra un mensaje apropiado.
     * 3. Si está disponible, configura un `BiometricPrompt`.
     * 4. Maneja callbacks de éxito, error y fallo.
     *
     * Si la autenticación es exitosa:
     * - Se permite la navegación normal de la app.
     *
     * Si falla o el usuario cancela:
     * - La aplicación se cierra inmediatamente por seguridad.
     */
    private fun authenticateUser() {
        val biometricManager = BiometricManager.from(this)

        val canAuth = biometricManager.canAuthenticate(
            BiometricManager.Authenticators.BIOMETRIC_STRONG or
                    BiometricManager.Authenticators.DEVICE_CREDENTIAL
        )

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

        // Executor para los callbacks biométricos
        val executor = ContextCompat.getMainExecutor(this)

        /**
         * Objeto encargado de manejar los eventos de la autenticación biométrica:
         * - Éxito
         * - Error crítico
         * - Fallo puntual (huella mal colocada, etc.)
         */
        val prompt = BiometricPrompt(
            this,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {

                /**
                 * Invocado cuando la autenticación se ha completado correctamente.
                 *
                 * Se asigna el estado de autenticación en el ViewModel y se muestra un mensaje.
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
                 * Se ejecuta cuando la autenticación arroja un error no recuperable.
                 *
                 * Finaliza completamente la aplicación para evitar acceso indebido.
                 */
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    Toast.makeText(
                        applicationContext,
                        applicationContext.getString(R.string.error) + ": $errString",
                        Toast.LENGTH_SHORT
                    ).show()

                    authViewModel.setAuthenticated(false)

                    finishAndRemoveTask()
                    kotlin.system.exitProcess(0)
                }

                /**
                 * Se llama cuando la autenticación falla pero sin errores graves.
                 *
                 * Se avisa al usuario sin cerrar el prompt.
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

        // Configuración visual del diálogo biométrico
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