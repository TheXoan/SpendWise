//[app](../../../index.md)/[com.arcaneia.spendwise.data.entity](../index.md)/[Categoria](index.md)

# Categoria

[androidJvm]\
data class [Categoria](index.md)(val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) = 0, val nome: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val tipo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val remote_id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null)

Entidad que representa una categoría dentro de la base de datos de Room.

Las categorías permiten clasificar los movimientos (ingresos o gastos) según su naturaleza, como por ejemplo: “Comida”, “Transporte”, “Salario”, etc.

La tabla asociada se llama `categoria`.

## Constructors

| | |
|---|---|
| [Categoria](-categoria.md) | [androidJvm]<br>constructor(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) = 0, nome: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), tipo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), remote_id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null) |

## Properties

| Name | Summary |
|---|---|
| [id](id.md) | [androidJvm]<br>val [id](id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) = 0<br>Identificador único de la categoría. Se genera automáticamente mediante `autoGenerate = true`. |
| [nome](nome.md) | [androidJvm]<br>val [nome](nome.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Nombre descriptivo de la categoría (ej.: &quot;Comida&quot;, &quot;Recurrente&quot;). |
| [remote_id](remote_id.md) | [androidJvm]<br>val [remote_id](remote_id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null<br>Identificador remoto de la categoría en PocketBase. Si es null, significa que la categoría aún no ha sido sincronizada con el servidor. |
| [tipo](tipo.md) | [androidJvm]<br>val [tipo](tipo.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Tipo de categoría, que puede ser utilizado para diferenciar categorías especiales o para marcar comportamientos específicos dentro de la app. |
