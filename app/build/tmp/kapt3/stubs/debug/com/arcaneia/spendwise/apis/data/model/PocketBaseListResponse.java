package com.arcaneia.spendwise.apis.data.model;

/**
 * Representa una respuesta paginada estándar de PocketBase.
 *
 * Esta estructura se usa para envolver listas de elementos obtenidos desde la API,
 * proporcionando información de paginación junto con los datos retornados.
 *
 * @param T Tipo de elementos contenidos en la lista.
 * @property page Número de página actual.
 * @property perPage Cantidad de elementos por página.
 * @property totalItems Número total de elementos disponibles en el servidor.
 * @property items Lista de elementos de la página actual.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u00a2\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0004H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0004H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0004H\u00c6\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u00c6\u0003J=\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u0004H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001c"}, d2 = {"Lcom/arcaneia/spendwise/apis/data/model/PocketBaseListResponse;", "T", "", "page", "", "perPage", "totalItems", "items", "", "<init>", "(IIILjava/util/List;)V", "getPage", "()I", "getPerPage", "getTotalItems", "getItems", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
public final class PocketBaseListResponse<T extends java.lang.Object> {
    private final int page = 0;
    private final int perPage = 0;
    private final int totalItems = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<T> items = null;
    
    public PocketBaseListResponse(int page, int perPage, int totalItems, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends T> items) {
        super();
    }
    
    public final int getPage() {
        return 0;
    }
    
    public final int getPerPage() {
        return 0;
    }
    
    public final int getTotalItems() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<T> getItems() {
        return null;
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<T> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.arcaneia.spendwise.apis.data.model.PocketBaseListResponse<T> copy(int page, int perPage, int totalItems, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends T> items) {
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