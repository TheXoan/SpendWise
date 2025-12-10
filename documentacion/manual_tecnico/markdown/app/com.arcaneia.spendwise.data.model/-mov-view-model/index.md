//[app](../../../index.md)/[com.arcaneia.spendwise.data.model](../index.md)/[MovViewModel](index.md)

# MovViewModel

[androidJvm]\
class [MovViewModel](index.md)(repository: [MovRepository](../../com.arcaneia.spendwise.data.repository/-mov-repository/index.md), remoteDataSource: [MovRemoteDataSource](../../com.arcaneia.spendwise.apis.data.model/-mov-remote-data-source/index.md), categoriaDao: [CategoriaDao](../../com.arcaneia.spendwise.data.dao/-categoria-dao/index.md), movRecurDao: [MovRecurDao](../../com.arcaneia.spendwise.data.dao/-mov-recur-dao/index.md), appContext: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

ViewModel encargado de gestionar los **movimientos simples** (`Mov`) siguiendo un enfoque **offline-first con sincronización inmediata**.

Proporciona:

- 
   Inserción, actualización y eliminación sincronizadas con PocketBase.
- 
   Cálculo de estadísticas (balance mensual).
- 
   Filtros reactivos por año y mes.
- 
   Flujo de movimientos enriquecidos con su categoría (`MovWithCategory`).

### Enfoque offline-first:

- 
   Todas las operaciones se aplican **primero en local** (Room).
- 
   Inmediatamente después se sincronizan con la API remota.
- 
   Si la categoría o el mov_recur aún no tienen `remote_id`, la sincronización remota se omite hasta que exista.

### Flujos importantes:

- 
   [movs](movs.md): lista completa de movimientos simples registrados.
- 
   [balanceMes](balance-mes.md): balance del mes actual.
- 
   [yearsAvailable](years-available.md) y [monthsAvailable](months-available.md): filtros disponibles según los datos almacenados.
- 
   [movements](movements.md): lista filtrada por año + mes, junto con su categoría.

## Constructors

| | |
|---|---|
| [MovViewModel](-mov-view-model.md) | [androidJvm]<br>constructor(repository: [MovRepository](../../com.arcaneia.spendwise.data.repository/-mov-repository/index.md), remoteDataSource: [MovRemoteDataSource](../../com.arcaneia.spendwise.apis.data.model/-mov-remote-data-source/index.md), categoriaDao: [CategoriaDao](../../com.arcaneia.spendwise.data.dao/-categoria-dao/index.md), movRecurDao: [MovRecurDao](../../com.arcaneia.spendwise.data.dao/-mov-recur-dao/index.md), appContext: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Properties

| Name | Summary |
|---|---|
| [balanceMes](balance-mes.md) | [androidJvm]<br>val [balanceMes](balance-mes.md): StateFlow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-double/index.html)&gt;<br>Flujo con el balance mensual del usuario. |
| [monthsAvailable](months-available.md) | [androidJvm]<br>val [monthsAvailable](months-available.md): StateFlow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt;&gt;<br>Meses disponibles para el año seleccionado. |
| [movements](movements.md) | [androidJvm]<br>val [movements](movements.md): StateFlow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovWithCategory](../../com.arcaneia.spendwise.data.entity/-mov-with-category/index.md)&gt;&gt;<br>Flujo reactivo con los movimientos filtrados por año y mes, incluyendo su categoría (`MovWithCategory`). |
| [movs](movs.md) | [androidJvm]<br>val [movs](movs.md): StateFlow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)&gt;&gt;<br>Lista de movimientos simple observable por la UI. |
| [selectedMonthFlow](selected-month-flow.md) | [androidJvm]<br>val [selectedMonthFlow](selected-month-flow.md): StateFlow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?&gt;<br>Mes seleccionado para filtrar movimientos. |
| [selectedYearFlow](selected-year-flow.md) | [androidJvm]<br>val [selectedYearFlow](selected-year-flow.md): StateFlow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?&gt;<br>Año seleccionado por la UI para filtrar movimientos. |
| [yearsAvailable](years-available.md) | [androidJvm]<br>val [yearsAvailable](years-available.md): StateFlow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt;&gt;<br>Lista de años disponibles según los movimientos registrados. |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](index.md#383812252%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](index.md#383812252%2FFunctions%2F-912451524)(closeable: [AutoCloseable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-auto-closeable/index.html))<br>fun [addCloseable](index.md#1722490497%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), closeable: [AutoCloseable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-auto-closeable/index.html)) |
| [delete](delete.md) | [androidJvm]<br>fun [delete](delete.md)(mov: [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)): Job<br>Elimina un movimiento simple tanto en PocketBase como en la base de datos local. |
| [getCloseable](index.md#1102255800%2FFunctions%2F-912451524) | [androidJvm]<br>fun &lt;[T](index.md#1102255800%2FFunctions%2F-912451524) : [AutoCloseable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-auto-closeable/index.html)&gt; [getCloseable](index.md#1102255800%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [T](index.md#1102255800%2FFunctions%2F-912451524)? |
| [insert](insert.md) | [androidJvm]<br>fun [insert](insert.md)(mov: [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)): Job<br>Inserta un movimiento simple en Room y luego intenta subirlo a PocketBase. |
| [onMonthSelected](on-month-selected.md) | [androidJvm]<br>fun [onMonthSelected](on-month-selected.md)(month: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))<br>Establece el mes seleccionado. |
| [onYearSelected](on-year-selected.md) | [androidJvm]<br>fun [onYearSelected](on-year-selected.md)(year: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))<br>Establece el año seleccionado y reinicia la selección de mes. |
| [update](update.md) | [androidJvm]<br>fun [update](update.md)(newMov: [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)): Job<br>Actualiza un movimiento simple tanto en local como en remoto. |
