//[app](../../../index.md)/[com.arcaneia.spendwise.data.repository](../index.md)/[MovRecurRepository](index.md)/[processRenewals](process-renewals.md)

# processRenewals

[androidJvm]\
suspend fun [processRenewals](process-renewals.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)&gt;

Procesa todas las renovaciones pendientes de movimientos recurrentes.

Flujo completo:

1. 
   Obtiene todos los `MovRecur` cuya `data_rnv` sea ≤ hoy.
2. 
   Por cada uno:
3. - 
      Genera movimientos simples ([Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)) con fecha actual.
   - 
      Crea un **renew_hash determinista**, único por fecha de renovación.
   - 
      Inserta esos movimientos simples en la base local.
   - 
      Avanza `data_rnv` tantas veces como sea necesario.
   - 
      Actualiza el `MovRecur` en local.
   - 
      Si tiene `remote_id`, actualiza también el registro en PocketBase.
4. 
   Devuelve una lista con todos los movimientos generados.

Este método **no notifica** al usuario. La notificación se realiza posteriormente por el `RenewMovsRecurWorker`.

#### Return

Lista de movimientos simples creados durante el proceso.
