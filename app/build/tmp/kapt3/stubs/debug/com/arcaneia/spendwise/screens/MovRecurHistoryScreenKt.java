package com.arcaneia.spendwise.screens;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a(\u0010\u0006\u001a\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0007\u001a\u001e\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0010H\u0007\u001a2\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\t2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u0010H\u0007\u00a8\u0006\u0015"}, d2 = {"MovRecurHistoryScreen", "", "navController", "Landroidx/navigation/NavController;", "movRecurViewModel", "Lcom/arcaneia/spendwise/data/model/MovRecurViewModel;", "MovRecurList", "movsRecur", "", "Lcom/arcaneia/spendwise/data/entity/MovRecur;", "modifier", "Landroidx/compose/ui/Modifier;", "viewModel", "MovRecurItem", "mov", "onClick", "Lkotlin/Function0;", "EditarMovDialog", "onGuardar", "Lkotlin/Function1;", "onDismiss", "app_debug"})
public final class MovRecurHistoryScreenKt {
    
    /**
     * Pantalla que muestra la lista de movimientos recurrentes configurados por el usuario.
     *
     * Esta pantalla:
     * - Observa el flujo de movimientos recurrentes mediante [MovRecurViewModel].
     * - Muestra cada movimiento en una lista desplazable.
     * - Permite editar o eliminar un movimiento mediante un diálogo contextual.
     * - Permite crear nuevos movimientos recurrentes mediante un botón inferior.
     *
     * Navega hacia:
     * - `newMovRecur_screen` → pantalla de creación de movimientos recurrentes.
     *
     * @param navController Controlador de navegación.
     * @param movRecurViewModel ViewModel para gestionar los movimientos recurrentes.
     */
    @androidx.compose.runtime.Composable()
    public static final void MovRecurHistoryScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.MovRecurViewModel movRecurViewModel) {
    }
    
    /**
     * Lista de movimientos recurrentes con soporte para:
     * - Mostrar lista o mensaje vacío.
     * - Abrir menú para editar/eliminar.
     * - Abrir diálogo de edición.
     *
     * @param movsRecur Lista de movimientos recurrentes.
     * @param modifier Modificador opcional.
     * @param viewModel ViewModel encargado de las operaciones sobre la lista.
     */
    @androidx.compose.runtime.Composable()
    public static final void MovRecurList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.arcaneia.spendwise.data.entity.MovRecur> movsRecur, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.MovRecurViewModel viewModel) {
    }
    
    /**
     * Tarjeta que muestra la información principal de un movimiento recurrente.
     *
     * Muestra:
     * - Nombre
     * - Fecha de inicio
     * - Fecha de renovación
     * - Periodo (semanal/mensual/anual)
     * - Importe con color según tipo
     *
     * @param mov Objeto del movimiento recurrente.
     * @param onClick Acción al pulsar el elemento.
     */
    @android.annotation.SuppressLint(value = {"DefaultLocale"})
    @androidx.compose.runtime.Composable()
    public static final void MovRecurItem(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.MovRecur mov, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    /**
     * Diálogo completo para editar un movimiento recurrente.
     *
     * Permite modificar:
     * - Nombre
     * - Importe
     * - Fecha de inicio (DatePicker)
     * - Periodicidad (mediante [RecurrenceSpinner])
     * - Tipo de movimiento (mediante [TypeMovSpinner])
     *
     * La fecha de renovación se actualiza usando [calculateNextDate].
     *
     * @param mov Movimiento recurrente a editar.
     * @param onGuardar Acción que recibe el movimiento actualizado.
     * @param onDismiss Acción al cerrar el diálogo sin guardar.
     */
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void EditarMovDialog(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.entity.MovRecur mov, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.arcaneia.spendwise.data.entity.MovRecur, kotlin.Unit> onGuardar, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
}