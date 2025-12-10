//[app](../../../index.md)/[com.arcaneia.spendwise.data.model](../index.md)/[TypeMov](index.md)

# TypeMov

[androidJvm]\
enum [TypeMov](index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-enum/index.html)&lt;[TypeMov](index.md)&gt; 

Enum que representa los tipos de movimiento económico disponibles dentro de la aplicación: ingresos y gastos.

Cada valor incluye una descripción legible que puede usarse directamente en la interfaz de usuario.

## Entries

| | |
|---|---|
| [INGRESO](-i-n-g-r-e-s-o/index.md) | [androidJvm]<br>[INGRESO](-i-n-g-r-e-s-o/index.md)<br>Representa un movimiento de tipo ingreso. |
| [GASTO](-g-a-s-t-o/index.md) | [androidJvm]<br>[GASTO](-g-a-s-t-o/index.md)<br>Representa un movimiento de tipo gasto. |

## Properties

| Name | Summary |
|---|---|
| [description](description.md) | [androidJvm]<br>val [description](description.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Texto descriptivo del tipo de movimiento. |
| [entries](entries.md) | [androidJvm]<br>val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.enums/-enum-entries/index.html)&lt;[TypeMov](index.md)&gt;<br>Returns a representation of an immutable list of all enum entries, in the order they're declared. |
| [name](-g-a-s-t-o/index.md#-372974862%2FProperties%2F-912451524) | [androidJvm]<br>val [name](-g-a-s-t-o/index.md#-372974862%2FProperties%2F-912451524): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html) |
| [ordinal](-g-a-s-t-o/index.md#-739389684%2FProperties%2F-912451524) | [androidJvm]<br>val [ordinal](-g-a-s-t-o/index.md#-739389684%2FProperties%2F-912451524): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) |

## Functions

| Name | Summary |
|---|---|
| [valueOf](value-of.md) | [androidJvm]<br>fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [TypeMov](index.md)<br>Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.) |
| [values](values.md) | [androidJvm]<br>fun [values](values.md)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-array/index.html)&lt;[TypeMov](index.md)&gt;<br>Returns an array containing the constants of this enum type, in the order they're declared. |
