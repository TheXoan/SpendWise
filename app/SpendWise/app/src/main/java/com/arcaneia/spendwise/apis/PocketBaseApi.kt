package com.arcaneia.spendwise.apis

import com.arcaneia.spendwise.data.model.AuthRequest
import com.arcaneia.spendwise.data.model.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PocketBaseApi {

    @POST("api/collections/users/auth-with-password")
    suspend fun login(@Body request: AuthRequest): AuthResponse
}