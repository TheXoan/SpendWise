//[app](../../../index.md)/[com.arcaneia.spendwise.data.model](../index.md)/[MovViewModel](index.md)/[insert](insert.md)

# insert

[androidJvm]\
fun [insert](insert.md)(mov: [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)): Job

Inserta un movimiento simple en Room y luego intenta subirlo a PocketBase.

Flujo del insert:

1. 
   Inserta en la base de datos local.
2. 
   Obtiene el `userId`.
3. 
   Obtiene el `remote_id` de la categoría asociada.
4. 
   Obtiene el `remote_id` del movimiento recurrente si aplica.
5. 
   Crea el movimiento en PocketBase.
6. 
   Actualiza el movimiento local con su `remote_id`.

Si algún ID remoto requerido no existe, la subida remota se omite.

#### Parameters

androidJvm

| | |
|---|---|
| mov | Movimiento a insertar. |
