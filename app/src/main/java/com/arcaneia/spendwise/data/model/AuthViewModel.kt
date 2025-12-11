package com.arcaneia.spendwise.data.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * ViewModel responsable de gestionar el estado de autenticación de la aplicación.
 *
 * Este ViewModel expone un estado observable que permite bloquear o permitir la
 * navegación en función de si el usuario ya pasó la pantalla de carga/autenticación.
 *
 * `isAuthenticated` se utiliza directamente en la `SplashScreen` para desencadenar
 * la navegación a la pantalla principal una vez que su valor se establece en `true`.
 *
 * @constructor Crea un [AuthViewModel] inicializando el estado como no autenticado.
 */
class AuthViewModel : ViewModel() {

    /**
     * Estado observable que indica si el usuario está autenticado.
     *
     * - `false` → La app permanece en la SplashScreen.
     * - `true` → La app navega a la pantalla principal.
     *
     * Solo puede modificarse desde dentro del ViewModel, lo que expone un patrón
     * de encapsulación adecuado.
     */
    var isAuthenticated =
        mutableStateOf(
            false
        )
        private set

    /**
     * Actualiza el estado de autenticación.
     *
     * Se invoca típicamente tras completar los procesos de verificación o carga
     * inicial de datos.
     *
     * @param success `true` si la autenticación/verificación fue exitosa.
     */
    fun setAuthenticated(success: Boolean) {
        isAuthenticated.value = success
    }
}