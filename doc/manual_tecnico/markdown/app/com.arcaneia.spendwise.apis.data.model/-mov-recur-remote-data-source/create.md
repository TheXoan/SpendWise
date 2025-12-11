//[app](../../../index.md)/[com.arcaneia.spendwise.apis.data.model](../index.md)/[MovRecurRemoteDataSource](index.md)/[create](create.md)

# create

[androidJvm]\
suspend fun [create](create.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), movRecur: [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md)): [MovRecurRecord](../-mov-recur-record/index.md)

Crea un nuevo movimiento recurrente en el servidor PocketBase.

Mapea los campos del modelo local [MovRecur](../../com.arcaneia.spendwise.data.entity/-mov-recur/index.md) al formato requerido por el backend, incluyendo:

- 
   Conversión de enums (`Recurrence`, `TypeMov`) a String.
- 
   Inclusión del ID del usuario propietario.

#### Return

El registro creado por el servidor.

#### Parameters

androidJvm

| | |
|---|---|
| userId | ID remoto del usuario propietario del registro. |
| movRecur | Entidad local que se desea subir. |
