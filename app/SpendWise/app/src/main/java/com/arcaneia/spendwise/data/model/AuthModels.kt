package com.arcaneia.spendwise.data.model

data class AuthRequest(
    val identity: String,
    val password: String
)

data class AuthResponse(
    val token: String,
    val record: UserRecord
)

data class UserRecord(
    val id: String,
    val email: String,
    val username: String?
)