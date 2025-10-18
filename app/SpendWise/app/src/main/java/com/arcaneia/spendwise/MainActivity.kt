package com.arcaneia.spendwise

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.activity.compose.setContent
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.arcaneia.spendwise.navigation.AppNavigation
import com.arcaneia.spendwise.screens.SplashScreen

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Pantalla inicial: splash visual
        setContent {
            SplashScreen()
        }

        // Lanza autenticación
        authenticateUser()
    }

    private fun authenticateUser() {
        val biometricManager = BiometricManager.from(this)
        val can = biometricManager.canAuthenticate(
            BiometricManager.Authenticators.BIOMETRIC_STRONG or
                    BiometricManager.Authenticators.DEVICE_CREDENTIAL
        )

        if (can != BiometricManager.BIOMETRIC_SUCCESS) {
            val msg = when (can) {
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                    "Tu dispositivo no tiene sensor biométrico."
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                    "No tienes huella, rostro o PIN configurado.\nConfigúralo en Ajustes → Seguridad."
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                    "El sensor biométrico no está disponible."
                else -> "Autenticación no disponible."
            }

            // ✅ En lugar de cerrar la app, mostramos un mensaje
            runOnUiThread {
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
            }
            return
        }

        val executor = ContextCompat.getMainExecutor(this)
        val prompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        setContent {
                            AppNavigation()
                        }
                    }, 2000)
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    // ✅ No cierres aquí. Solo muestra un aviso.
                    runOnUiThread {
                        Toast.makeText(
                            applicationContext,
                            "Error: $errString",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onAuthenticationFailed() {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "No reconocido", Toast.LENGTH_SHORT).show()
                    }
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