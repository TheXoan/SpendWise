//[app](../../../index.md)/[com.arcaneia.spendwise.data.model](../index.md)/[MovRecurViewModel](index.md)/[update](update.md)

# update

[androidJvm]\
fun [update](update.md)(m: [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)): Job

Actualiza un movimiento recurrente tanto en la base de datos local como en PocketBase.

- 
   Primero se obtiene el registro actual para preservar el `remote_id`.
- 
   Se actualiza en Room.
- 
   Si ya estaba sincronizado, se aplica la actualización en el servidor.

#### Parameters

androidJvm

| | |
|---|---|
| m | Movimiento recurrente con los valores actualizados. |
