//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[MovDao](index.md)/[getMonthsFromYear](get-months-from-year.md)

# getMonthsFromYear

[androidJvm]\
abstract fun [getMonthsFromYear](get-months-from-year.md)(year: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt;&gt;

Obtiene los meses que contienen movimientos para un año específico.

#### Return

Un Flow con meses únicos ordenados ascendentemente.

#### Parameters

androidJvm

| | |
|---|---|
| year | Año en formato YYYY. |
