//[app](../../../index.md)/[com.arcaneia.spendwise.data.model](../index.md)/[MovRecurViewModel](index.md)/[delete](delete.md)

# delete

[androidJvm]\
fun [delete](delete.md)(m: [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)): Job

Elimina un movimiento recurrente tanto local como remotamente.

Flujo:

- 
   Si el movimiento tiene `remote_id`, se elimina en PocketBase.
- 
   Luego se elimina de Room.

#### Parameters

androidJvm

| | |
|---|---|
| m | Movimiento recurrente que se desea eliminar. |
