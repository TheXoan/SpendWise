//[app](../../../index.md)/[com.arcaneia.spendwise.data.model](../index.md)/[Recurrence](index.md)

# Recurrence

[androidJvm]\
enum [Recurrence](index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-enum/index.html)&lt;[Recurrence](index.md)&gt; 

Enum que representa los distintos tipos de **recurrencia** disponibles para los movimientos recurrentes dentro de la aplicación.

Cada valor incluye una descripción legible que puede utilizarse directamente en la interfaz de usuario.

## Entries

| | |
|---|---|
| [SEMANAL](-s-e-m-a-n-a-l/index.md) | [androidJvm]<br>[SEMANAL](-s-e-m-a-n-a-l/index.md)<br>Movimiento recurrente que se repite cada semana. |
| [MENSUAL](-m-e-n-s-u-a-l/index.md) | [androidJvm]<br>[MENSUAL](-m-e-n-s-u-a-l/index.md)<br>Movimiento recurrente que se repite una vez al mes. |
| [ANUAL](-a-n-u-a-l/index.md) | [androidJvm]<br>[ANUAL](-a-n-u-a-l/index.md)<br>Movimiento recurrente que se repite una vez al año. |

## Properties

| Name | Summary |
|---|---|
| [description](description.md) | [androidJvm]<br>val [description](description.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Texto descriptivo del tipo de recurrencia. |
| [entries](entries.md) | [androidJvm]<br>val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.enums/-enum-entries/index.html)&lt;[Recurrence](index.md)&gt;<br>Returns a representation of an immutable list of all enum entries, in the order they're declared. |
| [name](../-type-mov/-g-a-s-t-o/index.md#-372974862%2FProperties%2F-912451524) | [androidJvm]<br>val [name](../-type-mov/-g-a-s-t-o/index.md#-372974862%2FProperties%2F-912451524): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html) |
| [ordinal](../-type-mov/-g-a-s-t-o/index.md#-739389684%2FProperties%2F-912451524) | [androidJvm]<br>val [ordinal](../-type-mov/-g-a-s-t-o/index.md#-739389684%2FProperties%2F-912451524): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) |

## Functions

| Name | Summary |
|---|---|
| [valueOf](value-of.md) | [androidJvm]<br>fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [Recurrence](index.md)<br>Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.) |
| [values](values.md) | [androidJvm]<br>fun [values](values.md)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-array/index.html)&lt;[Recurrence](index.md)&gt;<br>Returns an array containing the constants of this enum type, in the order they're declared. |
