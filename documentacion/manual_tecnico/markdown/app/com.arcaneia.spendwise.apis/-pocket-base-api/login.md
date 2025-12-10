//[app](../../../index.md)/[com.arcaneia.spendwise.apis](../index.md)/[PocketBaseApi](index.md)/[login](login.md)

# login

[androidJvm]\

@POST(value = &quot;api/collections/users/auth-with-password&quot;)

abstract suspend fun [login](login.md)(@Bodyrequest: [AuthRequest](../../com.arcaneia.spendwise.data.model/-auth-request/index.md)): [AuthResponse](../../com.arcaneia.spendwise.data.model/-auth-response/index.md)

Autentica un usuario mediante email y contraseña.

Este endpoint utiliza la colección `users` de PocketBase y devuelve un token de autenticación junto con la información del usuario.

#### Return

[AuthResponse](../../com.arcaneia.spendwise.data.model/-auth-response/index.md) que incluye el token JWT y los datos del usuario.

#### Parameters

androidJvm

| | |
|---|---|
| request | Objeto que contiene `email` y `password`. |
