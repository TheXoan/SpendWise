//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[MovDao](index.md)/[getMovementsForYearMonth](get-movements-for-year-month.md)

# getMovementsForYearMonth

[androidJvm]\
abstract fun [getMovementsForYearMonth](get-movements-for-year-month.md)(year: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), month: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovWithCategory](../../com.arcaneia.spendwise.data.entity/-mov-with-category/index.md)&gt;&gt;

Obtiene todos los movimientos del año y mes especificados, acompañados del nombre de su categoría mediante JOIN.

#### Return

Flow con movimientos enriquecidos con su categoría.

#### Parameters

androidJvm

| | |
|---|---|
| year | Año objetivo. |
| month | Mes objetivo. |
