package com.arcaneia.spendwise.apis;

/**
 * Interfaz Retrofit que define todos los endpoints utilizados para comunicarse con PocketBase.
 *
 * Esta API proporciona:
 *
 * - **Autenticación:** login mediante email y contraseña.
 * - **Gestión de categorías:** CRUD completo sobre la colección `categoria`.
 * - **Gestión de movimientos simples:** CRUD sobre la colección `mov`.
 * - **Gestión de movimientos recurrentes:** CRUD sobre la colección `mov_recur`.
 *
 * Cada función corresponde a un endpoint REST específico y requiere un token válido
 * enviado en la cabecera `Authorization` cuando se trabaja con recursos protegidos.
 *
 * Las funciones devuelven objetos modelo mapeados desde PocketBase:
 * - [CategoriaRecord]
 * - [MovRecord]
 * - [MovRecurRecord]
 *
 * Todas las respuestas listadas se encapsulan en [PocketBaseListResponse], el formato
 * estándar de paginación utilizado por PocketBase.
 *
 * Esta interfaz es utilizada por los data sources remotos para implementar la
 * sincronización con la base de datos local.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J*\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0001\u0010\n\u001a\u00020\u000b2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u000bH\u00a7@\u00a2\u0006\u0002\u0010\rJ5\u0010\u000e\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u001b\b\u0001\u0010\u000f\u001a\u0015\u0012\u0004\u0012\u00020\u000b\u0012\u000b\u0012\t\u0018\u00010\u0001\u00a2\u0006\u0002\b\u00110\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0012J?\u0010\u0013\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\u0014\u001a\u00020\u000b2\u001b\b\u0001\u0010\u000f\u001a\u0015\u0012\u0004\u0012\u00020\u000b\u0012\u000b\u0012\t\u0018\u00010\u0001\u00a2\u0006\u0002\b\u00110\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0015J\"\u0010\u0016\u001a\u00020\u00172\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\u0014\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\rJ*\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\b2\b\b\u0001\u0010\n\u001a\u00020\u000b2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u000bH\u00a7@\u00a2\u0006\u0002\u0010\rJ5\u0010\u001a\u001a\u00020\u00192\b\b\u0001\u0010\n\u001a\u00020\u000b2\u001b\b\u0001\u0010\u000f\u001a\u0015\u0012\u0004\u0012\u00020\u000b\u0012\u000b\u0012\t\u0018\u00010\u0001\u00a2\u0006\u0002\b\u00110\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0012J?\u0010\u001b\u001a\u00020\u00192\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\u0014\u001a\u00020\u000b2\u001b\b\u0001\u0010\u000f\u001a\u0015\u0012\u0004\u0012\u00020\u000b\u0012\u000b\u0012\t\u0018\u00010\u0001\u00a2\u0006\u0002\b\u00110\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0015J\"\u0010\u001c\u001a\u00020\u00172\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\u0014\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\rJ*\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\b2\b\b\u0001\u0010\n\u001a\u00020\u000b2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u000bH\u00a7@\u00a2\u0006\u0002\u0010\rJ5\u0010\u001f\u001a\u00020\u001e2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u001b\b\u0001\u0010\u000f\u001a\u0015\u0012\u0004\u0012\u00020\u000b\u0012\u000b\u0012\t\u0018\u00010\u0001\u00a2\u0006\u0002\b\u00110\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0012J?\u0010 \u001a\u00020\u001e2\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\u0014\u001a\u00020\u000b2\u001b\b\u0001\u0010\u000f\u001a\u0015\u0012\u0004\u0012\u00020\u000b\u0012\u000b\u0012\t\u0018\u00010\u0001\u00a2\u0006\u0002\b\u00110\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0015J\"\u0010!\u001a\u00020\u00172\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\u0014\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\r\u00a8\u0006\"\u00c0\u0006\u0003"}, d2 = {"Lcom/arcaneia/spendwise/apis/PocketBaseApi;", "", "login", "Lcom/arcaneia/spendwise/data/model/AuthResponse;", "request", "Lcom/arcaneia/spendwise/data/model/AuthRequest;", "(Lcom/arcaneia/spendwise/data/model/AuthRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCategorias", "Lcom/arcaneia/spendwise/apis/data/model/PocketBaseListResponse;", "Lcom/arcaneia/spendwise/apis/data/model/CategoriaRecord;", "auth", "", "filter", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createCategoria", "body", "", "Lkotlin/jvm/JvmSuppressWildcards;", "(Ljava/lang/String;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCategoria", "id", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCategoria", "", "getMov", "LMovRecord;", "createMov", "updateMov", "deleteMov", "getMovRecur", "Lcom/arcaneia/spendwise/apis/data/model/MovRecurRecord;", "createMovRecur", "updateMovRecur", "deleteMovRecur", "app_debug"})
public abstract interface PocketBaseApi {
    
    /**
     * Autentica un usuario mediante email y contraseña.
     *
     * Este endpoint utiliza la colección `users` de PocketBase y devuelve
     * un token de autenticación junto con la información del usuario.
     *
     * @param request Objeto que contiene `email` y `password`.
     * @return [AuthResponse] que incluye el token JWT y los datos del usuario.
     */
    @retrofit2.http.POST(value = "api/collections/users/auth-with-password")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.AuthRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.data.model.AuthResponse> $completion);
    
    /**
     * Obtiene una lista de categorías desde PocketBase.
     *
     * @param auth Token de autenticación en formato `"Bearer <token>"`.
     * @param filter Filtro opcional escrito en sintaxis de PocketBase.
     * @return Un objeto paginado con registros [CategoriaRecord].
     */
    @retrofit2.http.GET(value = "api/collections/categoria/records")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCategorias(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String auth, @retrofit2.http.Query(value = "filter")
    @org.jetbrains.annotations.Nullable()
    java.lang.String filter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.apis.data.model.PocketBaseListResponse<com.arcaneia.spendwise.apis.data.model.CategoriaRecord>> $completion);
    
    /**
     * Crea una nueva categoría en el servidor.
     *
     * @param auth Token de autenticación.
     * @param body Datos de la categoría como un mapa llave/valor.
     * @return El registro creado como [CategoriaRecord].
     */
    @retrofit2.http.POST(value = "api/collections/categoria/records")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createCategoria(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String auth, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Object> body, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.apis.data.model.CategoriaRecord> $completion);
    
    /**
     * Actualiza una categoría existente en PocketBase.
     *
     * @param auth Token de autenticación.
     * @param id Identificador remoto de la categoría.
     * @param body Mapa con los campos a modificar.
     * @return La categoría actualizada como [CategoriaRecord].
     */
    @retrofit2.http.PATCH(value = "api/collections/categoria/records/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateCategoria(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String auth, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Object> body, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.apis.data.model.CategoriaRecord> $completion);
    
    /**
     * Elimina una categoría remota.
     *
     * @param auth Token de autenticación.
     * @param id ID remoto de la categoría a borrar.
     */
    @retrofit2.http.DELETE(value = "api/collections/categoria/records/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteCategoria(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String auth, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Obtiene todos los movimientos simples desde PocketBase.
     *
     * @param auth Token de autenticación.
     * @param filter Filtro opcional.
     * @return Lista paginada de [MovRecord].
     */
    @retrofit2.http.GET(value = "api/collections/mov/records")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMov(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String auth, @retrofit2.http.Query(value = "filter")
    @org.jetbrains.annotations.Nullable()
    java.lang.String filter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.apis.data.model.PocketBaseListResponse<MovRecord>> $completion);
    
    /**
     * Crea un nuevo movimiento simple.
     *
     * @param auth Token de autenticación.
     * @param body Mapa con los datos del movimiento.
     * @return Registro creado como [MovRecord].
     */
    @retrofit2.http.POST(value = "api/collections/mov/records")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createMov(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String auth, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Object> body, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super MovRecord> $completion);
    
    /**
     * Actualiza un movimiento simple existente.
     *
     * @param auth Token de autenticación.
     * @param id ID remoto del movimiento.
     * @param body Campos modificados.
     * @return Movimiento actualizado como [MovRecord].
     */
    @retrofit2.http.PATCH(value = "api/collections/mov/records/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateMov(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String auth, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Object> body, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super MovRecord> $completion);
    
    /**
     * Elimina un movimiento simple remoto.
     *
     * @param auth Token de autenticación.
     * @param id Identificador remoto del movimiento.
     */
    @retrofit2.http.DELETE(value = "api/collections/mov/records/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteMov(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String auth, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Obtiene la lista de movimientos recurrentes desde PocketBase.
     *
     * @param auth Token de autenticación en formato Bearer.
     * @param filter Filtro de servidor opcional.
     * @return Respuesta paginada con registros [MovRecurRecord].
     */
    @retrofit2.http.GET(value = "api/collections/mov_recur/records")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMovRecur(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String auth, @retrofit2.http.Query(value = "filter")
    @org.jetbrains.annotations.Nullable()
    java.lang.String filter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.apis.data.model.PocketBaseListResponse<com.arcaneia.spendwise.apis.data.model.MovRecurRecord>> $completion);
    
    /**
     * Crea un nuevo movimiento recurrente en PocketBase.
     *
     * @param auth Token de autenticación.
     * @param body Datos del movimiento recurrente como mapa.
     * @return Registro creado como [MovRecurRecord].
     */
    @retrofit2.http.POST(value = "api/collections/mov_recur/records")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createMovRecur(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String auth, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Object> body, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.apis.data.model.MovRecurRecord> $completion);
    
    /**
     * Actualiza un movimiento recurrente existente.
     *
     * @param auth Token de autenticación.
     * @param id ID remoto del movimiento recurrente.
     * @param body Campos a modificar.
     * @return El registro actualizado como [MovRecurRecord].
     */
    @retrofit2.http.PATCH(value = "api/collections/mov_recur/records/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateMovRecur(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String auth, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Object> body, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.apis.data.model.MovRecurRecord> $completion);
    
    /**
     * Elimina un movimiento recurrente de PocketBase.
     *
     * @param auth Token de autenticación.
     * @param id ID remoto del registro a eliminar.
     */
    @retrofit2.http.DELETE(value = "api/collections/mov_recur/records/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteMovRecur(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String auth, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Interfaz Retrofit que define todos los endpoints utilizados para comunicarse con PocketBase.
     *
     * Esta API proporciona:
     *
     * - **Autenticación:** login mediante email y contraseña.
     * - **Gestión de categorías:** CRUD completo sobre la colección `categoria`.
     * - **Gestión de movimientos simples:** CRUD sobre la colección `mov`.
     * - **Gestión de movimientos recurrentes:** CRUD sobre la colección `mov_recur`.
     *
     * Cada función corresponde a un endpoint REST específico y requiere un token válido
     * enviado en la cabecera `Authorization` cuando se trabaja con recursos protegidos.
     *
     * Las funciones devuelven objetos modelo mapeados desde PocketBase:
     * - [CategoriaRecord]
     * - [MovRecord]
     * - [MovRecurRecord]
     *
     * Todas las respuestas listadas se encapsulan en [PocketBaseListResponse], el formato
     * estándar de paginación utilizado por PocketBase.
     *
     * Esta interfaz es utilizada por los data sources remotos para implementar la
     * sincronización con la base de datos local.
     */
    @kotlin.Metadata(mv = {2, 2, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}