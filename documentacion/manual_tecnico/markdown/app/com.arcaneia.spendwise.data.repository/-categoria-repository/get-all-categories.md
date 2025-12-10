//[app](../../../index.md)/[com.arcaneia.spendwise.data.repository](../index.md)/[CategoriaRepository](index.md)/[getAllCategories](get-all-categories.md)

# getAllCategories

[androidJvm]\
fun [getAllCategories](get-all-categories.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)&gt;&gt;

Obtiene un flujo reactivo con la lista completa de categorías almacenadas.

Se excluye la categoría con ID 1 si así lo define el DAO, y los cambios se emiten automáticamente a cualquier suscriptor cada vez que la tabla `categoria` es modificada.

#### Return

Flow que emite una lista de [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md).
