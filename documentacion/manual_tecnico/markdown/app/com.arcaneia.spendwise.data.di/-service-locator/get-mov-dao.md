//[app](../../../index.md)/[com.arcaneia.spendwise.data.di](../index.md)/[ServiceLocator](index.md)/[getMovDao](get-mov-dao.md)

# getMovDao

[androidJvm]\
fun [getMovDao](get-mov-dao.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [MovDao](../../com.arcaneia.spendwise.data.dao/-mov-dao/index.md)

Obtiene el DAO encargado de los movimientos simples ([MovDao](../../com.arcaneia.spendwise.data.dao/-mov-dao/index.md)).

Útil en procesos como:

- 
   sincronización,
- 
   generación de notificaciones,
- 
   manipulación directa de movimientos.

#### Return

Instancia de [MovDao](../../com.arcaneia.spendwise.data.dao/-mov-dao/index.md).

#### Parameters

androidJvm

| | |
|---|---|
| context | Contexto necesario para resolver la base de datos. |
