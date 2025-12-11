//[app](../../../index.md)/[com.arcaneia.spendwise.data.model](../index.md)/[CategoriaViewModel](index.md)/[deleteById](delete-by-id.md)

# deleteById

[androidJvm]\
fun [deleteById](delete-by-id.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)): Job

Elimina una categoría localmente y, si corresponde, también remotamente.

Flujo:

- 
   Si la categoría tiene un `remote_id`, se elimina primero de PocketBase.
- 
   Luego se elimina en Room.

#### Parameters

androidJvm

| | |
|---|---|
| id | Identificador local de la categoría que se desea eliminar. |
