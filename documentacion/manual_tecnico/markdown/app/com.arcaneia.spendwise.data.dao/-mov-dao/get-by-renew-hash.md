//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[MovDao](index.md)/[getByRenewHash](get-by-renew-hash.md)

# getByRenewHash

[androidJvm]\
abstract suspend fun [getByRenewHash](get-by-renew-hash.md)(hash: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)?

Busca un movimiento por su `renew_hash`, usado para detectar duplicados durante la sincronización de renovaciones recurrentes.

#### Return

Movimiento encontrado o `null`.

#### Parameters

androidJvm

| | |
|---|---|
| hash | Valor del hash único generado en la renovación. |
