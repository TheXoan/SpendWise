//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[MovRemoteDataSource](index.md)

# MovRemoteDataSource

[androidJvm]\
class [MovRemoteDataSource](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))

Fuente de datos remota encargada de gestionar la comunicación entre la aplicación y la colección `mov` de PocketBase.

Esta clase encapsula todas las operaciones CRUD remotas relacionadas con movimientos simples, proporcionando un acceso seguro, tipado y centralizado a las llamadas HTTP. Utiliza Retrofit para interactuar con la API y DataStore para obtener el token de autenticación almacenado en el dispositivo.

## 🔐 Autenticación

Cada operación realiza una llamada al método privado authHeader, el cual:

- 
   Recupera el token actual desde [TokenDataStore](../../com.arcaneia.spendwise.data.datastore/-token-data-store/index.md).
- 
   Lanza una excepción si el token no existe (evitando llamadas inválidas al servidor).
- 
   Devuelve el header `Bearer <token>` requerido por PocketBase.

## 🔄 Sincronización y compatibilidad

Esta fuente de datos es totalmente compatible con:

- 
   **IDs remotos** (`categoriaPBId`, `movRecurPBId`), necesarios para el mapeo relacional.
- 
   **renew_hash**, usado para evitar duplicados cuando un movimiento se genera desde una renovación recurrente.

Las operaciones remotas se limitan a enviar y recibir datos; el mapeo hacia Room y el merge final se realiza en `MovSyncRepository`.

## Métodos principales

### fetchAll(userId)

Obtiene todos los movimientos del usuario autenticado mediante un filtro en PocketBase.

### create(...)

Envía un nuevo movimiento al servidor. Admite valores opcionales como `descricion` o `mov_recur_id`.

### update(...)

Actualiza un movimiento existente en PocketBase utilizando su ID remoto.

### delete(movPBId)

Elimina un movimiento remoto de forma definitiva.

## Constructors

| | |
|---|---|
| [MovRemoteDataSource](-mov-remote-data-source.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Functions

| Name | Summary |
|---|---|
| [create](create.md) | [androidJvm]<br>suspend fun [create](create.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), mov: [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md), categoriaPBId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), movRecurPBId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?): [MovRecord](../../[root]/-mov-record/index.md)<br>Crea un nuevo movimiento remoto en PocketBase. |
| [delete](delete.md) | [androidJvm]<br>suspend fun [delete](delete.md)(movPBId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))<br>Elimina un movimiento remoto en PocketBase. |
| [fetchAll](fetch-all.md) | [androidJvm]<br>suspend fun [fetchAll](fetch-all.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovRecord](../../[root]/-mov-record/index.md)&gt;<br>Obtiene todos los movimientos remotos pertenecientes al usuario especificado. |
| [update](update.md) | [androidJvm]<br>suspend fun [update](update.md)(movPBId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), mov: [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md), categoriaPBId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), movRecurPBId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?): [MovRecord](../../[root]/-mov-record/index.md)<br>Actualiza un movimiento ya existente en PocketBase. |
