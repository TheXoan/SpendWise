//[app](../../../index.md)/[com.arcaneia.spendwise.data.workers](../index.md)/[RenewMovsRecurWorker](index.md)/[doWork](do-work.md)

# doWork

[androidJvm]\
open suspend override fun [doWork](do-work.md)(): [ListenableWorker.Result](https://developer.android.com/reference/kotlin/androidx/work/ListenableWorker.Result.html)

Ejecuta el flujo completo:

1. 
   Renovar movimientos recurrentes
2. 
   Subir renovaciones generadas
3. 
   Obtener pendientes de notificar
4. 
   Notificar (si procede)
5. 
   Marcar como notificados
