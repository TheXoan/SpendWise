package com.arcaneia.spendwise.apis.data.model;

/**
 * Data source encargado de gestionar las operaciones remotas relacionadas con las categorías.
 *
 * Esta clase se comunica con la API usando [RetrofitClient] para obtener, crear, actualizar
 * y eliminar categorías asociadas a un usuario. Requiere un token válido almacenado en
 * [TokenDataStore] para autenticar las solicitudes.
 *
 * @property context Context necesario para acceder al almacenamiento del token.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u0007H\u0082@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\rJ\u001e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001e\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/arcaneia/spendwise/apis/data/model/CategoriaRemoteDataSource;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "authHeader", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchAll", "", "Lcom/arcaneia/spendwise/apis/data/model/CategoriaRecord;", "userId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "categoria", "Lcom/arcaneia/spendwise/data/entity/Categoria;", "(Ljava/lang/String;Lcom/arcaneia/spendwise/data/entity/Categoria;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "categoriaPBId", "delete", "", "app_debug"})
public final class CategoriaRemoteDataSource {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    public CategoriaRemoteDataSource(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Obtiene el encabezado de autenticación necesario para las llamadas a la API.
     *
     * Recupera el token almacenado en [TokenDataStore] y construye el header de autorización
     * en formato `Bearer <token>`. Si no existe un token válido, se lanza una excepción.
     *
     * @return Cadena de texto con el header de autorización.
     * @throws IllegalArgumentException Si el token es nulo o está vacío.
     */
    private final java.lang.Object authHeader(kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    /**
     * Obtiene todas las categorías asociadas al usuario especificado.
     *
     * Realiza una solicitud GET a la API aplicando un filtro por `userId`.
     *
     * @param userId Identificador del usuario.
     * @return Lista de [CategoriaRecord] obtenidos desde la API.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchAll(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.arcaneia.spendwise.apis.data.model.CategoriaRecord>> $completion) {
        return null;
    }
    
    /**
     * Crea una nueva categoría asociada al usuario especificado.
     *
     * Envía los datos de la categoría a la API y devuelve el registro creado.
     *
     * @param userId Identificador del usuario propietario.
     * @param categoria Datos de la categoría a crear.
     * @return El registro creado como [CategoriaRecord].
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object create(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Categoria categoria, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.apis.data.model.CategoriaRecord> $completion) {
        return null;
    }
    
    /**
     * Actualiza los datos de una categoría existente.
     *
     * Envía un cuerpo con los valores modificados al endpoint correspondiente.
     *
     * @param categoriaPBId Identificador de la categoría en PocketBase.
     * @param categoria Nuevos datos de la categoría.
     * @return El registro actualizado como [CategoriaRecord].
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object update(@org.jetbrains.annotations.NotNull()
    java.lang.String categoriaPBId, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Categoria categoria, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.apis.data.model.CategoriaRecord> $completion) {
        return null;
    }
    
    /**
     * Elimina la categoría asociada al identificador proporcionado.
     *
     * Envía una solicitud DELETE a la API.
     *
     * @param categoriaPBId Identificador de la categoría en PocketBase.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    java.lang.String categoriaPBId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}