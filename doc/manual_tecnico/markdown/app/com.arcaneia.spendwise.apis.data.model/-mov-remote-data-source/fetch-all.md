//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[MovRemoteDataSource](index.md)/[fetchAll](fetch-all.md)

# fetchAll

[androidJvm]\
suspend fun [fetchAll](fetch-all.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[MovRecord](../../[root]/-mov-record/index.md)&gt;

Obtiene todos los movimientos remotos pertenecientes al usuario especificado.

Esta llamada aplica un filtro en el servidor:

```kotlin
user='<userId>'
```

#### Return

Lista de [MovRecord](../../[root]/-mov-record/index.md) correspondientes al usuario.

#### Parameters

androidJvm

| | |
|---|---|
| userId | ID remoto del usuario autenticado. |
