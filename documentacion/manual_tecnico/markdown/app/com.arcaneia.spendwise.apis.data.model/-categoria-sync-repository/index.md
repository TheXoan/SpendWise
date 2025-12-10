//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[CategoriaSyncRepository](index.md)

# CategoriaSyncRepository

[androidJvm]\
class [CategoriaSyncRepository](index.md)(local: [CategoriaDao](../../com.arcaneia.spendwise.data.dao/-categoria-dao/index.md), remote: [CategoriaRemoteDataSource](../-categoria-remote-data-source/index.md), context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))

Repositorio responsable de sincronizar las categorías entre la base de datos local (Room) y la base de datos remota (PocketBase).

Este repositorio implementa una estrategia híbrida que garantiza:

- 
   La asignación correcta del `remote_id` a las categorías locales.
- 
   La detección y vinculación especial de la categoría **&quot;Recurrente&quot;**, evitando duplicaciones.
- 
   La subida de categorías locales que aún no han sido sincronizadas.
- 
   La descarga y fusión de cambios realizados en el servidor.
- 
   La eliminación local de categorías que fueron borradas en el servidor (exceptuando &quot;Recurrente&quot;).

La sincronización mantiene la coherencia entre dispositivos y evita la creación de categorías duplicadas durante el proceso de login en múltiples terminales.

## Constructors

| | |
|---|---|
| [CategoriaSyncRepository](-categoria-sync-repository.md) | [androidJvm]<br>constructor(local: [CategoriaDao](../../com.arcaneia.spendwise.data.dao/-categoria-dao/index.md), remote: [CategoriaRemoteDataSource](../-categoria-remote-data-source/index.md), context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Functions

| Name | Summary |
|---|---|
| [sync](sync.md) | [androidJvm]<br>suspend fun [sync](sync.md)()<br>Ejecuta el proceso completo de sincronización entre la base de datos local y remota. |
