//[app](../../../index.md)/[com.arcaneia.spendwise.data.repository](../index.md)/[MovRepository](index.md)

# MovRepository

[androidJvm]\
class [MovRepository](index.md)(movDao: [MovDao](../../com.arcaneia.spendwise.data.dao/-mov-dao/index.md))

Repositorio encargado de gestionar todas las operaciones relacionadas con la entidad [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md).

Funciona como una capa intermedia entre el [MovDao](../../com.arcaneia.spendwise.data.dao/-mov-dao/index.md) y los ViewModels, proporcionando una API limpia y desacoplada para:

- 
   Insertar nuevos movimientos.
- 
   Actualizar y eliminar movimientos existentes.
- 
   Consultar información estadística como balance mensual, años disponibles y meses disponibles.
- 
   Observar en tiempo real la lista completa de movimientos almacenados.

El repositorio permite mantener una arquitectura más modular y facilita pruebas unitarias, ya que aísla el acceso directo a Room detrás de esta abstracción.

## Constructors

| | |
|---|---|
| [MovRepository](-mov-repository.md) | [androidJvm]<br>constructor(movDao: [MovDao](../../com.arcaneia.spendwise.data.dao/-mov-dao/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [androidJvm]<br>suspend fun [delete](delete.md)(mov: [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md))<br>Elimina un movimiento de la base de datos. |
| [getAllFlow](get-all-flow.md) | [androidJvm]<br>fun [getAllFlow](get-all-flow.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)&gt;&gt;<br>Devuelve un flujo reactivo con la lista completa de movimientos almacenados. |
| [getBalanceMesActual](get-balance-mes-actual.md) | [androidJvm]<br>fun [getBalanceMesActual](get-balance-mes-actual.md)(): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-double/index.html)&gt;<br>Obtiene el balance del mes actual, calculado como: **Ingresos − Gastos**. |
| [getById](get-by-id.md) | [androidJvm]<br>suspend fun [getById](get-by-id.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)): [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)?<br>Obtiene un movimiento por su ID local. |
| [getMonthsFromYear](get-months-from-year.md) | [androidJvm]<br>fun [getMonthsFromYear](get-months-from-year.md)(year: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt;&gt;<br>Obtiene los meses asociados a un año específico en los que existen movimientos. |
| [getMovementsForYearMonth](get-movements-for-year-month.md) | [androidJvm]<br>fun [getMovementsForYearMonth](get-movements-for-year-month.md)(year: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), month: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovWithCategory](../../com.arcaneia.spendwise.data.entity/-mov-with-category/index.md)&gt;&gt;<br>Obtiene todos los movimientos pertenecientes a un año y mes específicos, incluyendo la información de la categoría mediante un JOIN. |
| [getYearsWithValues](get-years-with-values.md) | [androidJvm]<br>fun [getYearsWithValues](get-years-with-values.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt;&gt;<br>Obtiene la lista de años en los que existen movimientos registrados. |
| [insert](insert.md) | [androidJvm]<br>suspend fun [insert](insert.md)(mov: [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-long/index.html)<br>Inserta un nuevo movimiento en la base de datos local. |
| [update](update.md) | [androidJvm]<br>suspend fun [update](update.md)(mov: [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md))<br>Actualiza un movimiento previamente existente en la base de datos. |
