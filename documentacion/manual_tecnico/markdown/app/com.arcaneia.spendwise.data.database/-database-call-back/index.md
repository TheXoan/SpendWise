//[app](../../../index.md)/[com.arcaneia.spendwise.data.database](../index.md)/[DatabaseCallBack](index.md)

# DatabaseCallBack

[androidJvm]\
class [DatabaseCallBack](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : [RoomDatabase.Callback](https://developer.android.com/reference/kotlin/androidx/room/RoomDatabase.Callback.html)

Callback personalizado para la base de datos Room.

Esta clase permite ejecutar operaciones automáticamente cuando la base de datos se abre, utilizando el método [onOpen](on-open.md). En este caso, se asegura de que exista una categoría predeterminada con ID = 1 bajo el nombre `"Recurrente"`.

Este callback se registra en la base de datos dentro de [AppDatabase](../-app-database/index.md) mediante:

```kotlin
.addCallback(DatabaseCallBack(context))
```

## Constructors

| | |
|---|---|
| [DatabaseCallBack](-database-call-back.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Functions

| Name | Summary |
|---|---|
| [onCreate](index.md#129191578%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onCreate](index.md#129191578%2FFunctions%2F-912451524)(connection: [SQLiteConnection](https://developer.android.com/reference/kotlin/androidx/sqlite/SQLiteConnection.html))<br>open fun [onCreate](index.md#1274653310%2FFunctions%2F-912451524)(db: [SupportSQLiteDatabase](https://developer.android.com/reference/kotlin/androidx/sqlite/db/SupportSQLiteDatabase.html)) |
| [onDestructiveMigration](index.md#1393147178%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onDestructiveMigration](index.md#1393147178%2FFunctions%2F-912451524)(connection: [SQLiteConnection](https://developer.android.com/reference/kotlin/androidx/sqlite/SQLiteConnection.html))<br>open fun [onDestructiveMigration](index.md#-1350530802%2FFunctions%2F-912451524)(db: [SupportSQLiteDatabase](https://developer.android.com/reference/kotlin/androidx/sqlite/db/SupportSQLiteDatabase.html)) |
| [onOpen](index.md#1627010316%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onOpen](index.md#1627010316%2FFunctions%2F-912451524)(connection: [SQLiteConnection](https://developer.android.com/reference/kotlin/androidx/sqlite/SQLiteConnection.html))<br>[androidJvm]<br>open override fun [onOpen](on-open.md)(db: [SupportSQLiteDatabase](https://developer.android.com/reference/kotlin/androidx/sqlite/db/SupportSQLiteDatabase.html))<br>Se ejecuta automáticamente cada vez que la base de datos se abre. |
