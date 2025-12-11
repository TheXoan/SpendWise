//[app](../../../index.md)/[com.arcaneia.spendwise.data.backup](../index.md)/[BackupManager](index.md)/[exportWriteToUri](export-write-to-uri.md)

# exportWriteToUri

[androidJvm]\
suspend fun [exportWriteToUri](export-write-to-uri.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)

Escribe la copia de seguridad temporal en una ubicación elegida por el usuario utilizando el **Storage Access Framework (SAF)**.

#### Return

`true` si la operación finaliza correctamente, `false` si ocurre algún error.

#### Parameters

androidJvm

| | |
|---|---|
| uri | URI proporcionada por el usuario donde se guardará el archivo ZIP. |
