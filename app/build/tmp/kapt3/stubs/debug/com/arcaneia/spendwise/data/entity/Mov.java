package com.arcaneia.spendwise.data.entity;

/**
 * Entidad que representa un movimiento económico individual dentro de la aplicación.
 *
 * Un **movimiento** puede ser un ingreso o un gasto, estar asociado a una categoría
 * y opcionalmente provenir de un movimiento recurrente.
 *
 * Esta entidad integra tanto datos locales como referencias para sincronización remota
 * con PocketBase.
 *
 * ---
 * ### 🔗 Relaciones con otras entidades
 *
 * Se definen dos claves foráneas:
 *
 * 1. **`categoria_id`** → referencia a [Categoria]
 *   - `CASCADE`: si se elimina una categoría, también se eliminan sus movimientos.
 *
 * 2. **`mov_recur_id`** → referencia a [MovRecur]
 *   - `SET_NULL`: si se elimina el movimiento recurrente, el movimiento simple permanece,
 *     pero deja de estar vinculado a esa recurrencia.
 *
 * ---
 * ### ⚡ Índices
 * La entidad define índices en:
 * - `categoria_id`
 * - `mov_recur_id`
 *
 * Esto optimiza:
 * - consultas con JOIN,
 * - filtros por categoría o recurrencia,
 * - validación de claves foráneas.
 *
 * ---
 * ### 🌐 Sincronización remota (PocketBase)
 *
 * Los campos:
 * - `remote_id`
 * - `renew_hash`
 * - `notificado`
 *
 * permiten:
 * - identificar el registro remoto asociado,
 * - evitar duplicados generados por renovaciones,
 * - controlar qué movimientos deben generar notificaciones locales.
 *
 * ---
 * @property id
 * ID autogenerado del movimiento en la base de datos local.
 *
 * @property tipo
 * Tipo de movimiento ([TypeMov]): INGRESO o GASTO. Puede ser `null` en casos especiales.
 *
 * @property importe
 * Cantidad económica del movimiento.
 *
 * @property data_mov
 * Fecha del movimiento en formato `"YYYY-MM-DD"` (o `"YYYY-MM-DD HH:mm:ss"` si se usa con hora).
 *
 * @property descricion
 * Descripción opcional del movimiento.
 *
 * @property categoria_id
 * ID local de la categoría asociada (clave foránea a [Categoria]).
 *
 * @property mov_recur_id
 * ID local del movimiento recurrente que originó este movimiento, o `null` si no es recurrente.
 *
 * @property remote_id
 * ID remoto en PocketBase. Si es `null`, aún no ha sido sincronizado.
 *
 * @property renew_hash
 * Hash único usado para evitar duplicados entre dispositivos
 * cuando se generan movimientos recurrentes automáticamente.
 *
 * @property notificado
 * Indica si este movimiento ya fue notificado localmente.
 * Utilizado para evitar notificaciones repetidas.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b$\b\u0087\b\u0018\u00002\u00020\u0001Bm\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0012J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010&\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\'\u001a\u00020\tH\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010*\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001eJ\u000b\u0010+\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\t\u0010-\u001a\u00020\u0010H\u00c6\u0003J|\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u00c6\u0001\u00a2\u0006\u0002\u0010/J\u0013\u00100\u001a\u00020\u00102\b\u00101\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00102\u001a\u00020\u0003H\u00d6\u0001J\t\u00103\u001a\u00020\tH\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\n\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0015\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\r\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#\u00a8\u00064"}, d2 = {"Lcom/arcaneia/spendwise/data/entity/Mov;", "", "id", "", "tipo", "Lcom/arcaneia/spendwise/data/model/TypeMov;", "importe", "", "data_mov", "", "descricion", "categoria_id", "mov_recur_id", "remote_id", "renew_hash", "notificado", "", "<init>", "(ILcom/arcaneia/spendwise/data/model/TypeMov;DLjava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Z)V", "getId", "()I", "getTipo", "()Lcom/arcaneia/spendwise/data/model/TypeMov;", "getImporte", "()D", "getData_mov", "()Ljava/lang/String;", "getDescricion", "getCategoria_id", "getMov_recur_id", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getRemote_id", "getRenew_hash", "getNotificado", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(ILcom/arcaneia/spendwise/data/model/TypeMov;DLjava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Z)Lcom/arcaneia/spendwise/data/entity/Mov;", "equals", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "mov", foreignKeys = {@androidx.room.ForeignKey(entity = com.arcaneia.spendwise.data.entity.Categoria.class, parentColumns = {"id"}, childColumns = {"categoria_id"}, onDelete = 5), @androidx.room.ForeignKey(entity = com.arcaneia.spendwise.data.entity.MovRecur.class, parentColumns = {"id"}, childColumns = {"mov_recur_id"}, onDelete = 3)}, indices = {@androidx.room.Index(value = {"categoria_id"}), @androidx.room.Index(value = {"mov_recur_id"})})
public final class Mov {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final int id = 0;
    @org.jetbrains.annotations.Nullable()
    private final com.arcaneia.spendwise.data.model.TypeMov tipo = null;
    private final double importe = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String data_mov = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String descricion = null;
    private final int categoria_id = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer mov_recur_id = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String remote_id = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String renew_hash = null;
    private final boolean notificado = false;
    
    public Mov(int id, @org.jetbrains.annotations.Nullable()
    com.arcaneia.spendwise.data.model.TypeMov tipo, double importe, @org.jetbrains.annotations.NotNull()
    java.lang.String data_mov, @org.jetbrains.annotations.Nullable()
    java.lang.String descricion, int categoria_id, @org.jetbrains.annotations.Nullable()
    java.lang.Integer mov_recur_id, @org.jetbrains.annotations.Nullable()
    java.lang.String remote_id, @org.jetbrains.annotations.Nullable()
    java.lang.String renew_hash, boolean notificado) {
        super();
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.arcaneia.spendwise.data.model.TypeMov getTipo() {
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
    
    public final int getCategoria_id() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getMov_recur_id() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRemote_id() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRenew_hash() {
        return null;
    }
    
    public final boolean getNotificado() {
        return false;
    }
    
    public final int component1() {
        return 0;
    }
    
    public final boolean component10() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.arcaneia.spendwise.data.model.TypeMov component2() {
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
    
    public final int component6() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.arcaneia.spendwise.data.entity.Mov copy(int id, @org.jetbrains.annotations.Nullable()
    com.arcaneia.spendwise.data.model.TypeMov tipo, double importe, @org.jetbrains.annotations.NotNull()
    java.lang.String data_mov, @org.jetbrains.annotations.Nullable()
    java.lang.String descricion, int categoria_id, @org.jetbrains.annotations.Nullable()
    java.lang.Integer mov_recur_id, @org.jetbrains.annotations.Nullable()
    java.lang.String remote_id, @org.jetbrains.annotations.Nullable()
    java.lang.String renew_hash, boolean notificado) {
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