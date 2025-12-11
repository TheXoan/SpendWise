//[app](../../../index.md)/[com.arcaneia.spendwise.data.di](../index.md)/[ServiceLocator](index.md)

# ServiceLocator

[androidJvm]\
object [ServiceLocator](index.md)

Proveedor centralizado de dependencias para la capa de datos.

Este objeto funciona como un **Service Locator**, permitiendo obtener instancias de repositorios y DAOs sin necesidad de un framework de inyección de dependencias como Hilt o Koin.

El principal objetivo de este componente es:

- 
   Simplificar la creación de repositorios.
- 
   Garantizar el acceso coherente a DAOs de Room.
- 
   Evitar duplicación de código en distintas capas de la aplicación.

Todas las dependencias provistas aquí utilizan la instancia única de la base de datos generada por [AppDatabase.getDatabase](../../com.arcaneia.spendwise.data.database/-app-database/-companion/get-database.md).

## Functions

| Name | Summary |
|---|---|
| [getMovDao](get-mov-dao.md) | [androidJvm]<br>fun [getMovDao](get-mov-dao.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [MovDao](../../com.arcaneia.spendwise.data.dao/-mov-dao/index.md)<br>Obtiene el DAO encargado de los movimientos simples ([MovDao](../../com.arcaneia.spendwise.data.dao/-mov-dao/index.md)). |
| [getMovRecurDao](get-mov-recur-dao.md) | [androidJvm]<br>fun [getMovRecurDao](get-mov-recur-dao.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [MovRecurDao](../../com.arcaneia.spendwise.data.dao/-mov-recur-dao/index.md)<br>Obtiene el DAO para movimientos recurrentes ([MovRecurDao](../../com.arcaneia.spendwise.data.dao/-mov-recur-dao/index.md)). |
| [getMovRecurRepository](get-mov-recur-repository.md) | [androidJvm]<br>fun [getMovRecurRepository](get-mov-recur-repository.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [MovRecurRepository](../../com.arcaneia.spendwise.data.repository/-mov-recur-repository/index.md)<br>Obtiene una instancia funcional de [MovRecurRepository](../../com.arcaneia.spendwise.data.repository/-mov-recur-repository/index.md). |
