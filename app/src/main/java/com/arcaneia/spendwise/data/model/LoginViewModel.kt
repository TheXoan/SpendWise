package com.arcaneia.spendwise.data.model

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcaneia.spendwise.R
import com.arcaneia.spendwise.apis.RetrofitClient
import com.arcaneia.spendwise.data.datastore.TokenDataStore
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar la lógica del proceso de inicio de sesión.
 *
 * Expone el estado necesario para la pantalla de login, incluyendo:
 * - Email y contraseña ingresados.
 * - Indicador de carga mientras se procesa la autenticación.
 * - Mensajes de error si ocurre un problema.
 * - Indicador de éxito cuando el login ha sido completado correctamente.
 *
 * Este ViewModel interactúa directamente con la API mediante [RetrofitClient]
 * y guarda la sesión del usuario en [TokenDataStore].
 */
class LoginViewModel : ViewModel() {

    /** Email ingresado por el usuario en la pantalla de login. */
    var email by mutableStateOf("")

    /** Contraseña ingresada por el usuario. */
    var password by mutableStateOf("")

    /** Indica si se está procesando la solicitud de login. */
    var isLoading by mutableStateOf(false)

    /** Contiene el mensaje de error a mostrar en caso de fallo. */
    var errorMessage by mutableStateOf<String?>(null)

    /** Indica si el usuario logró autenticarse exitosamente. */
    var loginSuccess by mutableStateOf(false)

    /**
     * Intenta iniciar sesión con las credenciales ingresadas.
     *
     * Flujo del proceso:
     * 1. Evita múltiples clics si ya se está cargando.
     * 2. Llama al endpoint de autenticación con el email y contraseña.
     * 3. Si la respuesta es correcta, guarda el token y el userId en [TokenDataStore].
     * 4. Si ocurre una excepción, establece un mensaje de error para la UI.
     * 5. Finaliza el estado de carga en el bloque `finally`.
     *
     * @param context Contexto necesario para acceder a recursos (strings) y DataStore.
     */
    fun login(context: Context) {
        if (isLoading) return

        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val response = RetrofitClient.api.login(
                    AuthRequest(identity = email, password = password)
                )

                TokenDataStore.saveSession(
                    context,
                    response.token,
                    response.record.id
                )

                loginSuccess = true

            } catch (e: Exception) {
                errorMessage = context.getString(
                    R.string.wrong_password)
            } finally {
                isLoading = false
            }
        }
    }
}