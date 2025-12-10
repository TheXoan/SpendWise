//[app](../../../index.md)/[[root]](../index.md)/[MovRecord](index.md)

# MovRecord

[androidJvm]\
data class [MovRecord](index.md)(val id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val tipo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?, val importe: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-double/index.html), val data_mov: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val descricion: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null, val categoria_id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val mov_recur_id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null, val user: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val renew_hash: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?)

Representa un registro remoto de la colección `mov` en PocketBase.

Esta clase actúa como un **DTO (Data Transfer Object)** utilizado para mapear las respuestas JSON provenientes del servidor. Cada instancia corresponde a un movimiento simple almacenado en PocketBase, incluyendo información básica, referencias a entidades relacionadas y metadatos útiles para sincronización.

## 🔗 Relaciones y sincronización

PocketBase almacena relaciones mediante **IDs remotos (String)**. Por ello, los campos `categoria_id` y `mov_recur_id` deben ser traducidos posteriormente por el `MovSyncRepository` a los IDs internos de Room.

Además, este DTO incluye el campo `renew_hash`, un identificador único generado por las renovaciones automáticas, que permite:

- 
   Detectar duplicados en sincronizaciones entre dispositivos.
- 
   Evitar que un mismo movimiento recurrente genere múltiples copias.

## Propiedades

## Constructors

| | |
|---|---|
| [MovRecord](-mov-record.md) | [androidJvm]<br>constructor(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), tipo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?, importe: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-double/index.html), data_mov: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), descricion: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null, categoria_id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), mov_recur_id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null, user: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), renew_hash: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?) |

## Properties

| Name | Summary |
|---|---|
| [categoria_id](categoria_id.md) | [androidJvm]<br>val [categoria_id](categoria_id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>ID remoto de la categoría asociada. Debe mapearse al ID local en Room. |
| [data_mov](data_mov.md) | [androidJvm]<br>val [data_mov](data_mov.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Fecha del movimiento en formato `"YYYY-MM-DD HH:mm:ss"` o `"YYYY-MM-DD"` según el origen del dato. |
| [descricion](descricion.md) | [androidJvm]<br>val [descricion](descricion.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null<br>Texto descriptivo del movimiento. Puede ser nulo. |
| [id](id.md) | [androidJvm]<br>val [id](id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>ID único generado por PocketBase para este movimiento. |
| [importe](importe.md) | [androidJvm]<br>val [importe](importe.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-double/index.html)<br>Cantidad económica asociada al movimiento. |
| [mov_recur_id](mov_recur_id.md) | [androidJvm]<br>val [mov_recur_id](mov_recur_id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null<br>ID remoto de la entrada `mov_recur` que generó este movimiento. Es nulo si el movimiento no proviene de una recurrencia. |
| [renew_hash](renew_hash.md) | [androidJvm]<br>val [renew_hash](renew_hash.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?<br>Identificador único que permite detectar movimientos creados automáticamente por renovaciones recurrentes y evitar duplicados en la sincronización. |
| [tipo](tipo.md) | [androidJvm]<br>val [tipo](tipo.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?<br>Tipo de movimiento (`INGRESO` o `GASTO`) como String. Puede ser nulo si en el servidor no se estableció el campo. |
| [user](user.md) | [androidJvm]<br>val [user](user.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>ID remoto del usuario propietario del registro. |
