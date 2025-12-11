//[app](../../../index.md)/[com.arcaneia.spendwise.data.model](../index.md)/[MovRecurViewModel](index.md)/[insert](insert.md)

# insert

[androidJvm]\
fun [insert](insert.md)(m: [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)): Job

Inserta un movimiento recurrente **localmente de forma inmediata** y luego intenta sincronizarlo con el servidor PocketBase.

Flujo:

1. 
   Se inserta en Room.
2. 
   Se crea el registro en PocketBase.
3. 
   Se actualiza el registro local con su `remote_id`.

#### Parameters

androidJvm

| | |
|---|---|
| m | Movimiento recurrente a insertar. |
