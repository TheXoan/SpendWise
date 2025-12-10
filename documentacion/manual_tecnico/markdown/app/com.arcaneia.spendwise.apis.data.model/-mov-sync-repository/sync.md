//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[MovSyncRepository](index.md)/[sync](sync.md)

# sync

[androidJvm]\
suspend fun [sync](sync.md)()

Ejecuta la sincronización completa de movimientos simples.

El proceso se realiza secuencialmente:

1. 
   **Subida local → remoto**     Se envían todos los movimientos pendientes (`remote_id == null`).
2. 
   **Descarga remoto → dispositivo**     Se obtienen todos los movimientos del usuario desde PocketBase.
3. 
   **Fusión remoto/local (merge)**
4. - 
      Se actualizan registros existentes.
   - 
      Se insertan los nuevos.
   - 
      Se detectan duplicados mediante `renew_hash`.
5. 
   **Eliminación local**     Cualquier movimiento local cuyo `remote_id` ya no exista en PocketBase     se elimina del dispositivo.

Esta operación garantiza consistencia total entre dispositivos sin duplicar movimientos generados automáticamente por renovaciones recurrentes.
