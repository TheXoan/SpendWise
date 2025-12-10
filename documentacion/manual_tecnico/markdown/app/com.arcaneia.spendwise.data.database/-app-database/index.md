//[app](../../../index.md)/[com.arcaneia.spendwise.data.database](../index.md)/[AppDatabase](index.md)

# AppDatabase

abstract class [AppDatabase](index.md) : [RoomDatabase](https://developer.android.com/reference/kotlin/androidx/room/RoomDatabase.html)

Base de datos principal de la aplicación, implementada con Room.

Aquí se registran todas las entidades y DAOs utilizados en la capa de persistencia. La base de datos incluye:

- 
   [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)
- 
   [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)
- 
   [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)

También aplica convertidores personalizados mediante [Converters](../-converters/index.md) para transformar tipos complejos a tipos compatibles con la base de datos.

La base de datos utiliza un patrón **Singleton** para garantizar que solo exista una única instancia activa durante el ciclo de vida de la aplicación.

#### See also

| |
|---|
| [RoomDatabase](https://developer.android.com/reference/kotlin/androidx/room/RoomDatabase.html) |

## Constructors

| | |
|---|---|
| [AppDatabase](-app-database.md) | [androidJvm]<br>constructor() |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [driver](index.md#1688947723%2FProperties%2F-912451524) | [androidJvm]<br>val [driver](index.md#1688947723%2FProperties%2F-912451524): [SQLiteDriver](https://developer.android.com/reference/kotlin/androidx/sqlite/SQLiteDriver.html) |
| [invalidationTracker](index.md#-990093491%2FProperties%2F-912451524) | [androidJvm]<br>open val [invalidationTracker](index.md#-990093491%2FProperties%2F-912451524): [InvalidationTracker](https://developer.android.com/reference/kotlin/androidx/room/InvalidationTracker.html) |
| [isOpen](index.md#-277138657%2FProperties%2F-912451524) | [androidJvm]<br>open val [isOpen](index.md#-277138657%2FProperties%2F-912451524): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) |
| [openHelper](index.md#-1864821605%2FProperties%2F-912451524) | [androidJvm]<br>open val [openHelper](index.md#-1864821605%2FProperties%2F-912451524): [SupportSQLiteOpenHelper](https://developer.android.com/reference/kotlin/androidx/sqlite/db/SupportSQLiteOpenHelper.html) |
| [path](index.md#86731822%2FProperties%2F-912451524) | [androidJvm]<br>val [path](index.md#86731822%2FProperties%2F-912451524): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? |
| [queryExecutor](index.md#-177284564%2FProperties%2F-912451524) | [androidJvm]<br>open val [queryExecutor](index.md#-177284564%2FProperties%2F-912451524): [Executor](https://developer.android.com/reference/kotlin/java/util/concurrent/Executor.html) |
| [suspendingTransactionContext](index.md#-235457316%2FProperties%2F-912451524) | [androidJvm]<br>val [suspendingTransactionContext](index.md#-235457316%2FProperties%2F-912451524): [ThreadLocal](https://developer.android.com/reference/kotlin/java/lang/ThreadLocal.html)&lt;[CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.coroutines/-coroutine-context/index.html)&gt; |
| [transactionExecutor](index.md#722320214%2FProperties%2F-912451524) | [androidJvm]<br>open val [transactionExecutor](index.md#722320214%2FProperties%2F-912451524): [Executor](https://developer.android.com/reference/kotlin/java/util/concurrent/Executor.html) |

## Functions

| Name | Summary |
|---|---|
| [assertNotMainThread](index.md#-917214377%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [assertNotMainThread](index.md#-917214377%2FFunctions%2F-912451524)() |
| [assertNotSuspendingTransaction](index.md#1166251624%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [assertNotSuspendingTransaction](index.md#1166251624%2FFunctions%2F-912451524)() |
| [beginTransaction](index.md#1020009182%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [~~beginTransaction~~](index.md#1020009182%2FFunctions%2F-912451524)() |
| [categoriaDao](categoria-dao.md) | [androidJvm]<br>abstract fun [categoriaDao](categoria-dao.md)(): [CategoriaDao](../../com.arcaneia.spendwise.data.dao/-categoria-dao/index.md) |
| [clearAllTables](index.md#404244410%2FFunctions%2F-912451524) | [androidJvm]<br>abstract fun [clearAllTables](index.md#404244410%2FFunctions%2F-912451524)() |
| [close](index.md#1674273423%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [close](index.md#1674273423%2FFunctions%2F-912451524)() |
| [compileStatement](index.md#162913197%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [compileStatement](index.md#162913197%2FFunctions%2F-912451524)(sql: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [SupportSQLiteStatement](https://developer.android.com/reference/kotlin/androidx/sqlite/db/SupportSQLiteStatement.html) |
| [createAutoMigrations](index.md#1420085996%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [createAutoMigrations](index.md#1420085996%2FFunctions%2F-912451524)(autoMigrationSpecs: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-map/index.html)&lt;[KClass](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.reflect/-k-class/index.html)&lt;out [AutoMigrationSpec](https://developer.android.com/reference/kotlin/androidx/room/migration/AutoMigrationSpec.html)&gt;, [AutoMigrationSpec](https://developer.android.com/reference/kotlin/androidx/room/migration/AutoMigrationSpec.html)&gt;): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Migration](https://developer.android.com/reference/kotlin/androidx/room/migration/Migration.html)&gt; |
| [endTransaction](index.md#622722960%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [~~endTransaction~~](index.md#622722960%2FFunctions%2F-912451524)() |
| [getAutoMigrations](index.md#178130989%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [~~getAutoMigrations~~](index.md#178130989%2FFunctions%2F-912451524)(autoMigrationSpecs: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-map/index.html)&lt;[Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;out [AutoMigrationSpec](https://developer.android.com/reference/kotlin/androidx/room/migration/AutoMigrationSpec.html)&gt;, [AutoMigrationSpec](https://developer.android.com/reference/kotlin/androidx/room/migration/AutoMigrationSpec.html)&gt;): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Migration](https://developer.android.com/reference/kotlin/androidx/room/migration/Migration.html)&gt; |
| [getCoroutineScope](index.md#-1278223499%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getCoroutineScope](index.md#-1278223499%2FFunctions%2F-912451524)(): CoroutineScope |
| [getQueryContext](index.md#-779149974%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getQueryContext](index.md#-779149974%2FFunctions%2F-912451524)(): [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.coroutines/-coroutine-context/index.html) |
| [getRequiredAutoMigrationSpecClasses](index.md#-432702106%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [getRequiredAutoMigrationSpecClasses](index.md#-432702106%2FFunctions%2F-912451524)(): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-set/index.html)&lt;[KClass](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.reflect/-k-class/index.html)&lt;out [AutoMigrationSpec](https://developer.android.com/reference/kotlin/androidx/room/migration/AutoMigrationSpec.html)&gt;&gt; |
| [getRequiredAutoMigrationSpecs](index.md#1623281881%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [~~getRequiredAutoMigrationSpecs~~](index.md#1623281881%2FFunctions%2F-912451524)(): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-set/index.html)&lt;[Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;out [AutoMigrationSpec](https://developer.android.com/reference/kotlin/androidx/room/migration/AutoMigrationSpec.html)&gt;&gt; |
| [getTypeConverter](index.md#-1472154772%2FFunctions%2F-912451524) | [androidJvm]<br>open fun &lt;[T](index.md#-1472154772%2FFunctions%2F-912451524) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-any/index.html)&gt; [~~getTypeConverter~~](index.md#-1472154772%2FFunctions%2F-912451524)(klass: [Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;[T](index.md#-1472154772%2FFunctions%2F-912451524)&gt;): [T](index.md#-1472154772%2FFunctions%2F-912451524)?<br>fun &lt;[T](index.md#2031305957%2FFunctions%2F-912451524) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-any/index.html)&gt; [getTypeConverter](index.md#2031305957%2FFunctions%2F-912451524)(klass: [KClass](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.reflect/-k-class/index.html)&lt;[T](index.md#2031305957%2FFunctions%2F-912451524)&gt;): [T](index.md#2031305957%2FFunctions%2F-912451524) |
| [inCompatibilityMode](index.md#908529465%2FFunctions%2F-912451524) | [androidJvm]<br>fun [inCompatibilityMode](index.md#908529465%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) |
| [init](index.md#1039887154%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [init](index.md#1039887154%2FFunctions%2F-912451524)(configuration: [DatabaseConfiguration](https://developer.android.com/reference/kotlin/androidx/room/DatabaseConfiguration.html)) |
| [inTransaction](index.md#-1889647314%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [inTransaction](index.md#-1889647314%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) |
| [movDao](mov-dao.md) | [androidJvm]<br>abstract fun [movDao](mov-dao.md)(): [MovDao](../../com.arcaneia.spendwise.data.dao/-mov-dao/index.md) |
| [movRecurDao](mov-recur-dao.md) | [androidJvm]<br>abstract fun [movRecurDao](mov-recur-dao.md)(): [MovRecurDao](../../com.arcaneia.spendwise.data.dao/-mov-recur-dao/index.md) |
| [query](index.md#604106995%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [query](index.md#604106995%2FFunctions%2F-912451524)(query: [SupportSQLiteQuery](https://developer.android.com/reference/kotlin/androidx/sqlite/db/SupportSQLiteQuery.html), signal: [CancellationSignal](https://developer.android.com/reference/kotlin/android/os/CancellationSignal.html)?): [Cursor](https://developer.android.com/reference/kotlin/android/database/Cursor.html)<br>open fun [query](index.md#-1376474873%2FFunctions%2F-912451524)(query: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), args: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-array/index.html)&lt;out [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-any/index.html)?&gt;?): [Cursor](https://developer.android.com/reference/kotlin/android/database/Cursor.html) |
| [runInTransaction](index.md#1063989044%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [runInTransaction](index.md#1063989044%2FFunctions%2F-912451524)(body: [Runnable](https://developer.android.com/reference/kotlin/java/lang/Runnable.html))<br>open fun &lt;[V](index.md#-1842697888%2FFunctions%2F-912451524)&gt; [runInTransaction](index.md#-1842697888%2FFunctions%2F-912451524)(body: [Callable](https://developer.android.com/reference/kotlin/java/util/concurrent/Callable.html)&lt;[V](index.md#-1842697888%2FFunctions%2F-912451524)&gt;): [V](index.md#-1842697888%2FFunctions%2F-912451524) |
| [setTransactionSuccessful](index.md#954356125%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [~~setTransactionSuccessful~~](index.md#954356125%2FFunctions%2F-912451524)() |
| [useConnection](index.md#470906822%2FFunctions%2F-912451524) | [androidJvm]<br>suspend fun &lt;[R](index.md#470906822%2FFunctions%2F-912451524)&gt; [useConnection](index.md#470906822%2FFunctions%2F-912451524)(isReadOnly: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html), block: suspend ([Transactor](https://developer.android.com/reference/kotlin/androidx/room/Transactor.html)) -&gt; [R](index.md#470906822%2FFunctions%2F-912451524)): [R](index.md#470906822%2FFunctions%2F-912451524) |
