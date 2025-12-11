package com.arcaneia.spendwise.screens;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u00a8\u0006\u0006"}, d2 = {"CategoryScreen", "", "navController", "Landroidx/navigation/NavController;", "categoriaViewModel", "Lcom/arcaneia/spendwise/data/model/CategoriaViewModel;", "app_debug"})
public final class CategoryScreenKt {
    
    /**
     * Pantalla encargada de gestionar las categorías de la aplicación.
     *
     * Permite:
     * - Visualizar las categorías existentes mediante un combo box.
     * - Crear nuevas categorías.
     * - Actualizar categorías seleccionadas.
     * - Eliminar categorías.
     *
     * Esta pantalla utiliza `CategoriaViewModel` para interactuar con la capa de datos,
     * aunque también recurre directamente al DAO cuando es necesario (actualización e inserción).
     *
     * Elementos principales de la UI:
     * - Campo para seleccionar una categoría existente (selector).
     * - Campo de texto para ingresar o modificar el nombre de la categoría.
     * - Botón para guardar (crear o actualizar).
     * - Botón para eliminar la categoría seleccionada.
     *
     * @param navController Controlador de navegación para cambiar de pantallas.
     * @param categoriaViewModel ViewModel encargado de gestionar la lógica de categorías.
     */
    @androidx.compose.runtime.Composable()
    public static final void CategoryScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.CategoriaViewModel categoriaViewModel) {
    }
}