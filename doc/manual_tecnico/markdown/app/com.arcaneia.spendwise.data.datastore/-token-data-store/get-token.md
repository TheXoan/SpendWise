//[app](../../../index.md)/[com.arcaneia.spendwise.data.datastore](../index.md)/[TokenDataStore](index.md)/[getToken](get-token.md)

# getToken

[androidJvm]\
fun [getToken](get-token.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): Flow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?&gt;

Devuelve un flujo que emite el token almacenado.

#### Return

Un flujo que emite el token actual o null si no existe.

#### Parameters

androidJvm

| | |
|---|---|
| context | Contexto asociado al DataStore. |
