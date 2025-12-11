//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[MovRecurRemoteDataSource](index.md)/[fetchAll](fetch-all.md)

# fetchAll

[androidJvm]\
suspend fun [fetchAll](fetch-all.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovRecurRecord](../-mov-recur-record/index.md)&gt;

Descarga todos los movimientos recurrentes pertenecientes al usuario dado.

Realiza una consulta filtrada mediante el campo `user` en PocketBase.

#### Return

Lista de registros remotos [MovRecurRecord](../-mov-recur-record/index.md).

#### Parameters

androidJvm

| | |
|---|---|
| userId | Identificador remoto del usuario propietario. |
