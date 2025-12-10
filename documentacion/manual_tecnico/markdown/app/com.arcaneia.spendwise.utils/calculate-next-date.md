//[app](../../index.md)/[com.arcaneia.spendwise.utils](index.md)/[calculateNextDate](calculate-next-date.md)

# calculateNextDate

[androidJvm]\
fun [calculateNextDate](calculate-next-date.md)(dateIni: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), periodicity: [Recurrence](../com.arcaneia.spendwise.data.model/-recurrence/index.md)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)

Calcula la siguiente fecha de renovación para un movimiento recurrente.

#### Return

Fecha resultante en formato `yyyy-MM-dd`.

Esta función interpreta la fecha inicial como un `LocalDate`, aplica la cantidad de tiempo correspondiente según la periodicidad y devuelve la fecha calculada en formato base para la base de datos.

Ejemplos:

- 
   Si dateIni = &quot;2025-02-01&quot; y periodicity = MENSUAL → &quot;2025-03-01&quot;
- 
   Si dateIni = &quot;2025-02-01&quot; y periodicity = SEMANAL → &quot;2025-02-08&quot;

#### Parameters

androidJvm

| | |
|---|---|
| dateIni | Fecha inicial en formato `yyyy-MM-dd`. |
| periodicity | Tipo de recurrencia (semanal, mensual o anual). |
