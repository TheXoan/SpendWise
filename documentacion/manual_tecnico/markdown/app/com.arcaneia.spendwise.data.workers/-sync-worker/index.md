//[app](../../../index.md)/[com.arcaneia.spendwise.data.workers](../index.md)/[SyncWorker](index.md)

# SyncWorker

class [SyncWorker](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), workerParams: [WorkerParameters](https://developer.android.com/reference/kotlin/androidx/work/WorkerParameters.html)) : [CoroutineWorker](https://developer.android.com/reference/kotlin/androidx/work/CoroutineWorker.html)

Worker encargado de realizar la **sincronización completa** de la aplicación con PocketBase.

Su misión es mantener la base de datos local perfectamente alineada con el servidor, siguiendo un orden estricto para evitar inconsistencias y errores de integridad.

## 🔄 Flujo completo de sincronización

La sincronización se ejecuta siempre en el siguiente orden:

### **1. Categorías**

Es obligatorio empezar por aquí, ya que:

- 
   Los movimientos simples y recurrentes dependen de las categorías.
- 
   Se asegura que las claves foráneas apunten a registros válidos.

### **2. Movimientos Recurrentes**

- 
   Descarga y actualiza movimientos recurrentes desde PocketBase.
- 
   Subida de cambios locales pendientes.
- 
   Refleja eliminaciones remotas.

### **3. Movimientos Simples**

- 
   Sincroniza todos los movimientos normales.
- 
   Soporta duplicados mediante `renew_hash`.
- 
   Mantiene relación con categorías y mov_recur correctamente.

## 🔔 Lanzar renovaciones automáticas

Una vez finalizada la sincronización principal, el Worker:

1. 
   **Ejecuta** `RenewMovsRecurWorker` **en segundo plano**, que se encarga de:
2. - 
      Detectar renovaciones pendientes
   - 
      Crear movimientos recurrentes (si toca)
   - 
      Subirlos al servidor
   - 
      Notificar al usuario
   - 
      Marcar como notificados
3. 
   Esto garantiza que **cada dispositivo** reciba sus notificaciones locales, incluso si las renovaciones fueron creadas en otro móvil.

## ✔ Garantías del SyncWorker

- 
   Mantiene el orden correcto entre colecciones dependientes.
- 
   Evita fallos de claves foráneas.
- 
   Asegura paridad entre local y remoto.
- 
   Mantiene lógica de negocio aislada en repositorios de sincronización.
- 
   Tras sincronizar, dispara el Worker de renovaciones sin bloquear la UI.

#### See also

| | |
|---|---|
| [RenewMovsRecurWorker](../-renew-movs-recur-worker/index.md) | Worker que procesa renovaciones y notificaciones. |

## Constructors

| | |
|---|---|
| [SyncWorker](-sync-worker.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), workerParams: [WorkerParameters](https://developer.android.com/reference/kotlin/androidx/work/WorkerParameters.html))<br>Recibe el contexto y parámetros del Worker. |

## Properties

| Name | Summary |
|---|---|
| [coroutineContext](index.md#1269180052%2FProperties%2F-912451524) | [androidJvm]<br>open val [~~coroutineContext~~](index.md#1269180052%2FProperties%2F-912451524): CoroutineDispatcher |

## Functions

| Name | Summary |
|---|---|
| [doWork](do-work.md) | [androidJvm]<br>open suspend override fun [doWork](do-work.md)(): [ListenableWorker.Result](https://developer.android.com/reference/kotlin/androidx/work/ListenableWorker.Result.html)<br>Ejecuta la sincronización completa con PocketBase. |
| [getApplicationContext](index.md#-560782721%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getApplicationContext](index.md#-560782721%2FFunctions%2F-912451524)(): @NonNull[Context](https://developer.android.com/reference/kotlin/android/content/Context.html) |
| [getBackgroundExecutor](index.md#1421258461%2FFunctions%2F-912451524) | [androidJvm]<br>@[RestrictTo](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.html)(value = [[RestrictTo.Scope.LIBRARY_GROUP](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.Scope.LIBRARY_GROUP.html)])<br>open fun [getBackgroundExecutor](index.md#1421258461%2FFunctions%2F-912451524)(): @NonNull[Executor](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html) |
| [getForegroundInfo](index.md#1577343784%2FFunctions%2F-912451524) | [androidJvm]<br>open suspend fun [getForegroundInfo](index.md#1577343784%2FFunctions%2F-912451524)(): [ForegroundInfo](https://developer.android.com/reference/kotlin/androidx/work/ForegroundInfo.html) |
| [getForegroundInfoAsync](index.md#67363926%2FFunctions%2F-912451524) | [androidJvm]<br>override fun [getForegroundInfoAsync](index.md#67363926%2FFunctions%2F-912451524)(): ListenableFuture&lt;[ForegroundInfo](https://developer.android.com/reference/kotlin/androidx/work/ForegroundInfo.html)&gt; |
| [getId](index.md#-1759193821%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getId](index.md#-1759193821%2FFunctions%2F-912451524)(): @NonNull[UUID](https://docs.oracle.com/javase/8/docs/api/java/util/UUID.html) |
| [getInputData](index.md#-907781528%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getInputData](index.md#-907781528%2FFunctions%2F-912451524)(): @NonNull[Data](https://developer.android.com/reference/kotlin/androidx/work/Data.html) |
| [getNetwork](index.md#-1225012274%2FFunctions%2F-912451524) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 28)<br>fun [getNetwork](index.md#-1225012274%2FFunctions%2F-912451524)(): @Nullable[Network](https://developer.android.com/reference/kotlin/android/net/Network.html) |
| [getRunAttemptCount](index.md#1096617839%2FFunctions%2F-912451524) | [androidJvm]<br>@[IntRange](https://developer.android.com/reference/kotlin/androidx/annotation/IntRange.html)(from = 0)<br>fun [getRunAttemptCount](index.md#1096617839%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) |
| [getStopReason](index.md#-1809449288%2FFunctions%2F-912451524) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 31)<br>fun [getStopReason](index.md#-1809449288%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) |
| [getTags](index.md#1356325797%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getTags](index.md#1356325797%2FFunctions%2F-912451524)(): @NonNull[MutableSet](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-mutable-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt; |
| [getTaskExecutor](index.md#1625383462%2FFunctions%2F-912451524) | [androidJvm]<br>@[RestrictTo](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.html)(value = [[RestrictTo.Scope.LIBRARY_GROUP](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.Scope.LIBRARY_GROUP.html)])<br>open fun [getTaskExecutor](index.md#1625383462%2FFunctions%2F-912451524)(): @NonNullTaskExecutor |
| [getTriggeredContentAuthorities](index.md#514689021%2FFunctions%2F-912451524) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 24)<br>fun [getTriggeredContentAuthorities](index.md#514689021%2FFunctions%2F-912451524)(): @NonNull[MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-mutable-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt; |
| [getTriggeredContentUris](index.md#-1016068107%2FFunctions%2F-912451524) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 24)<br>fun [getTriggeredContentUris](index.md#-1016068107%2FFunctions%2F-912451524)(): @NonNull[MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)&gt; |
| [getWorkerFactory](index.md#-473896752%2FFunctions%2F-912451524) | [androidJvm]<br>@[RestrictTo](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.html)(value = [[RestrictTo.Scope.LIBRARY_GROUP](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.Scope.LIBRARY_GROUP.html)])<br>open fun [getWorkerFactory](index.md#-473896752%2FFunctions%2F-912451524)(): @NonNull[WorkerFactory](https://developer.android.com/reference/kotlin/androidx/work/WorkerFactory.html) |
| [isStopped](index.md#-43937871%2FFunctions%2F-912451524) | [androidJvm]<br>fun [isStopped](index.md#-43937871%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) |
| [isUsed](index.md#2101847327%2FFunctions%2F-912451524) | [androidJvm]<br>@[RestrictTo](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.html)(value = [[RestrictTo.Scope.LIBRARY_GROUP](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.Scope.LIBRARY_GROUP.html)])<br>fun [isUsed](index.md#2101847327%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) |
| [onStopped](index.md#-1990082143%2FFunctions%2F-912451524) | [androidJvm]<br>override fun [onStopped](index.md#-1990082143%2FFunctions%2F-912451524)() |
| [setForeground](index.md#317365985%2FFunctions%2F-912451524) | [androidJvm]<br>suspend fun [setForeground](index.md#317365985%2FFunctions%2F-912451524)(foregroundInfo: [ForegroundInfo](https://developer.android.com/reference/kotlin/androidx/work/ForegroundInfo.html)) |
| [setForegroundAsync](index.md#-1269350234%2FFunctions%2F-912451524) | [androidJvm]<br>fun [setForegroundAsync](index.md#-1269350234%2FFunctions%2F-912451524)(foregroundInfo: @NonNull[ForegroundInfo](https://developer.android.com/reference/kotlin/androidx/work/ForegroundInfo.html)): @NonNullListenableFuture&lt;[Void](https://docs.oracle.com/javase/8/docs/api/java/lang/Void.html)&gt; |
| [setProgress](index.md#1755411902%2FFunctions%2F-912451524) | [androidJvm]<br>suspend fun [setProgress](index.md#1755411902%2FFunctions%2F-912451524)(data: [Data](https://developer.android.com/reference/kotlin/androidx/work/Data.html)) |
| [setProgressAsync](index.md#-348364649%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [setProgressAsync](index.md#-348364649%2FFunctions%2F-912451524)(data: @NonNull[Data](https://developer.android.com/reference/kotlin/androidx/work/Data.html)): @NonNullListenableFuture&lt;[Void](https://docs.oracle.com/javase/8/docs/api/java/lang/Void.html)&gt; |
| [setUsed](index.md#1019169525%2FFunctions%2F-912451524) | [androidJvm]<br>@[RestrictTo](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.html)(value = [[RestrictTo.Scope.LIBRARY_GROUP](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.Scope.LIBRARY_GROUP.html)])<br>fun [setUsed](index.md#1019169525%2FFunctions%2F-912451524)() |
| [startWork](index.md#-1181660772%2FFunctions%2F-912451524) | [androidJvm]<br>override fun [startWork](index.md#-1181660772%2FFunctions%2F-912451524)(): ListenableFuture&lt;[ListenableWorker.Result](https://developer.android.com/reference/kotlin/androidx/work/ListenableWorker.Result.html)&gt; |
| [stop](index.md#-1498077086%2FFunctions%2F-912451524) | [androidJvm]<br>@[RestrictTo](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.html)(value = [[RestrictTo.Scope.LIBRARY_GROUP](https://developer.android.com/reference/kotlin/androidx/annotation/RestrictTo.Scope.LIBRARY_GROUP.html)])<br>fun [stop](index.md#-1498077086%2FFunctions%2F-912451524)(reason: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)) |
