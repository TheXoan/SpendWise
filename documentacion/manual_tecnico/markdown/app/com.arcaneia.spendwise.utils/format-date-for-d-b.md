//[app](../../index.md)/[com.arcaneia.spendwise.utils](index.md)/[formatDateForDB](format-date-for-d-b.md)

# formatDateForDB

[androidJvm]\
fun [formatDateForDB](format-date-for-d-b.md)(date: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)

Convierte una fecha desde el formato UI (`dd/MM/yyyy`) al formato utilizado en la base de datos (`yyyy-MM-dd`).

#### Return

Fecha convertida en formato `yyyy-MM-dd`.

#### Parameters

androidJvm

| | |
|---|---|
| date | Fecha en formato `dd/MM/yyyy`. |

#### Throws

| | |
|---|---|
| [Exception](https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html) | si el formato de entrada no es válido. |
