//[app](../../../index.md)/[com.arcaneia.spendwise.data.entity](../index.md)/[MovRecur](index.md)

# MovRecur

[androidJvm]\
data class [MovRecur](index.md)(val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) = 0, val nome: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val importe: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-double/index.html), val periodicidade: [Recurrence](../../com.arcaneia.spendwise.data.model/-recurrence/index.md)?, val data_ini: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val data_rnv: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val tipo: [TypeMov](../../com.arcaneia.spendwise.data.model/-type-mov/index.md)?, val remote_id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null)

Entidad que representa un **movimiento recurrente** dentro de la aplicación (Room).

Los movimientos recurrentes permiten programar transacciones automáticas en intervalos definidos (mensual, anual, semanal, etc.). Cada vez que un movimiento recurrente llega a su fecha de renovación (`data_rnv`), la aplicación puede generar automáticamente un movimiento normal asociado.

La tabla asociada se llama `mov_recur`.

## Constructors

| | |
|---|---|
| [MovRecur](-mov-recur.md) | [androidJvm]<br>constructor(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) = 0, nome: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), importe: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-double/index.html), periodicidade: [Recurrence](../../com.arcaneia.spendwise.data.model/-recurrence/index.md)?, data_ini: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), data_rnv: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), tipo: [TypeMov](../../com.arcaneia.spendwise.data.model/-type-mov/index.md)?, remote_id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null) |

## Properties

| Name | Summary |
|---|---|
| [data_ini](data_ini.md) | [androidJvm]<br>val [data_ini](data_ini.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Fecha de inicio de la recurrencia, en formato `"YYYY-MM-DD"`. Esta fecha marca el punto de partida para el cálculo de futuras renovaciones. |
| [data_rnv](data_rnv.md) | [androidJvm]<br>val [data_rnv](data_rnv.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Fecha de la próxima renovación del movimiento, en formato `"YYYY-MM-DD"`. Esta fecha es actualizada por el sistema tras cada generación de movimiento. |
| [id](id.md) | [androidJvm]<br>val [id](id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) = 0<br>Identificador único del movimiento recurrente. Se genera automáticamente mediante `autoGenerate = true`. |
| [importe](importe.md) | [androidJvm]<br>val [importe](importe.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-double/index.html)<br>Monto económico que se renovará periódicamente. |
| [nome](nome.md) | [androidJvm]<br>val [nome](nome.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Nombre descriptivo del movimiento recurrente (ej.: “Suscripción Netflix”, “Salario mensual”). |
| [periodicidade](periodicidade.md) | [androidJvm]<br>val [periodicidade](periodicidade.md): [Recurrence](../../com.arcaneia.spendwise.data.model/-recurrence/index.md)?<br>Tipo de recurrencia, representado por el enum [Recurrence](../../com.arcaneia.spendwise.data.model/-recurrence/index.md) (&quot;MENSUAL&quot;, &quot;ANUAL&quot;, &quot;SEMANAL&quot;, etc.). Puede ser `null` en algunos casos especiales. |
| [remote_id](remote_id.md) | [androidJvm]<br>val [remote_id](remote_id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null<br>Identificador remoto (PocketBase ID) del registro. Es `null` si el movimiento recurrente aún no ha sido sincronizado con el servidor. |
| [tipo](tipo.md) | [androidJvm]<br>val [tipo](tipo.md): [TypeMov](../../com.arcaneia.spendwise.data.model/-type-mov/index.md)?<br>Tipo de movimiento recurrente, representado por el enum [TypeMov](../../com.arcaneia.spendwise.data.model/-type-mov/index.md) (por ejemplo: *INGRESO* o *GASTO*). Puede ser `null` en casos excepcionales. |
