package com.arcaneia.spendwise.data.model

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcaneia.spendwise.R
import com.arcaneia.spendwise.apis.RetrofitClient
import com.arcaneia.spendwise.data.datastore.TokenDataStore
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var loginSuccess by mutableStateOf(false)

    fun login(context: Context) {
        if (isLoading) return

        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val response = RetrofitClient.api.login(
                    AuthRequest(identity = email, password = password)
                )

                // Guardamos token JWT
                TokenDataStore.saveToken(context, response.token)

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