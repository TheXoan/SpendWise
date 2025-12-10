//[app](../../../index.md)/[com.arcaneia.spendwise.data.model](../index.md)/[MovViewModel](index.md)/[update](update.md)

# update

[androidJvm]\
fun [update](update.md)(newMov: [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)): Job

Actualiza un movimiento simple tanto en local como en remoto.

Flujo:

1. 
   Recupera el registro original para preservar el `remote_id`.
2. 
   Sobrescribe solo los campos modificables.
3. 
   Actualiza el registro en Room.
4. 
   Si tiene `remote_id`, intenta actualizarlo también en PocketBase.

El update remoto se ejecuta únicamente si:

- 
   El movimiento tiene `remote_id`, y
- 
   Su categoría tiene `remote_id`.

#### Parameters

androidJvm

| | |
|---|---|
| newMov | Movimiento con los valores editados. |
