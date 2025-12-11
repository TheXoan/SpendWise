//[app](../../../index.md)/[com.arcaneia.spendwise.data.dao](../index.md)/[MovDao](index.md)/[getPendingNotifications](get-pending-notifications.md)

# getPendingNotifications

[androidJvm]\
abstract suspend fun [getPendingNotifications](get-pending-notifications.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Mov](../../com.arcaneia.spendwise.data.entity/-mov/index.md)&gt;

Obtiene todos los movimientos que aún no han sido notificados.

Usado por RenewMovsRecurWorker para mostrar notificaciones locales.

#### Return

Lista de movimientos sin notificar.
