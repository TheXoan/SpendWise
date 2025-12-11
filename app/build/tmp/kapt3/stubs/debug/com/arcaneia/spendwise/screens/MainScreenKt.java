package com.arcaneia.spendwise.screens;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u00a8\u0006\u0006"}, d2 = {"MainScreen", "", "navController", "Landroidx/navigation/NavController;", "movViewModel", "Lcom/arcaneia/spendwise/data/model/MovViewModel;", "app_debug"})
public final class MainScreenKt {
    
    /**
     * Pantalla principal de la aplicación, encargada de mostrar el balance del mes
     * actual junto con accesos directos para registrar nuevos gastos e ingresos.
     *
     * Esta pantalla:
     * - Observa en tiempo real el balance mensual obtenido desde [MovViewModel].
     * - Muestra el nombre de la aplicación como cabecera.
     * - Presenta una tarjeta con el balance total de ingresos menos gastos del mes.
     * - Proporciona botones para navegar a las pantallas de:
     *  - Registro de gastos (`expense_screen`)
     *  - Registro de ingresos (`income_screen`)
     *
     * La pantalla está diseñada para ser el punto central de interacción del usuario,
     * ofreciendo una visión clara del estado financiero actual y acciones rápidas.
     *
     * @param navController Controlador de navegación para redirigir a otras pantallas.
     * @param movViewModel ViewModel utilizado para obtener el balance mensual.
     */
    @androidx.compose.runtime.Composable()
    public static final void MainScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.MovViewModel movViewModel) {
    }
}