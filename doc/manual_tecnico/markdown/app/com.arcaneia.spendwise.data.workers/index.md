//[app](../../index.md)/[com.arcaneia.spendwise.data.workers](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [RenewMovsRecurWorker](-renew-movs-recur-worker/index.md) | [androidJvm]<br>class [RenewMovsRecurWorker](-renew-movs-recur-worker/index.md)(appContext: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), params: [WorkerParameters](https://developer.android.com/reference/kotlin/androidx/work/WorkerParameters.html)) : [CoroutineWorker](https://developer.android.com/reference/kotlin/androidx/work/CoroutineWorker.html)<br>Worker responsable de procesar renovaciones de movimientos recurrentes y gestionar sus notificaciones locales. |
| [SyncWorker](-sync-worker/index.md) | [androidJvm]<br>class [SyncWorker](-sync-worker/index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), workerParams: [WorkerParameters](https://developer.android.com/reference/kotlin/androidx/work/WorkerParameters.html)) : [CoroutineWorker](https://developer.android.com/reference/kotlin/androidx/work/CoroutineWorker.html)<br>Worker encargado de realizar la **sincronización completa** de la aplicación con PocketBase. |
