//[app](../../../index.md)/[com.arcaneia.spendwise.data.database](../index.md)/[Converters](index.md)

# Converters

[androidJvm]\
class [Converters](index.md)

Clase que contiene los convertidores de tipos utilizados por Room para almacenar correctamente valores de enums en la base de datos.

Room no puede guardar directamente tipos enumerados (`enum class`), por lo que estos convertidores permiten transformar los enums [Recurrence](../../com.arcaneia.spendwise.data.model/-recurrence/index.md) y [TypeMov](../../com.arcaneia.spendwise.data.model/-type-mov/index.md) a `String` para su almacenamiento, y viceversa para su recuperación.

Esta clase se registra en la base de datos mediante la anotación `@TypeConverters(Converters::class)` en [AppDatabase](../-app-database/index.md).

## Constructors

| | |
|---|---|
| [Converters](-converters.md) | [androidJvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [fromRecurrence](from-recurrence.md) | [androidJvm]<br>fun [fromRecurrence](from-recurrence.md)(value: [Recurrence](../../com.arcaneia.spendwise.data.model/-recurrence/index.md)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Convierte un valor del enum [Recurrence](../../com.arcaneia.spendwise.data.model/-recurrence/index.md) en un `String` que puede ser almacenado en la BD. |
| [fromTipoMov](from-tipo-mov.md) | [androidJvm]<br>fun [fromTipoMov](from-tipo-mov.md)(value: [TypeMov](../../com.arcaneia.spendwise.data.model/-type-mov/index.md)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Convierte un valor del enum [TypeMov](../../com.arcaneia.spendwise.data.model/-type-mov/index.md) a una cadena para ser almacenada en la base de datos. |
| [toRecurrence](to-recurrence.md) | [androidJvm]<br>fun [toRecurrence](to-recurrence.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [Recurrence](../../com.arcaneia.spendwise.data.model/-recurrence/index.md)<br>Convierte una cadena almacenada en la base de datos en un valor del enum [Recurrence](../../com.arcaneia.spendwise.data.model/-recurrence/index.md). |
| [toTipoMov](to-tipo-mov.md) | [androidJvm]<br>fun [toTipoMov](to-tipo-mov.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [TypeMov](../../com.arcaneia.spendwise.data.model/-type-mov/index.md)<br>Convierte una cadena almacenada en la base de datos en un valor del enum [TypeMov](../../com.arcaneia.spendwise.data.model/-type-mov/index.md). |
