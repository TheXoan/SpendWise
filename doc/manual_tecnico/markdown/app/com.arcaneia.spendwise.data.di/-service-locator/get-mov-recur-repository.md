//[app](../../../index.md)/[com.arcaneia.spendwise.data.di](../index.md)/[ServiceLocator](index.md)/[getMovRecurRepository](get-mov-recur-repository.md)

# getMovRecurRepository

[androidJvm]\
fun [getMovRecurRepository](get-mov-recur-repository.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [MovRecurRepository](../../com.arcaneia.spendwise.data.repository/-mov-recur-repository/index.md)

Obtiene una instancia funcional de [MovRecurRepository](../../com.arcaneia.spendwise.data.repository/-mov-recur-repository/index.md).

Este repositorio requiere dos DAOs:

- 
   [MovRecurDao](../../com.arcaneia.spendwise.data.dao/-mov-recur-dao/index.md) para gestionar movimientos recurrentes.
- 
   [MovDao](../../com.arcaneia.spendwise.data.dao/-mov-dao/index.md) para generar movimientos derivados de renovaciones.

Además se pasa el `context` como `appContext` para permitir la subida remota desde el propio repositorio cuando se realizan renovaciones automáticas.

#### Return

Instancia lista para usar de [MovRecurRepository](../../com.arcaneia.spendwise.data.repository/-mov-recur-repository/index.md).

#### Parameters

androidJvm

| | |
|---|---|
| context | Contexto utilizado para obtener la base de datos. |
