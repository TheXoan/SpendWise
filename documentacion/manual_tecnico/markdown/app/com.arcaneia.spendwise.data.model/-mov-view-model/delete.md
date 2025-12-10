//[app](../../../index.md)/[com.arcaneia.spendwise.data.model](../index.md)/[MovViewModel](index.md)/[delete](delete.md)

# delete

[androidJvm]\
fun [delete](delete.md)(mov: [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)): Job

Elimina un movimiento simple tanto en PocketBase como en la base de datos local.

Flujo:

1. 
   Si el movimiento tiene `remote_id`, se elimina en PocketBase.
2. 
   Se elimina de Room de inmediato.

#### Parameters

androidJvm

| | |
|---|---|
| mov | Movimiento a eliminar. |
