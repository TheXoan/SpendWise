//[app](../../../index.md)/[com.arcaneia.spendwise.data.workers](../index.md)/[RenewMovsRecurWorker](index.md)

# RenewMovsRecurWorker

[androidJvm]\
class [RenewMovsRecurWorker](index.md)(appContext: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), params: [WorkerParameters](https://developer.android.com/reference/kotlin/androidx/work/WorkerParameters.html)) : [CoroutineWorker](https://developer.android.com/reference/kotlin/androidx/work/CoroutineWorker.html)

Worker responsable de procesar renovaciones de movimientos recurrentes y gestionar sus notificaciones locales.

Este Worker garantiza un flujo completo y seguro:

### 🔄 **1. Procesar renovaciones (MovRecur → Mov)**

Ejecuta `MovRecurRepository.processRenewals()`:

- 
   Genera movimientos simples ([Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)) cuando corresponde según `data_rnv`.
- 
   Cada movimiento creado incluye un `renew_hash` determinista para evitar duplicados.
- 
   Devuelve los movimientos generados en esta ejecución.

### ☁ **2. Sube inmediatamente los movimientos generados a PocketBase**

Utiliza [MovSyncRepository](../../com.arcaneia.spendwise.apis.data.model/-mov-sync-repository/index.md) para:

- 
   Subir los nuevos movimientos
- 
   Resolver duplicados mediante `renew_hash`
- 
   Traer cambios remotos
- 
   Mantener el estado local consistente con PocketBase

Esto asegura que:

- 
   Otros dispositivos verán las renovaciones
- 
   Ningún dispositivo genere la misma renovación dos veces

### 🔔 **3. Obtiene todos los movimientos con** `notificado = 0`

El repositorio no marca como “notificado” durante su creación. Lo hace exclusivamente este Worker, permitiendo:

- 
   Mostrar notificaciones locales
- 
   No repetirlas nunca más
- 
   Notificar renovaciones creadas en *otro* dispositivo al sincronizar

### 📳 **4. Genera notificaciones locales**

Solo se envían si:

- 
   El usuario dio permiso interno desde ajustes de la app
- 
   Y el sistema tiene permiso para mostrar notificaciones (Android 13+)

Una notificación por cada movimiento pendiente.

### 🏁 **5. Marca todos los pendientes como notificados**

Esto evita repetición infinita de notificaciones, incluso si el Worker se vuelve a ejecutar varias veces.

### ✔ Garantías del sistema

- 
   ❌ No duplica renovaciones (gracias al `renew_hash` determinista)
- 
   ❌ No duplica notificaciones (se notifican solo los `notificado = 0`)
- 
   ✔ Funciona en todos los dispositivos sincronizados (cada móvil genera notificaciones de los Mov que existan en *su* base local)

## Constructors

| | |
|---|---|
| [RenewMovsRecurWorker](-renew-movs-recur-worker.md) | [androidJvm]<br>constructor(appContext: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), params: [WorkerParameters](https://developer.android.com/reference/kotlin/androidx/work/WorkerParameters.html))<br>Recibe el contexto y parámetros del Worker. |

## Properties

| Name | Summary |
|---|---|
| [coroutineContext](../-sync-worker/index.md#1269180052%2FProperties%2F-912451524) | [androidJvm]<br>open val [~~coroutineContext~~](../-sync-worker/index.md#1269180052%2FProperties%2F-912451524): CoroutineDispatcher |

## Functions

| Name | Summary |
|---|---|
| [doWork](do-work.md) | [androidJvm]<br>open suspend override fun [doWork](do-work.md)(): [ListenableWorker.Result](https://developer.android.com/reference/kotlin/androidx/work/ListenableWorker.Result.html)<br>Ejecuta el flujo completo: |
| [getApplicationContext](../-sync-worker/index.md#-560782721%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getApplicationContext](../-sync-worker/index.md#-560782721%2FFunctions%2F-912451524)(): @NonNull[Context](https://developer.android.com/reference/kotlin/android/content/Context.html) |
| [getBackgroundExecutor](../-sync-worker/index.md#1421258461%2FFunctions%2F-912451524) | [androidJvm]<br>@[RestrictTo](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.html)(value = [[RestrictTo.Scope.LIBRARY_GROUP](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.Scope.LIBRARY_GROUP.html)])<br>open fun [getBackgroundExecutor](../-sync-worker/index.md#1421258461%2FFunctions%2F-912451524)(): @NonNull[Executor](https://developer.android.com/reference/kotlin/java/util/concurrent/Executor.html) |
| [getForegroundInfo](../-sync-worker/index.md#1577343784%2FFunctions%2F-912451524) | [androidJvm]<br>open suspend fun [getForegroundInfo](../-sync-worker/index.md#1577343784%2FFunctions%2F-912451524)(): [ForegroundInfo](https://developer.android.com/reference/kotlin/androidx/work/ForegroundInfo.html) |
| [getForegroundInfoAsync](../-sync-worker/index.md#67363926%2FFunctions%2F-912451524) | [androidJvm]<br>override fun [getForegroundInfoAsync](../-sync-worker/index.md#67363926%2FFunctions%2F-912451524)(): ListenableFuture&lt;[ForegroundInfo](https://developer.android.com/reference/kotlin/androidx/work/ForegroundInfo.html)&gt; |
| [getId](../-sync-worker/index.md#-1759193821%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getId](../-sync-worker/index.md#-1759193821%2FFunctions%2F-912451524)(): @NonNull[UUID](https://developer.android.com/reference/kotlin/java/util/UUID.html) |
| [getInputData](../-sync-worker/index.md#-907781528%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getInputData](../-sync-worker/index.md#-907781528%2FFunctions%2F-912451524)(): @NonNull[Data](https://developer.android.com/reference/kotlin/androidx/work/Data.html) |
| [getNetwork](../-sync-worker/index.md#-1225012274%2FFunctions%2F-912451524) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 28)<br>fun [getNetwork](../-sync-worker/index.md#-1225012274%2FFunctions%2F-912451524)(): @Nullable[Network](https://developer.android.com/reference/kotlin/android/net/Network.html) |
| [getRunAttemptCount](../-sync-worker/index.md#1096617839%2FFunctions%2F-912451524) | [androidJvm]<br>@[IntRange](https://developer.android.com/reference/kotlin/androidx/annotation/IntRange.html)(from = 0)<br>fun [getRunAttemptCount](../-sync-worker/index.md#1096617839%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) |
| [getStopReason](../-sync-worker/index.md#-1809449288%2FFunctions%2F-912451524) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 31)<br>fun [getStopReason](../-sync-worker/index.md#-1809449288%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) |
| [getTags](../-sync-worker/index.md#1356325797%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getTags](../-sync-worker/index.md#1356325797%2FFunctions%2F-912451524)(): @NonNull[MutableSet](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-mutable-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt; |
| [getTaskExecutor](../-sync-worker/index.md#1625383462%2FFunctions%2F-912451524) | [androidJvm]<br>@[RestrictTo](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.html)(value = [[RestrictTo.Scope.LIBRARY_GROUP](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.Scope.LIBRARY_GROUP.html)])<br>open fun [getTaskExecutor](../-sync-worker/index.md#1625383462%2FFunctions%2F-912451524)(): @NonNullTaskExecutor |
| [getTriggeredContentAuthorities](../-sync-worker/index.md#514689021%2FFunctions%2F-912451524) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 24)<br>fun [getTriggeredContentAuthorities](../-sync-worker/index.md#514689021%2FFunctions%2F-912451524)(): @NonNull[MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-mutable-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt; |
| [getTriggeredContentUris](../-sync-worker/index.md#-1016068107%2FFunctions%2F-912451524) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 24)<br>fun [getTriggeredContentUris](../-sync-worker/index.md#-1016068107%2FFunctions%2F-912451524)(): @NonNull[MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)&gt; |
| [getWorkerFactory](../-sync-worker/index.md#-473896752%2FFunctions%2F-912451524) | [androidJvm]<br>@[RestrictTo](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.html)(value = [[RestrictTo.Scope.LIBRARY_GROUP](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.Scope.LIBRARY_GROUP.html)])<br>open fun [getWorkerFactory](../-sync-worker/index.md#-473896752%2FFunctions%2F-912451524)(): @NonNull[WorkerFactory](https://developer.android.com/reference/kotlin/androidx/work/WorkerFactory.html) |
| [isStopped](../-sync-worker/index.md#-43937871%2FFunctions%2F-912451524) | [androidJvm]<br>fun [isStopped](../-sync-worker/index.md#-43937871%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) |
| [isUsed](../-sync-worker/index.md#2101847327%2FFunctions%2F-912451524) | [androidJvm]<br>@[RestrictTo](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.html)(value = [[RestrictTo.Scope.LIBRARY_GROUP](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.Scope.LIBRARY_GROUP.html)])<br>fun [isUsed](../-sync-worker/index.md#2101847327%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) |
| [onStopped](../-sync-worker/index.md#-1990082143%2FFunctions%2F-912451524) | [androidJvm]<br>override fun [onStopped](../-sync-worker/index.md#-1990082143%2FFunctions%2F-912451524)() |
| [setForeground](../-sync-worker/index.md#317365985%2FFunctions%2F-912451524) | [androidJvm]<br>suspend fun [setForeground](../-sync-worker/index.md#317365985%2FFunctions%2F-912451524)(foregroundInfo: [ForegroundInfo](https://developer.android.com/reference/kotlin/androidx/work/ForegroundInfo.html)) |
| [setForegroundAsync](../-sync-worker/index.md#-1269350234%2FFunctions%2F-912451524) | [androidJvm]<br>fun [setForegroundAsync](../-sync-worker/index.md#-1269350234%2FFunctions%2F-912451524)(foregroundInfo: @NonNull[ForegroundInfo](https://developer.android.com/reference/kotlin/androidx/work/ForegroundInfo.html)): @NonNullListenableFuture&lt;[Void](https://developer.android.com/reference/kotlin/java/lang/Void.html)&gt; |
| [setProgress](../-sync-worker/index.md#1755411902%2FFunctions%2F-912451524) | [androidJvm]<br>suspend fun [setProgress](../-sync-worker/index.md#1755411902%2FFunctions%2F-912451524)(data: [Data](https://developer.android.com/reference/kotlin/androidx/work/Data.html)) |
| [setProgressAsync](../-sync-worker/index.md#-348364649%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [setProgressAsync](../-sync-worker/index.md#-348364649%2FFunctions%2F-912451524)(data: @NonNull[Data](https://developer.android.com/reference/kotlin/androidx/work/Data.html)): @NonNullListenableFuture&lt;[Void](https://developer.android.com/reference/kotlin/java/lang/Void.html)&gt; |
| [setUsed](../-sync-worker/index.md#1019169525%2FFunctions%2F-912451524) | [androidJvm]<br>@[RestrictTo](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.html)(value = [[RestrictTo.Scope.LIBRARY_GROUP](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.Scope.LIBRARY_GROUP.html)])<br>fun [setUsed](../-sync-worker/index.md#1019169525%2FFunctions%2F-912451524)() |
| [startWork](../-sync-worker/index.md#-1181660772%2FFunctions%2F-912451524) | [androidJvm]<br>override fun [startWork](../-sync-worker/index.md#-1181660772%2FFunctions%2F-912451524)(): ListenableFuture&lt;[ListenableWorker.Result](https://developer.android.com/reference/kotlin/androidx/work/ListenableWorker.Result.html)&gt; |
| [stop](../-sync-worker/index.md#-1498077086%2FFunctions%2F-912451524) | [androidJvm]<br>@[RestrictTo](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.html)(value = [[RestrictTo.Scope.LIBRARY_GROUP](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.Scope.LIBRARY_GROUP.html)])<br>fun [stop](../-sync-worker/index.md#-1498077086%2FFunctions%2F-912451524)(reason: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)) |
