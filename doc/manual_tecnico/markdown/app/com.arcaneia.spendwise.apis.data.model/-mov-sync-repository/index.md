//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[MovSyncRepository](index.md)

# MovSyncRepository

[androidJvm]\
class [MovSyncRepository](index.md)(local: [MovDao](../../com.arcaneia.spendwise.data.dao/-mov-dao/index.md), remote: [MovRemoteDataSource](../-mov-remote-data-source/index.md), categoriaDao: [CategoriaDao](../../com.arcaneia.spendwise.data.dao/-categoria-dao/index.md), movRecurDao: [MovRecurDao](../../com.arcaneia.spendwise.data.dao/-mov-recur-dao/index.md), context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))

Repositorio encargado de sincronizar la colección de **movimientos simples (mov)** entre la base de datos local (Room) y la base de datos remota en PocketBase.

Esta clase constituye el núcleo del sistema de sincronización de movimientos, manteniendo la integridad y consistencia entre múltiples dispositivos y evitando duplicados mediante el campo `renew_hash`, utilizado especialmente para movimientos generados desde renovaciones recurrentes.

## 🔄 Funciones principales del sincronizador

La función [sync](sync.md) implementa el flujo completo de sincronización:

### **1. Subida de movimientos locales pendientes**

Todos los movimientos cuyo `remote_id` es `null` se consideran pendientes de subir. Estos incluyen:

- 
   Movimientos creados manualmente por el usuario.
- 
   Movimientos generados automáticamente por renovaciones recurrentes.

Para cada uno:

- 
   Se mapean sus relaciones (categoría y movimiento recurrente) a IDs remotos.
- 
   Se envía el movimiento a PocketBase vía [MovRemoteDataSource.create](../-mov-remote-data-source/create.md).
- 
   Se adjunta el `remote_id` recibido del servidor mediante `attachRemoteId`.

### **2. Descarga de movimientos remotos**

Se recuperan todos los registros remotos pertenecientes al usuario autenticado.

### **3. Merge remoto → local**

Por cada movimiento remoto se realiza:

- 
   Registro del ID remoto en la lista `remoteIds`.
- 
   Si el movimiento tiene `renew_hash`:
- 
   Se detecta si ya existe el movimiento local correspondiente.
- 
   Se evita crear duplicados.
- 
   Si existe localmente → se actualiza.
- 
   Si no existe → se inserta un nuevo registro local.

Todas las relaciones remotas (`categoria_id`, `mov_recur_id`) se convierten a sus IDs locales mediante `CategoriaDao` y `MovRecurDao`.

### **4. Eliminación de movimientos locales borrados en PocketBase**

Cualquier movimiento local que posea un `remote_id` que ya no existe en PocketBase será eliminado automáticamente del dispositivo.

Esto garantiza la coherencia entre dispositivos y respeta eliminaciones remotas.

## 🧩 Consideraciones importantes

- 
   Esta clase no interactúa directamente con la capa de UI.
- 
   No procesa renovaciones recurrentes (esa responsabilidad pertenece a `MovRecurRepository`).
- 
   No muestra notificaciones (esto lo realiza `RenewMovsRecurWorker`).
- 
   Supone que todos los DAOs están correctamente configurados para soportar sincronización.

## Constructors

| | |
|---|---|
| [MovSyncRepository](-mov-sync-repository.md) | [androidJvm]<br>constructor(local: [MovDao](../../com.arcaneia.spendwise.data.dao/-mov-dao/index.md), remote: [MovRemoteDataSource](../-mov-remote-data-source/index.md), categoriaDao: [CategoriaDao](../../com.arcaneia.spendwise.data.dao/-categoria-dao/index.md), movRecurDao: [MovRecurDao](../../com.arcaneia.spendwise.data.dao/-mov-recur-dao/index.md), context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Functions

| Name | Summary |
|---|---|
| [sync](sync.md) | [androidJvm]<br>suspend fun [sync](sync.md)()<br>Ejecuta la sincronización completa de movimientos simples. |
