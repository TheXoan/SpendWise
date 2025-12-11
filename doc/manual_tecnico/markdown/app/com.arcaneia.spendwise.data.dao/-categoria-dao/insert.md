//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[CategoriaDao](index.md)/[insert](insert.md)

# insert

[androidJvm]\
abstract suspend fun [insert](insert.md)(categoria: [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-long/index.html)

Inserta una nueva categoría en la base de datos.

Si ocurre un conflicto (por ejemplo, IDs repetidos), el registro se reemplaza usando `OnConflictStrategy.REPLACE`.

#### Return

El ID autogenerado de la fila insertada.

#### Parameters

androidJvm

| | |
|---|---|
| categoria | Entidad a insertar. |
