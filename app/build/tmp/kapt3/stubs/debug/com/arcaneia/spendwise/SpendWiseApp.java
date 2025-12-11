package com.arcaneia.spendwise;

/**
 * Clase base de la aplicación.
 *
 * Esta clase se ejecuta antes que cualquier Activity y es responsable de:
 *
 * ### 🔧 Inicializar WorkManager con los procesos automáticos:
 *
 * ---
 * ## 1️⃣ **RenewMovsRecurWorker** (ejecución periódica cada 15 minutos)
 *
 * Este Worker:
 * - Procesa renovaciones de movimientos recurrentes.
 * - Genera nuevos movimientos si corresponde.
 * - Sincroniza con el servidor.
 * - Envía notificaciones a este dispositivo.
 *
 * Se programa como **trabajo periódico único**, lo que garantiza que:
 * - No haya múltiples instancias repetidas.
 * - WorkManager respete un mínimo de 15 minutos entre ejecuciones (límite Android).
 *
 * ---
 * ## 2️⃣ **SyncWorker** (ejecución periódica cada 15 minutos)
 *
 * Se encarga de:
 * - Sincronizar categorías, movimientos recurrentes y movimientos simples.
 * - Detectar borrados remotos.
 * - Mantener la BD local coherente con PocketBase.
 *
 * También se programa como tarea periódica única.
 *
 * ---
 * ## 3️⃣ **SyncWorker lanzado al iniciar la app**
 *
 * Además de la tarea periódica, se ejecuta una sincronización inmediata al arrancar:
 *
 * - Asegura que el usuario vea datos actualizados desde el primer momento.
 * - Repara estados intermedios o inconsistencias generadas mientras la app estaba cerrada.
 * - No interfiere con la tarea periódica, ya que se programa como trabajo *OneTime*.
 *
 * ---
 * ## ✔ Garantías de este setup
 *
 * - Ningún worker se duplica innecesariamente (`KEEP`).
 * - La app siempre sincroniza datos *on start* (`REPLACE` para asegurar que se ejecute).
 * - Se cumplen los intervalos mínimos exigidos por Android.
 * - El usuario recibe notificaciones de movimientos recurrentes sin retrasos excesivos.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016\u00a8\u0006\u0006"}, d2 = {"Lcom/arcaneia/spendwise/SpendWiseApp;", "Landroid/app/Application;", "<init>", "()V", "onCreate", "", "app_debug"})
public final class SpendWiseApp extends android.app.Application {
    
    public SpendWiseApp() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
}