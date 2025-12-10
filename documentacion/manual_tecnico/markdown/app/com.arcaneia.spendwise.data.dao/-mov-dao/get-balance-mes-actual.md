//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[MovDao](index.md)/[getBalanceMesActual](get-balance-mes-actual.md)

# getBalanceMesActual

[androidJvm]\
abstract fun [getBalanceMesActual](get-balance-mes-actual.md)(): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-double/index.html)&gt;

Calcula el balance total del mes actual:

**Ingresos − Gastos**, considerando únicamente movimientos cuyo `data_mov` pertenezca al mes corriente.

#### Return

Un Flow con el valor del balance actualizado en tiempo real.
