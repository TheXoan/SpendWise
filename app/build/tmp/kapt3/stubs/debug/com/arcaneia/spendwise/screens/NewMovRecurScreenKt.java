package com.arcaneia.spendwise.screens;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u00a8\u0006\u0006"}, d2 = {"NewMovRecurScreen", "", "navController", "Landroidx/navigation/NavController;", "movRecurViewModel", "Lcom/arcaneia/spendwise/data/model/MovRecurViewModel;", "app_debug"})
public final class NewMovRecurScreenKt {
    
    /**
     * Pantalla para crear un nuevo movimiento recurrente.
     *
     * Esta pantalla permite al usuario:
     * - Introducir importe, nombre, tipo y periodicidad.
     * - Seleccionar la fecha inicial mediante un DatePicker.
     * - Guardar el movimiento recurrente en la base de datos.
     *
     * Funcionalidad adicional:
     * - Solicita el permiso de notificaciones usando [PermissionManager].
     * - Calcula automáticamente la próxima fecha de renovación usando [calculateNextDate].
     *
     * @param navController Controlador de navegación para volver atrás tras guardar.
     * @param movRecurViewModel ViewModel encargado de gestionar los movimientos recurrentes.
     */
    @androidx.compose.runtime.Composable()
    public static final void NewMovRecurScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.MovRecurViewModel movRecurViewModel) {
    }
}