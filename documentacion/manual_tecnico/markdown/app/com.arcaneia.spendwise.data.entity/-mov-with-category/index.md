//[app](../../../index.md)/[com.arcaneia.spendwise.data.entity](../index.md)/[MovWithCategory](index.md)

# MovWithCategory

[androidJvm]\
data class [MovWithCategory](index.md)(val mov: [Mov](../-mov/index.md), val categoriaNome: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))

Clase auxiliar utilizada para representar un movimiento ([Mov](../-mov/index.md)) junto con el nombre de su categoría correspondiente.

Esta clase es el resultado de una consulta `JOIN` entre las tablas `mov` y `categoria`, permitiendo acceder a la información combinada sin necesidad de consultar múltiples entidades por separado.

Se usa frecuentemente en listas, reportes o vistas donde se necesita mostrar el movimiento junto al nombre de su categoría.

## Constructors

| | |
|---|---|
| [MovWithCategory](-mov-with-category.md) | [androidJvm]<br>constructor(mov: [Mov](../-mov/index.md), categoriaNome: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [categoriaNome](categoria-nome.md) | [androidJvm]<br>val [categoriaNome](categoria-nome.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Nombre de la categoría asociada al movimiento. |
| [mov](mov.md) | [androidJvm]<br>val [mov](mov.md): [Mov](../-mov/index.md)<br>Instancia completa del movimiento recuperado. |
