//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[CategoriaSyncRepository](index.md)/[sync](sync.md)

# sync

[androidJvm]\
suspend fun [sync](sync.md)()

Ejecuta el proceso completo de sincronización entre la base de datos local y remota.

La sincronización se ejecuta en **cuatro fases**:

### 1) Descarga inicial desde PocketBase

- 
   Se obtienen todas las categorías remotas del usuario.
- 
   Se detecta la existencia de la categoría especial `"Recurrente"`.
- 
   Si existe en remoto, se vincula al registro local de ID fijo `1` (creado por el callback de Room).

### 2) Subida de categorías locales pendientes

- 
   Se obtienen todas las categorías locales con `remote_id = null`.
- 
   Se suben al servidor una por una.
- 
   **Excepción:** si la categoría se llama `"Recurrente"` y ya existe en remoto, no se sube.

### 3) Fusión de categorías remotas → locales

- 
   Si una categoría remota no existe localmente, se inserta.
- 
   Si existe pero cambió algún campo, se actualiza.
- 
   `"Recurrente"` se omite aquí para evitar duplicados.

### 4) Eliminación de categorías locales borradas en el servidor

- 
   Se borran localmente las categorías cuyo `remote_id` ya no existe en remoto.
- 
   **Excepción:**`"Recurrente"` nunca se elimina localmente.

Esta función garantiza la integridad de datos y evita duplicaciones incluso cuando se inicia sesión en múltiples dispositivos.
