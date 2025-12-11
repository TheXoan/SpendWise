//[app](../../../../index.md)/[com.arcaneia.spendwise.data.database](../../index.md)/[AppDatabase](../index.md)/[Companion](index.md)/[getDatabase](get-database.md)

# getDatabase

[androidJvm]\
fun [getDatabase](get-database.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [AppDatabase](../index.md)

Obtiene la instancia única de la base de datos.

Si la instancia aún no existe, se crea utilizando `Room.databaseBuilder()`. Se aplica:

- 
   `fallbackToDestructiveMigration(true)` para reconstruir la BD en caso de cambios de versión sin migraciones definidas.
- 
   Un `DatabaseCallBack` para operaciones adicionales durante su creación.

Esta función es segura para hilos múltiples gracias al uso de `synchronized` y la anotación `@Volatile` en INSTANCE.

#### Return

La instancia única de [AppDatabase](../index.md).

#### Parameters

androidJvm

| | |
|---|---|
| context | Contexto necesario para crear la base de datos. |
