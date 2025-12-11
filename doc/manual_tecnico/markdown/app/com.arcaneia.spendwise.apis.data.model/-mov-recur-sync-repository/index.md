//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[MovRecurSyncRepository](index.md)

# MovRecurSyncRepository

[androidJvm]\
class [MovRecurSyncRepository](index.md)(local: [MovRecurDao](../../com.arcaneia.spendwise.data.dao/-mov-recur-dao/index.md), remote: [MovRecurRemoteDataSource](../-mov-recur-remote-data-source/index.md), context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))

Repositorio responsable de la **sincronización completa** de los movimientos recurrentes (`mov_recur`) entre la base de datos local (Room) y el servidor PocketBase.

Esta clase centraliza el flujo de sincronización, gestionando:

1. 
   **Subida de movimientos recurrentes locales pendientes**     (aquellos sin `remote_id`, aún no creados en PocketBase).
2. 
   **Descarga de todos los movimientos recurrentes remotos**     pertenecientes al usuario autenticado.
3. 
   **Fusión remota → local (merge)**
4. - 
      Inserción de nuevos registros remotos.
   - 
      Actualización de registros locales existentes.
   - 
      Conversión segura de enums remotos (`Recurrence`, `TypeMov`).
5. 
   **Eliminación local de movimientos recurrentes borrados remotamente.**

Este repositorio forma parte del sistema de sincronización general de la aplicación, y se ejecuta normalmente:

- 
   Tras login,
- 
   Tras cambios en red,
- 
   O tras operaciones CRUD locales que afecten a datos con vinculación remota.

## Constructors

| | |
|---|---|
| [MovRecurSyncRepository](-mov-recur-sync-repository.md) | [androidJvm]<br>constructor(local: [MovRecurDao](../../com.arcaneia.spendwise.data.dao/-mov-recur-dao/index.md), remote: [MovRecurRemoteDataSource](../-mov-recur-remote-data-source/index.md), context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Functions

| Name | Summary |
|---|---|
| [sync](sync.md) | [androidJvm]<br>suspend fun [sync](sync.md)()<br>Ejecuta el proceso **completo de sincronización** entre local ↔ remoto. |
