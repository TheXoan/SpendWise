
/**
 * Representa un registro remoto de la colección `mov` en PocketBase.
 *
 * Esta clase actúa como un **DTO (Data Transfer Object)** utilizado para mapear
 * las respuestas JSON provenientes del servidor. Cada instancia corresponde a
 * un movimiento simple almacenado en PocketBase, incluyendo información básica,
 * referencias a entidades relacionadas y metadatos útiles para sincronización.
 *
 * ---
 *
 * ## 🔗 Relaciones y sincronización
 *
 * PocketBase almacena relaciones mediante **IDs remotos (String)**.
 * Por ello, los campos `categoria_id` y `mov_recur_id` deben ser traducidos
 * posteriormente por el `MovSyncRepository` a los IDs internos de Room.
 *
 * Además, este DTO incluye el campo `renew_hash`, un identificador único
 * generado por las renovaciones automáticas, que permite:
 * - Detectar duplicados en sincronizaciones entre dispositivos.
 * - Evitar que un mismo movimiento recurrente genere múltiples copias.
 *
 * ---
 *
 * ## Propiedades
 *
 * @property id
 * ID único generado por PocketBase para este movimiento.
 *
 * @property tipo
 * Tipo de movimiento (`INGRESO` o `GASTO`) como String. Puede ser nulo si
 * en el servidor no se estableció el campo.
 *
 * @property importe
 * Cantidad económica asociada al movimiento.
 *
 * @property data_mov
 * Fecha del movimiento en formato `"YYYY-MM-DD HH:mm:ss"` o `"YYYY-MM-DD"`
 * según el origen del dato.
 *
 * @property descricion
 * Texto descriptivo del movimiento. Puede ser nulo.
 *
 * @property categoria_id
 * ID remoto de la categoría asociada. Debe mapearse al ID local en Room.
 *
 * @property mov_recur_id
 * ID remoto de la entrada `mov_recur` que generó este movimiento.
 * Es nulo si el movimiento no proviene de una recurrencia.
 *
 * @property user
 * ID remoto del usuario propietario del registro.
 *
 * @property renew_hash
 * Identificador único que permite detectar movimientos creados automáticamente
 * por renovaciones recurrentes y evitar duplicados en la sincronización.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003Jk\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\'\u001a\u00020(H\u00d6\u0001J\t\u0010)\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010\u00a8\u0006*"}, d2 = {"LMovRecord;", "", "id", "", "tipo", "importe", "", "data_mov", "descricion", "categoria_id", "mov_recur_id", "user", "renew_hash", "<init>", "(Ljava/lang/String;Ljava/lang/String;DLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getTipo", "getImporte", "()D", "getData_mov", "getDescricion", "getCategoria_id", "getMov_recur_id", "getUser", "getRenew_hash", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class MovRecord {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String tipo = null;
    private final double importe = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String data_mov = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String descricion = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String categoria_id = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String mov_recur_id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String user = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String renew_hash = null;
    
    public MovRecord(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.Nullable()
    java.lang.String tipo, double importe, @org.jetbrains.annotations.NotNull()
    java.lang.String data_mov, @org.jetbrains.annotations.Nullable()
    java.lang.String descricion, @org.jetbrains.annotations.NotNull()
    java.lang.String categoria_id, @org.jetbrains.annotations.Nullable()
    java.lang.String mov_recur_id, @org.jetbrains.annotations.NotNull()
    java.lang.String user, @org.jetbrains.annotations.Nullable()
    java.lang.String renew_hash) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTipo() {
        return null;
    }
    
    public final double getImporte() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getData_mov() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDescricion() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCategoria_id() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMov_recur_id() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUser() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRenew_hash() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    public final double component3() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
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
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final MovRecord copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.Nullable()
    java.lang.String tipo, double importe, @org.jetbrains.annotations.NotNull()
    java.lang.String data_mov, @org.jetbrains.annotations.Nullable()
    java.lang.String descricion, @org.jetbrains.annotations.NotNull()
    java.lang.String categoria_id, @org.jetbrains.annotations.Nullable()
    java.lang.String mov_recur_id, @org.jetbrains.annotations.NotNull()
    java.lang.String user, @org.jetbrains.annotations.Nullable()
    java.lang.String renew_hash) {
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