//[app](../../index.md)/[com.arcaneia.spendwise.utils](index.md)/[uiFormat](ui-format.md)

# uiFormat

[androidJvm]\
fun [uiFormat](ui-format.md)(date: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)

Formatea una fecha al formato amigable de UI (`dd/MM/yyyy`).

Esta función:

- 
   Recorta el string a 10 caracteres por seguridad.
- 
   Intenta parsearlo como `yyyy-MM-dd`.
- 
   Si falla, devuelve la fecha original sin modificar.

#### Return

Fecha convertida al formato `dd/MM/yyyy`, o el mismo valor si ocurre un error.

#### Parameters

androidJvm

| | |
|---|---|
| date | Fecha en formato `yyyy-MM-dd` o similar. |
