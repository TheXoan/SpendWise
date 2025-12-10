//[app](../../../index.md)/[com.arcaneia.spendwise.data.database](../index.md)/[DatabaseCallBack](index.md)/[onOpen](on-open.md)

# onOpen

[androidJvm]\
open override fun [onOpen](on-open.md)(db: [SupportSQLiteDatabase](https://developer.android.com/reference/kotlin/androidx/sqlite/db/SupportSQLiteDatabase.html))

Se ejecuta automáticamente cada vez que la base de datos se abre.

En este caso, inserta una categoría por defecto si no existe, utilizando la sentencia SQL:

```kotlin
INSERT OR IGNORE INTO categoria (id, nome, tipo)
VALUES (1, 'Recurrente', '')
```

Esto garantiza que la categoría &quot;Recurrente&quot; esté disponible para operaciones que dependen de ella, sin sobrescribirla si ya existe.

#### Parameters

androidJvm

| | |
|---|---|
| db | Instancia de [SupportSQLiteDatabase](https://developer.android.com/reference/kotlin/androidx/sqlite/db/SupportSQLiteDatabase.html) que permite ejecutar sentencias SQL. |
