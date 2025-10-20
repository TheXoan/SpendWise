package com.arcaneia.spendwise

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.arcaneia.spendwise.navigation.AppNavigation
import com.arcaneia.spendwise.ui.theme.SpendWiseTheme
import com.arcaneia.spendwise.viewmodel.AuthViewModel

class MainActivity : FragmentActivity() {

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Este objeto cambiará a true cuando se autentique el usuario e iniciará la navegación de vistas
        authViewModel = AuthViewModel()

        setContent {
            // Habilita la gestión dinámica de estilos y temas
            SpendWiseTheme {  AppNavigation(authViewModel = authViewModel) }
        }

        // Se lanza autenticación biométrica
        authenticateUser()
    }

    /**
     * Nos permitirá autenticar al usuario mediante biometría o patrón/PIN de desbloqueo
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

        val executor = ContextCompat.getMainExecutor(this)
        val prompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    authViewModel.setAuthenticated(true)
                    Toast.makeText(applicationContext, "Autenticación correcta", Toast.LENGTH_SHORT).show()
                }
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    Toast.makeText(applicationContext, "Error: $errString", Toast.LENGTH_SHORT).show()
                    authViewModel.setAuthenticated(false)
                }

                override fun onAuthenticationFailed() {
                    Toast.makeText(applicationContext, "Fallo de autenticación", Toast.LENGTH_SHORT).show()
                }
            })

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