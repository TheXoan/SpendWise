package com.arcaneia.spendwise.data.entity;

/**
 * Entidad que representa un **movimiento recurrente** dentro de la aplicación (Room).
 *
 * Los movimientos recurrentes permiten programar transacciones automáticas en
 * intervalos definidos (mensual, anual, semanal, etc.).
 * Cada vez que un movimiento recurrente llega a su fecha de renovación (`data_rnv`),
 * la aplicación puede generar automáticamente un movimiento normal asociado.
 *
 * La tabla asociada se llama `mov_recur`.
 *
 * @property id Identificador único del movimiento recurrente.
 * Se genera automáticamente mediante `autoGenerate = true`.
 *
 * @property nome Nombre descriptivo del movimiento recurrente
 * (ej.: “Suscripción Netflix”, “Salario mensual”).
 *
 * @property importe Monto económico que se renovará periódicamente.
 *
 * @property periodicidade Tipo de recurrencia, representado por el enum [Recurrence]
 * ("MENSUAL", "ANUAL", "SEMANAL", etc.). Puede ser `null` en algunos casos especiales.
 *
 * @property data_ini Fecha de inicio de la recurrencia, en formato `"YYYY-MM-DD"`.
 * Esta fecha marca el punto de partida para el cálculo de futuras renovaciones.
 *
 * @property data_rnv Fecha de la próxima renovación del movimiento, en formato `"YYYY-MM-DD"`.
 * Esta fecha es actualizada por el sistema tras cada generación de movimiento.
 *
 * @property tipo Tipo de movimiento recurrente, representado por el enum [TypeMov]
 * (por ejemplo: *INGRESO* o *GASTO*). Puede ser `null` en casos excepcionales.
 *
 * @property remote_id Identificador remoto (PocketBase ID) del registro.
 * Es `null` si el movimiento recurrente aún no ha sido sincronizado con el servidor.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BQ\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\t\u0010\"\u001a\u00020\u0005H\u00c6\u0003J\t\u0010#\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\rH\u00c6\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J_\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010*\u001a\u00020\u0003H\u00d6\u0001J\t\u0010+\u001a\u00020\u0005H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0013\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014\u00a8\u0006,"}, d2 = {"Lcom/arcaneia/spendwise/data/entity/MovRecur;", "", "id", "", "nome", "", "importe", "", "periodicidade", "Lcom/arcaneia/spendwise/data/model/Recurrence;", "data_ini", "data_rnv", "tipo", "Lcom/arcaneia/spendwise/data/model/TypeMov;", "remote_id", "<init>", "(ILjava/lang/String;DLcom/arcaneia/spendwise/data/model/Recurrence;Ljava/lang/String;Ljava/lang/String;Lcom/arcaneia/spendwise/data/model/TypeMov;Ljava/lang/String;)V", "getId", "()I", "getNome", "()Ljava/lang/String;", "getImporte", "()D", "getPeriodicidade", "()Lcom/arcaneia/spendwise/data/model/Recurrence;", "getData_ini", "getData_rnv", "getTipo", "()Lcom/arcaneia/spendwise/data/model/TypeMov;", "getRemote_id", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "mov_recur")
public final class MovRecur {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final int id = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String nome = null;
    private final double importe = 0.0;
    @org.jetbrains.annotations.Nullable()
    private final com.arcaneia.spendwise.data.model.Recurrence periodicidade = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String data_ini = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String data_rnv = null;
    @org.jetbrains.annotations.Nullable()
    private final com.arcaneia.spendwise.data.model.TypeMov tipo = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String remote_id = null;
    
    public MovRecur(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String nome, double importe, @org.jetbrains.annotations.Nullable()
    com.arcaneia.spendwise.data.model.Recurrence periodicidade, @org.jetbrains.annotations.NotNull()
    java.lang.String data_ini, @org.jetbrains.annotations.NotNull()
    java.lang.String data_rnv, @org.jetbrains.annotations.Nullable()
    com.arcaneia.spendwise.data.model.TypeMov tipo, @org.jetbrains.annotations.Nullable()
    java.lang.String remote_id) {
        super();
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNome() {
        return null;
    }
    
    public final double getImporte() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.arcaneia.spendwise.data.model.Recurrence getPeriodicidade() {
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
    public final com.arcaneia.spendwise.data.model.TypeMov getTipo() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRemote_id() {
        return null;
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final double component3() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.arcaneia.spendwise.data.model.Recurrence component4() {
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
    public final com.arcaneia.spendwise.data.model.TypeMov component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.arcaneia.spendwise.data.entity.MovRecur copy(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String nome, double importe, @org.jetbrains.annotations.Nullable()
    com.arcaneia.spendwise.data.model.Recurrence periodicidade, @org.jetbrains.annotations.NotNull()
    java.lang.String data_ini, @org.jetbrains.annotations.NotNull()
    java.lang.String data_rnv, @org.jetbrains.annotations.Nullable()
    com.arcaneia.spendwise.data.model.TypeMov tipo, @org.jetbrains.annotations.Nullable()
    java.lang.String remote_id) {
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