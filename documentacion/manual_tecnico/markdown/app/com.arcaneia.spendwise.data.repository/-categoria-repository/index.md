//[app](../../../index.md)/[com.arcaneia.spendwise.data.repository](../index.md)/[CategoriaRepository](index.md)

# CategoriaRepository

[androidJvm]\
class [CategoriaRepository](index.md)(categoriaDao: [CategoriaDao](../../com.arcaneia.spendwise.data.dao/-categoria-dao/index.md))

Repositorio responsable de manejar todas las operaciones relacionadas con la entidad [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md).

Esta clase actúa como una capa intermedia entre el *Data Access Object* ([CategoriaDao](../../com.arcaneia.spendwise.data.dao/-categoria-dao/index.md)) y el ViewModel correspondiente, proporcionando un punto de acceso único y estructurado para:

- 
   Insertar nuevas categorías.
- 
   Actualizar y eliminar categorías existentes.
- 
   Consultar la lista completa de categorías almacenadas.
- 
   Buscar categorías por ID.

El uso de un repositorio logra:

- 
   Desacoplar las operaciones de acceso a datos de la lógica de UI.
- 
   Facilitar la escalabilidad y el testeo.
- 
   Centralizar la lógica de lectura y escritura en la base de datos Room.

**Nota:** Este repositorio trabaja exclusivamente con datos locales mediante Room. La sincronización remota con PocketBase es gestionada por clases específicas de sincronización.

## Constructors

| | |
|---|---|
| [CategoriaRepository](-categoria-repository.md) | [androidJvm]<br>constructor(categoriaDao: [CategoriaDao](../../com.arcaneia.spendwise.data.dao/-categoria-dao/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [androidJvm]<br>suspend fun [delete](delete.md)(categoria: [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md))<br>Elimina una categoría especificada de la base de datos. |
| [deleteById](delete-by-id.md) | [androidJvm]<br>suspend fun [deleteById](delete-by-id.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html))<br>Elimina una categoría usando su ID local. |
| [getAllCategories](get-all-categories.md) | [androidJvm]<br>fun [getAllCategories](get-all-categories.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)&gt;&gt;<br>Obtiene un flujo reactivo con la lista completa de categorías almacenadas. |
| [getById](get-by-id.md) | [androidJvm]<br>suspend fun [getById](get-by-id.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)): [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)?<br>Recupera una categoría a partir de su ID. |
| [insert](insert.md) | [androidJvm]<br>suspend fun [insert](insert.md)(categoria: [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-long/index.html)<br>Inserta una nueva categoría en la base de datos local. |
| [update](update.md) | [androidJvm]<br>suspend fun [update](update.md)(categoria: [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md))<br>Actualiza una categoría previamente registrada en la base de datos. |
