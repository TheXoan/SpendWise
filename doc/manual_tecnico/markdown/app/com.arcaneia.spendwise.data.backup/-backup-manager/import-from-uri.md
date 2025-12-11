//[app](../../../index.md)/[com.arcaneia.spendwise.data.backup](../index.md)/[BackupManager](index.md)/[importFromUri](import-from-uri.md)

# importFromUri

[androidJvm]\
suspend fun [importFromUri](import-from-uri.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)

Importa y restaura la base de datos desde un archivo ZIP.

Antes de realizar la restauración, la base de datos de Room se cierra para evitar corrupción. Luego, los archivos incluidos en el ZIP se extraen y reemplazan la base de datos actual.

#### Return

`true` si la extracción y restauración se realizan correctamente, `false` si ocurre alguna excepción.

#### Parameters

androidJvm

| | |
|---|---|
| uri | URI del archivo ZIP desde el cual se restaurará la base de datos. |
