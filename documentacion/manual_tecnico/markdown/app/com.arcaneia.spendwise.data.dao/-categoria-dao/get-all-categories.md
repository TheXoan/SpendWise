//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[CategoriaDao](index.md)/[getAllCategories](get-all-categories.md)

# getAllCategories

[androidJvm]\
abstract fun [getAllCategories](get-all-categories.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)&gt;&gt;

Recupera todas las categorías excepto la de ID 1, que corresponde a la categoría reservada **&quot;Recurrente&quot;**.

#### Return

Un flujo reactivo con la lista de categorías.
