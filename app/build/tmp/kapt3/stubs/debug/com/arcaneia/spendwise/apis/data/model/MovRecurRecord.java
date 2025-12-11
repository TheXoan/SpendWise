package com.arcaneia.spendwise.apis.data.model;

/**
 * Representa un **registro remoto de movimiento recurrente** tal como es devuelto por PocketBase.
 *
 * Esta clase modela exactamente la estructura de un ítem dentro de la colección remota
 * `mov_recur` en el servidor, y se utiliza para mapear las respuestas JSON recibidas por Retrofit.
 *
 * Cada instancia refleja el estado actual del movimiento recurrente almacenado en el backend,
 * incluyendo su periodicidad, fechas relacionadas y tipo de movimiento.
 *
 * ### Relación con la capa local
 * Este objeto es utilizado por el sincronizador (`MovRecurSyncRepository`) para:
 * - Crear nuevos registros locales si no existen.
 * - Actualizar datos locales cuando el servidor tiene cambios.
 * - Relacionar los movimientos recurrentes remotos con sus equivalentes locales mediante `remote_id`.
 *
 * ### Campos recibidos desde PocketBase
 * PocketBase almacena los enums (`Recurrence`, `TypeMov`) como **strings**, por lo que aquí se reciben
 * en formato textual y posteriormente se convierten en enums Kotlin en la capa de sincronización.
 *
 * @property id Identificador único del registro en PocketBase.
 * @property nome Nombre descriptivo del movimiento recurrente.
 * @property importe Importe económico que se genera en cada renovación.
 * @property periodicidade Tipo de recurrencia en formato String tal como se guarda en PocketBase.
 * @property data_ini Fecha de inicio en formato `YYYY-MM-DD`.
 * @property data_rnv Fecha programada para la próxima renovación (`YYYY-MM-DD`).
 * @property tipo Tipo de movimiento en formato String (`"INGRESO"` o `"GASTO"`).
 * @property user Identificador remoto del usuario propietario de este registro.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0006H\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J]\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020%H\u00d6\u0001J\t\u0010&\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f\u00a8\u0006\'"}, d2 = {"Lcom/arcaneia/spendwise/apis/data/model/MovRecurRecord;", "", "id", "", "nome", "importe", "", "periodicidade", "data_ini", "data_rnv", "tipo", "user", "<init>", "(Ljava/lang/String;Ljava/lang/String;DLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getNome", "getImporte", "()D", "getPeriodicidade", "getData_ini", "getData_rnv", "getTipo", "getUser", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class MovRecurRecord {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String nome = null;
    private final double importe = 0.0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String periodicidade = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String data_ini = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String data_rnv = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String tipo = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String user = null;
    
    public MovRecurRecord(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String nome, double importe, @org.jetbrains.annotations.Nullable()
    java.lang.String periodicidade, @org.jetbrains.annotations.NotNull()
    java.lang.String data_ini, @org.jetbrains.annotations.NotNull()
    java.lang.String data_rnv, @org.jetbrains.annotations.Nullable()
    java.lang.String tipo, @org.jetbrains.annotations.NotNull()
    java.lang.String user) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNome() {
        return null;
    }
    
    public final double getImporte() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPeriodicidade() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getData_ini() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getData_rnv() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTipo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final double component3() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.arcaneia.spendwise.apis.data.model.MovRecurRecord copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String nome, double importe, @org.jetbrains.annotations.Nullable()
    java.lang.String periodicidade, @org.jetbrains.annotations.NotNull()
    java.lang.String data_ini, @org.jetbrains.annotations.NotNull()
    java.lang.String data_rnv, @org.jetbrains.annotations.Nullable()
    java.lang.String tipo, @org.jetbrains.annotations.NotNull()
    java.lang.String user) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}