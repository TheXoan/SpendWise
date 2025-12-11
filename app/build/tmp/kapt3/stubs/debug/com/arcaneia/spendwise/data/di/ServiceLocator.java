package com.arcaneia.spendwise.data.di;

/**
 * Proveedor centralizado de dependencias para la capa de datos.
 *
 * Este objeto funciona como un **Service Locator**, permitiendo obtener instancias
 * de repositorios y DAOs sin necesidad de un framework de inyección de dependencias
 * como Hilt o Koin.
 *
 * El principal objetivo de este componente es:
 * - Simplificar la creación de repositorios.
 * - Garantizar el acceso coherente a DAOs de Room.
 * - Evitar duplicación de código en distintas capas de la aplicación.
 *
 * Todas las dependencias provistas aquí utilizan la instancia única de la base
 * de datos generada por [AppDatabase.getDatabase].
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007\u00a8\u0006\f"}, d2 = {"Lcom/arcaneia/spendwise/data/di/ServiceLocator;", "", "<init>", "()V", "getMovRecurRepository", "Lcom/arcaneia/spendwise/data/repository/MovRecurRepository;", "context", "Landroid/content/Context;", "getMovDao", "Lcom/arcaneia/spendwise/data/dao/MovDao;", "getMovRecurDao", "Lcom/arcaneia/spendwise/data/dao/MovRecurDao;", "app_debug"})
public final class ServiceLocator {
    @org.jetbrains.annotations.NotNull()
    public static final com.arcaneia.spendwise.data.di.ServiceLocator INSTANCE = null;
    
    private ServiceLocator() {
        super();
    }
    
    /**
     * Obtiene una instancia funcional de [MovRecurRepository].
     *
     * Este repositorio requiere dos DAOs:
     * - [MovRecurDao] para gestionar movimientos recurrentes.
     * - [MovDao] para generar movimientos derivados de renovaciones.
     *
     * Además se pasa el `context` como `appContext` para permitir la subida remota
     * desde el propio repositorio cuando se realizan renovaciones automáticas.
     *
     * @param context Contexto utilizado para obtener la base de datos.
     * @return Instancia lista para usar de [MovRecurRepository].
     */
    @org.jetbrains.annotations.NotNull()
    public final com.arcaneia.spendwise.data.repository.MovRecurRepository getMovRecurRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    /**
     * Obtiene el DAO encargado de los movimientos simples ([MovDao]).
     *
     * Útil en procesos como:
     * - sincronización,
     * - generación de notificaciones,
     * - manipulación directa de movimientos.
     *
     * @param context Contexto necesario para resolver la base de datos.
     * @return Instancia de [MovDao].
     */
    @org.jetbrains.annotations.NotNull()
    public final com.arcaneia.spendwise.data.dao.MovDao getMovDao(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    /**
     * Obtiene el DAO para movimientos recurrentes ([MovRecurDao]).
     *
     * Este DAO se utiliza para:
     * - gestionar las reglas de recurrencia,
     * - calcular renovaciones,
     * - actualizar fechas de próxima ejecución.
     *
     * @param context Contexto necesario para obtener la instancia de Room.
     * @return Instancia de [MovRecurDao].
     */
    @org.jetbrains.annotations.NotNull()
    public final com.arcaneia.spendwise.data.dao.MovRecurDao getMovRecurDao(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}