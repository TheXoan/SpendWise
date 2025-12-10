//[app](../../../index.md)/[com.arcaneia.spendwise.data.entity](../index.md)/[Mov](index.md)

# Mov

[androidJvm]\
data class [Mov](index.md)(val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) = 0, val tipo: [TypeMov](../../com.arcaneia.spendwise.data.model/-type-mov/index.md)?, val importe: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-double/index.html), val data_mov: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val descricion: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null, val categoria_id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html), val mov_recur_id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)? = null, val remote_id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null, val renew_hash: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null, val notificado: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) = false)

Entidad que representa un movimiento económico individual dentro de la aplicación.

Un **movimiento** puede ser un ingreso o un gasto, estar asociado a una categoría y opcionalmente provenir de un movimiento recurrente.

Esta entidad integra tanto datos locales como referencias para sincronización remota con PocketBase.

### 🔗 Relaciones con otras entidades

Se definen dos claves foráneas:

1. 
   `categoria_id` → referencia a [Categoria](../-categoria/index.md)
2. - 
      `CASCADE`: si se elimina una categoría, también se eliminan sus movimientos.
3. 
   `mov_recur_id` → referencia a [MovRecur](../-mov-recur/index.md)
4. - 
      `SET_NULL`: si se elimina el movimiento recurrente, el movimiento simple permanece,       pero deja de estar vinculado a esa recurrencia.

### ⚡ Índices

La entidad define índices en:

- 
   `categoria_id`
- 
   `mov_recur_id`

Esto optimiza:

- 
   consultas con JOIN,
- 
   filtros por categoría o recurrencia,
- 
   validación de claves foráneas.

### 🌐 Sincronización remota (PocketBase)

Los campos:

- 
   `remote_id`
- 
   `renew_hash`
- 
   `notificado`

permiten:

- 
   identificar el registro remoto asociado,
- 
   evitar duplicados generados por renovaciones,
- 
   controlar qué movimientos deben generar notificaciones locales.

## Constructors

| | |
|---|---|
| [Mov](-mov.md) | [androidJvm]<br>constructor(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) = 0, tipo: [TypeMov](../../com.arcaneia.spendwise.data.model/-type-mov/index.md)?, importe: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-double/index.html), data_mov: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), descricion: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null, categoria_id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html), mov_recur_id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)? = null, remote_id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null, renew_hash: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null, notificado: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) = false) |

## Properties

| Name | Summary |
|---|---|
| [categoria_id](categoria_id.md) | [androidJvm]<br>val [categoria_id](categoria_id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)<br>ID local de la categoría asociada (clave foránea a [Categoria](../-categoria/index.md)). |
| [data_mov](data_mov.md) | [androidJvm]<br>val [data_mov](data_mov.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Fecha del movimiento en formato `"YYYY-MM-DD"` (o `"YYYY-MM-DD HH:mm:ss"` si se usa con hora). |
| [descricion](descricion.md) | [androidJvm]<br>val [descricion](descricion.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null<br>Descripción opcional del movimiento. |
| [id](id.md) | [androidJvm]<br>val [id](id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) = 0<br>ID autogenerado del movimiento en la base de datos local. |
| [importe](importe.md) | [androidJvm]<br>val [importe](importe.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-double/index.html)<br>Cantidad económica del movimiento. |
| [mov_recur_id](mov_recur_id.md) | [androidJvm]<br>val [mov_recur_id](mov_recur_id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)? = null<br>ID local del movimiento recurrente que originó este movimiento, o `null` si no es recurrente. |
| [notificado](notificado.md) | [androidJvm]<br>val [notificado](notificado.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) = false<br>Indica si este movimiento ya fue notificado localmente. Utilizado para evitar notificaciones repetidas. |
| [remote_id](remote_id.md) | [androidJvm]<br>val [remote_id](remote_id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null<br>ID remoto en PocketBase. Si es `null`, aún no ha sido sincronizado. |
| [renew_hash](renew_hash.md) | [androidJvm]<br>val [renew_hash](renew_hash.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)? = null<br>Hash único usado para evitar duplicados entre dispositivos cuando se generan movimientos recurrentes automáticamente. |
| [tipo](tipo.md) | [androidJvm]<br>val [tipo](tipo.md): [TypeMov](../../com.arcaneia.spendwise.data.model/-type-mov/index.md)?<br>Tipo de movimiento ([TypeMov](../../com.arcaneia.spendwise.data.model/-type-mov/index.md)): INGRESO o GASTO. Puede ser `null` en casos especiales. |
