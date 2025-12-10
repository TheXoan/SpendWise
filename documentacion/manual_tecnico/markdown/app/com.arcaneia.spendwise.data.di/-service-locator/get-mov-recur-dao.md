//[app](../../../index.md)/[com.arcaneia.spendwise.data.di](../index.md)/[ServiceLocator](index.md)/[getMovRecurDao](get-mov-recur-dao.md)

# getMovRecurDao

[androidJvm]\
fun [getMovRecurDao](get-mov-recur-dao.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [MovRecurDao](../../com.arcaneia.spendwise.data.dao/-mov-recur-dao/index.md)

Obtiene el DAO para movimientos recurrentes ([MovRecurDao](../../com.arcaneia.spendwise.data.dao/-mov-recur-dao/index.md)).

Este DAO se utiliza para:

- 
   gestionar las reglas de recurrencia,
- 
   calcular renovaciones,
- 
   actualizar fechas de próxima ejecución.

#### Return

Instancia de [MovRecurDao](../../com.arcaneia.spendwise.data.dao/-mov-recur-dao/index.md).

#### Parameters

androidJvm

| | |
|---|---|
| context | Contexto necesario para obtener la instancia de Room. |
