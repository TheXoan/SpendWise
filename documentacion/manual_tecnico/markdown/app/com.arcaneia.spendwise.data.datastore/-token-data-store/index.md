//[app](../../../index.md)/[com.arcaneia.spendwise.data.datastore](../index.md)/[TokenDataStore](index.md)

# TokenDataStore

[androidJvm]\
object [TokenDataStore](index.md)

Objeto que gestiona la lectura y escritura del token y userId del usuario.

Este gestor permite:

- 
   Guardar token + userId en la misma operación.
- 
   Recuperar ambos como flujos (`Flow<String?>`).
- 
   Limpiar la sesión eliminando ambos valores.

## Functions

| Name | Summary |
|---|---|
| [clear](clear.md) | [androidJvm]<br>suspend fun [clear](clear.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Elimina tanto el token como el userId, cerrando la sesión local del usuario. |
| [getToken](get-token.md) | [androidJvm]<br>fun [getToken](get-token.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): Flow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?&gt;<br>Devuelve un flujo que emite el token almacenado. |
| [getUserId](get-user-id.md) | [androidJvm]<br>fun [getUserId](get-user-id.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): Flow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?&gt;<br>Devuelve un flujo que emite el userId almacenado. |
| [saveSession](save-session.md) | [androidJvm]<br>suspend fun [saveSession](save-session.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))<br>Guarda el token y el userId en una sola operación atómica. |
