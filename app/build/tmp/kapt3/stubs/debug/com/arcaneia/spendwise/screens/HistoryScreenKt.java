package com.arcaneia.spendwise.screens;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u001a0\u0010\b\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u001e\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u000b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0012H\u0007\u001a:\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u00122\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u00a8\u0006\u0019"}, d2 = {"HistoryScreen", "", "navController", "Landroidx/navigation/NavController;", "movViewModel", "Lcom/arcaneia/spendwise/data/model/MovViewModel;", "categoriaViewModel", "Lcom/arcaneia/spendwise/data/model/CategoriaViewModel;", "HistoryList", "transacciones", "", "Lcom/arcaneia/spendwise/data/entity/MovWithCategory;", "modifier", "Landroidx/compose/ui/Modifier;", "viewModel", "TransaccionItem", "movWithCategory", "onClick", "Lkotlin/Function0;", "EditMovDialog", "mov", "Lcom/arcaneia/spendwise/data/entity/Mov;", "onGuardar", "Lkotlin/Function1;", "onDismiss", "app_debug"})
public final class HistoryScreenKt {
    
    /**
     * Pantalla que muestra el historial filtrado de movimientos económicos.
     *
     * Esta vista permite al usuario:
     * - Seleccionar un año y mes para filtrar los movimientos.
     * - Visualizar la lista de transacciones correspondientes.
     * - Editar o eliminar movimientos mediante un menú contextual.
     *
     * La pantalla observa distintos estados expuestos por [MovViewModel],
     * incluyendo la lista de años, meses y movimientos disponibles.
     *
     * @param navController Controlador de navegación.
     * @param movViewModel ViewModel encargado de gestionar movimientos.
     * @param categoriaViewModel ViewModel encargado de gestionar categorías.
     */
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void HistoryScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.MovViewModel movViewModel, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.CategoriaViewModel categoriaViewModel) {
    }
    
    /**
     * Lista de movimientos filtrados según los parámetros seleccionados.
     *
     * Permite:
     * - Mostrar un mensaje cuando no hay datos.
     * - Mostrar cada transacción usando [TransaccionItem].
     * - Abrir un menú contextual para editar o eliminar.
     * - Mostrar un diálogo para editar la transacción seleccionada.
     *
     * @param transacciones Lista de movimientos con categoría.
     * @param modifier Modificador para personalización externa.
     * @param viewModel ViewModel para realizar operaciones CRUD sobre movimientos.
     * @param categoriaViewModel ViewModel para gestionar categorías.
     */
    @androidx.compose.runtime.Composable()
    public static final void HistoryList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.arcaneia.spendwise.data.entity.MovWithCategory> transacciones, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.MovViewModel viewModel, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.CategoriaViewModel categoriaViewModel) {
    }
    
    /**
     * Elemento visual que representa un movimiento dentro de la lista del historial.
     *
     * Muestra:
     * - Descripción del movimiento
     * - Fecha formateada
     * - Categoría
     * - Importe con color verde (ingreso) o rojo (gasto)
     *
     * @param movWithCategory Movimiento junto con el nombre de su categoría.
     * @param onClick Acción al pulsar el elemento (abrir opciones).
     */
    @android.annotation.SuppressLint(value = {"DefaultLocale"})
    @androidx.compose.runtime.Composable()
    public static final void TransaccionItem(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.MovWithCategory movWithCategory, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    /**
     * Diálogo que permite editar un movimiento existente.
     *
     * Puede modificarse:
     * - Nombre del movimiento
     * - Importe
     * - Fecha (mediante DatePicker)
     * - Tipo (Ingreso/Gasto)
     * - Categoría
     *
     * @param mov Movimiento a editar.
     * @param onGuardar Callback que se ejecuta al guardar cambios.
     * @param onDismiss Acción al cerrar el diálogo sin guardar.
     * @param categoriaViewModel ViewModel para cargar categorías.
     */
    @android.annotation.SuppressLint(value = {"SimpleDateFormat"})
    @androidx.compose.runtime.Composable()
    public static final void EditMovDialog(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.Mov mov, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.arcaneia.spendwise.data.entity.Mov, kotlin.Unit> onGuardar, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.CategoriaViewModel categoriaViewModel) {
    }
}