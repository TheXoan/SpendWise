package com.arcaneia.spendwise.data.repository;

/**
 * Repositorio responsable de manejar todas las operaciones relacionadas con la entidad [Categoria].
 *
 * Esta clase actúa como una capa intermedia entre el *Data Access Object* ([CategoriaDao])
 * y el ViewModel correspondiente, proporcionando un punto de acceso único y estructurado para:
 *
 * - Insertar nuevas categorías.
 * - Actualizar y eliminar categorías existentes.
 * - Consultar la lista completa de categorías almacenadas.
 * - Buscar categorías por ID.
 *
 * El uso de un repositorio logra:
 * - Desacoplar las operaciones de acceso a datos de la lógica de UI.
 * - Facilitar la escalabilidad y el testeo.
 * - Centralizar la lógica de lectura y escritura en la base de datos Room.
 *
 * > **Nota:** Este repositorio trabaja exclusivamente con datos locales mediante Room.
 * > La sincronización remota con PocketBase es gestionada por clases específicas de sincronización.
 *
 * @property categoriaDao DAO encargado de ejecutar las operaciones sobre la tabla `categoria`.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\nJ\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000f0\u000eJ\u0016\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0015\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/arcaneia/spendwise/data/repository/CategoriaRepository;", "", "categoriaDao", "Lcom/arcaneia/spendwise/data/dao/CategoriaDao;", "<init>", "(Lcom/arcaneia/spendwise/data/dao/CategoriaDao;)V", "insert", "", "categoria", "Lcom/arcaneia/spendwise/data/entity/Categoria;", "(Lcom/arcaneia/spendwise/data/entity/Categoria;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "", "getAllCategories", "Lkotlinx/coroutines/flow/Flow;", "", "deleteById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "update", "app_debug"})
public final class CategoriaRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.arcaneia.spendwise.data.dao.CategoriaDao categoriaDao = null;
    
    public CategoriaRepository(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.dao.CategoriaDao categoriaDao) {
        super();
    }
    
    /**
     * Inserta una nueva categoría en la base de datos local.
     *
     * @param categoria Objeto [Categoria] que se desea insertar.
     * @return El ID autogenerado de la categoría insertada.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Categoria categoria, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    /**
     * Elimina una categoría especificada de la base de datos.
     *
     * @param categoria Entidad a eliminar.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Categoria categoria, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Obtiene un flujo reactivo con la lista completa de categorías almacenadas.
     *
     * Se excluye la categoría con ID 1 si así lo define el DAO, y los cambios se emiten
     * automáticamente a cualquier suscriptor cada vez que la tabla `categoria` es modificada.
     *
     * @return [Flow] que emite una lista de [Categoria].
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.arcaneia.spendwise.data.entity.Categoria>> getAllCategories() {
        return null;
    }
    
    /**
     * Elimina una categoría usando su ID local.
     *
     * @param id Identificador de la categoría.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Recupera una categoría a partir de su ID.
     *
     * @param id ID local de la categoría.
     * @return La categoría encontrada, o `null` si no existe.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.arcaneia.spendwise.data.entity.Categoria> $completion) {
        return null;
    }
    
    /**
     * Actualiza una categoría previamente registrada en la base de datos.
     *
     * @param categoria Entidad con los valores actualizados.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Categoria categoria, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}