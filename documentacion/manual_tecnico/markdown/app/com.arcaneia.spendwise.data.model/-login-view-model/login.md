//[app](../../../index.md)/[com.arcaneia.spendwise.data.model](../index.md)/[LoginViewModel](index.md)/[login](login.md)

# login

[androidJvm]\
fun [login](login.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))

Intenta iniciar sesión con las credenciales ingresadas.

Flujo del proceso:

1. 
   Evita múltiples clics si ya se está cargando.
2. 
   Llama al endpoint de autenticación con el email y contraseña.
3. 
   Si la respuesta es correcta, guarda el token y el userId en [TokenDataStore](../../com.arcaneia.spendwise.data.datastore/-token-data-store/index.md).
4. 
   Si ocurre una excepción, establece un mensaje de error para la UI.
5. 
   Finaliza el estado de carga en el bloque `finally`.

#### Parameters

androidJvm

| | |
|---|---|
| context | Contexto necesario para acceder a recursos (strings) y DataStore. |
