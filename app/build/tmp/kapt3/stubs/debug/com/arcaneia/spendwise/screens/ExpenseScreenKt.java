package com.arcaneia.spendwise.screens;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u00a8\u0006\b"}, d2 = {"ExpenseScreen", "", "navController", "Landroidx/navigation/NavController;", "movViewModel", "Lcom/arcaneia/spendwise/data/model/MovViewModel;", "categoriaViewModel", "Lcom/arcaneia/spendwise/data/model/CategoriaViewModel;", "app_debug"})
public final class ExpenseScreenKt {
    
    /**
     * Pantalla para registrar un nuevo gasto en la aplicación.
     *
     * Esta vista permite al usuario:
     * - Introducir un importe numérico.
     * - Seleccionar una categoría existente.
     * - Añadir una descripción opcional del gasto.
     * - Guardar el movimiento en la base de datos mediante [MovViewModel].
     *
     * La pantalla incorpora validaciones básicas:
     * - El importe solo admite números y decimales.
     * - La categoría es obligatoria.
     *
     * Una vez guardado el gasto, se muestra un `Toast` confirmando la operación
     * y se regresa a la pantalla anterior mediante `navController.popBackStack()`.
     *
     * @param navController Controlador de navegación para movernos entre pantallas.
     * @param movViewModel ViewModel responsable de manejar operaciones relacionadas con movimientos.
     * @param categoriaViewModel ViewModel para gestionar la lista de categorías disponibles.
     */
    @androidx.compose.runtime.Composable()
    public static final void ExpenseScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.MovViewModel movViewModel, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.CategoriaViewModel categoriaViewModel) {
    }
}