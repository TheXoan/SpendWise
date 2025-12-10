//[app](../../../index.md)/[com.arcaneia.spendwise.data.datastore](../index.md)/[TokenDataStore](index.md)/[saveSession](save-session.md)

# saveSession

[androidJvm]\
suspend fun [saveSession](save-session.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))

Guarda el token y el userId en una sola operación atómica.

#### Parameters

androidJvm

| | |
|---|---|
| context | Contexto necesario para acceder al DataStore. |
| token | Token JWT devuelto por el backend. |
| userId | Identificador del usuario autenticado. |
