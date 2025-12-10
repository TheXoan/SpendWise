//[app](../../../index.md)/[com.arcaneia.spendwise.data.datastore](../index.md)/[TokenDataStore](index.md)/[getUserId](get-user-id.md)

# getUserId

[androidJvm]\
fun [getUserId](get-user-id.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): Flow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?&gt;

Devuelve un flujo que emite el userId almacenado.

#### Return

Un flujo que emite el userId actual o null si no está guardado.

#### Parameters

androidJvm

| | |
|---|---|
| context | Contexto asociado al DataStore. |
