//[app](../../../index.md)/[com.arcaneia.spendwise.data.repository](../index.md)/[MovRepository](index.md)/[getMovementsForYearMonth](get-movements-for-year-month.md)

# getMovementsForYearMonth

[androidJvm]\
fun [getMovementsForYearMonth](get-movements-for-year-month.md)(year: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), month: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovWithCategory](../../com.arcaneia.spendwise.data.entity/-mov-with-category/index.md)&gt;&gt;

Obtiene todos los movimientos pertenecientes a un año y mes específicos, incluyendo la información de la categoría mediante un JOIN.

#### Return

Un flujo de listas de [MovWithCategory](../../com.arcaneia.spendwise.data.entity/-mov-with-category/index.md).

#### Parameters

androidJvm

| | |
|---|---|
| year | Año en formato `"YYYY"`. |
| month | Mes en formato `"MM"`. |
