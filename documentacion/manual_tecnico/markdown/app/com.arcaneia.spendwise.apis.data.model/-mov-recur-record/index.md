//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[MovRecurRecord](index.md)

# MovRecurRecord

[androidJvm]\
data class [MovRecurRecord](index.md)(val id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val nome: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val importe: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-double/index.html), val periodicidade: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?, val data_ini: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val data_rnv: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val tipo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?, val user: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))

Representa un **registro remoto de movimiento recurrente** tal como es devuelto por PocketBase.

Esta clase modela exactamente la estructura de un ítem dentro de la colección remota `mov_recur` en el servidor, y se utiliza para mapear las respuestas JSON recibidas por Retrofit.

Cada instancia refleja el estado actual del movimiento recurrente almacenado en el backend, incluyendo su periodicidad, fechas relacionadas y tipo de movimiento.

### Relación con la capa local

Este objeto es utilizado por el sincronizador (`MovRecurSyncRepository`) para:

- 
   Crear nuevos registros locales si no existen.
- 
   Actualizar datos locales cuando el servidor tiene cambios.
- 
   Relacionar los movimientos recurrentes remotos con sus equivalentes locales mediante `remote_id`.

### Campos recibidos desde PocketBase

PocketBase almacena los enums (`Recurrence`, `TypeMov`) como **strings**, por lo que aquí se reciben en formato textual y posteriormente se convierten en enums Kotlin en la capa de sincronización.

## Constructors

| | |
|---|---|
| [MovRecurRecord](-mov-recur-record.md) | [androidJvm]<br>constructor(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), nome: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), importe: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-double/index.html), periodicidade: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?, data_ini: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), data_rnv: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), tipo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?, user: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [data_ini](data_ini.md) | [androidJvm]<br>val [data_ini](data_ini.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Fecha de inicio en formato `YYYY-MM-DD`. |
| [data_rnv](data_rnv.md) | [androidJvm]<br>val [data_rnv](data_rnv.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Fecha programada para la próxima renovación (`YYYY-MM-DD`). |
| [id](id.md) | [androidJvm]<br>val [id](id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Identificador único del registro en PocketBase. |
| [importe](importe.md) | [androidJvm]<br>val [importe](importe.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-double/index.html)<br>Importe económico que se genera en cada renovación. |
| [nome](nome.md) | [androidJvm]<br>val [nome](nome.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Nombre descriptivo del movimiento recurrente. |
| [periodicidade](periodicidade.md) | [androidJvm]<br>val [periodicidade](periodicidade.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?<br>Tipo de recurrencia en formato String tal como se guarda en PocketBase. |
| [tipo](tipo.md) | [androidJvm]<br>val [tipo](tipo.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?<br>Tipo de movimiento en formato String (`"INGRESO"` o `"GASTO"`). |
| [user](user.md) | [androidJvm]<br>val [user](user.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Identificador remoto del usuario propietario de este registro. |
