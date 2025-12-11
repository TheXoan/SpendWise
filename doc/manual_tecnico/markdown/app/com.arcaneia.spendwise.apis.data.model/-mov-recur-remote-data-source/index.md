//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[MovRecurRemoteDataSource](index.md)

# MovRecurRemoteDataSource

[androidJvm]\
class [MovRecurRemoteDataSource](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))

Fuente de datos remota encargada de gestionar todas las operaciones relacionadas con los **movimientos recurrentes** (`mov_recur`) en el servidor PocketBase.

Esta clase actúa como intermediaria entre la capa de sincronización/repositorio y las llamadas HTTP realizadas mediante Retrofit, proporcionando funciones para:

- 
   Obtener todos los movimientos recurrentes remotos del usuario.
- 
   Crear nuevos movimientos recurrentes en el servidor.
- 
   Actualizar registros existentes.
- 
   Eliminar movimientos recurrentes remotos.

Internamente maneja la autenticación utilizando el token almacenado en `TokenDataStore`, construyendo el encabezado `Bearer <token>` requerido por PocketBase para todas las peticiones protegidas.

### Uso

Esta clase es utilizada típicamente por:

- 
   `MovRecurSyncRepository`: para sincronización remota ↔ local.
- 
   ViewModels que requieran crear, editar o borrar movimientos recurrentes directamente.

## Constructors

| | |
|---|---|
| [MovRecurRemoteDataSource](-mov-recur-remote-data-source.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Functions

| Name | Summary |
|---|---|
| [create](create.md) | [androidJvm]<br>suspend fun [create](create.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), movRecur: [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)): [MovRecurRecord](../-mov-recur-record/index.md)<br>Crea un nuevo movimiento recurrente en el servidor PocketBase. |
| [delete](delete.md) | [androidJvm]<br>suspend fun [delete](delete.md)(movRecurPBId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))<br>Elimina un movimiento recurrente remoto identificado por su ID. |
| [fetchAll](fetch-all.md) | [androidJvm]<br>suspend fun [fetchAll](fetch-all.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovRecurRecord](../-mov-recur-record/index.md)&gt;<br>Descarga todos los movimientos recurrentes pertenecientes al usuario dado. |
| [update](update.md) | [androidJvm]<br>suspend fun [update](update.md)(movRecurPBId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), movRecur: [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)): [MovRecurRecord](../-mov-recur-record/index.md)<br>Actualiza un movimiento recurrente existente en PocketBase. |
