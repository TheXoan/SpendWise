package com.arcaneia.spendwise.screens;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u00a8\u0006\b"}, d2 = {"IncomeScreen", "", "navController", "Landroidx/navigation/NavController;", "movViewModel", "Lcom/arcaneia/spendwise/data/model/MovViewModel;", "categoriaViewModel", "Lcom/arcaneia/spendwise/data/model/CategoriaViewModel;", "app_debug"})
public final class IncomeScreenKt {
    
    /**
     * Pantalla encargada de registrar un nuevo ingreso en la aplicación.
     *
     * Esta vista permite al usuario:
     * - Introducir un importe numérico.
     * - Seleccionar una categoría existente.
     * - Añadir una descripción opcional.
     * - Guardar el ingreso utilizando [MovViewModel].
     *
     * La pantalla aplica validaciones básicas:
     * - Solo se permiten números y decimales en el importe.
     * - La categoría es obligatoria.
     *
     * Una vez guardado el ingreso:
     * - Se muestra un mensaje `Toast` confirmando la operación.
     * - Se vuelve a la pantalla anterior usando `navController.popBackStack()`.
     *
     * Para evitar dobles inserciones accidentales, se usa la bandera `isSaving`
     * que deshabilita múltiples pulsaciones del botón.
     *
     * @param navController Controlador de navegación para cambiar de pantallas.
     * @param movViewModel ViewModel encargado de gestionar movimientos.
     * @param categoriaViewModel ViewModel encargado de cargar las categorías existentes.
     */
    @androidx.compose.runtime.Composable()
    public static final void IncomeScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.MovViewModel movViewModel, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.CategoriaViewModel categoriaViewModel) {
    }
}