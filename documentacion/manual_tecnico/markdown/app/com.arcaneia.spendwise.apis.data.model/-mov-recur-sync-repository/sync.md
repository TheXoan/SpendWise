//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[MovRecurSyncRepository](index.md)/[sync](sync.md)

# sync

[androidJvm]\
suspend fun [sync](sync.md)()

Ejecuta el proceso **completo de sincronización** entre local ↔ remoto.

Este método orquesta todas las fases del proceso:

1. 
   `uploadPendingMovRecur()` — Subida de registros locales nuevos.
2. 
   `downloadAndMergeMovRecur()` — Descarga y fusión de registros remotos.
3. 
   `deleteStaleMovRecur()` — Eliminación local de elementos removidos en el servidor.

Si el usuario no tiene un `userId` válido, la sincronización se cancela silenciosamente.
