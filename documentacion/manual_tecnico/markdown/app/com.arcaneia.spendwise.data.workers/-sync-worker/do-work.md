//[app](../../../index.md)/[com.arcaneia.spendwise.data.workers](../index.md)/[SyncWorker](index.md)/[doWork](do-work.md)

# doWork

[androidJvm]\
open suspend override fun [doWork](do-work.md)(): [ListenableWorker.Result](https://developer.android.com/reference/kotlin/androidx/work/ListenableWorker.Result.html)

Ejecuta la sincronización completa con PocketBase.

Si ocurre un error en cualquier punto, se retorna `Result.retry()` para que WorkManager vuelva a intentarlo más tarde de manera automática.
