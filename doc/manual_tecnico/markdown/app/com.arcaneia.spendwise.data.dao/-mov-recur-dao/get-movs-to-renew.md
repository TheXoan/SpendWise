//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[MovRecurDao](index.md)/[getMovsToRenew](get-movs-to-renew.md)

# getMovsToRenew

[androidJvm]\
abstract suspend fun [getMovsToRenew](get-movs-to-renew.md)(today: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)&gt;

Obtiene todos los movimientos recurrentes cuya fecha de renovación (`data_rnv`) sea anterior o igual a la fecha indicada.

Esta función es crucial para determinar qué movimientos deben renovarse en el día actual o han vencido.

#### Return

Una lista con los movimientos recurrentes [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md) que deben renovarse.

#### Parameters

androidJvm

| | |
|---|---|
| today | Fecha límite en formato `"YYYY-MM-DD"`. |
