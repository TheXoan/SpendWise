package com.arcaneia.spendwise.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class AuthViewModel : ViewModel() {
    // Permitirá bloquear la navegación mientras no se autentique
    var isAuthenticated = mutableStateOf(false)
        private set

    fun setAuthenticated(success: Boolean) {
        isAuthenticated.value = success
    }
}