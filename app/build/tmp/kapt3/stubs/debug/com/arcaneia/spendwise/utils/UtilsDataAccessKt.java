package com.arcaneia.spendwise.utils;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aP\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00052\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007\u001a,\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\n2\u0006\u0010\u0012\u001a\u00020\u0013H\u0007\u00a8\u0006\u0014"}, d2 = {"ComboBoxHistory", "", "modifier", "Landroidx/compose/ui/Modifier;", "label", "", "options", "", "selected", "onSelected", "Lkotlin/Function1;", "enabled", "", "ComboBoxCategorias", "viewModel", "Lcom/arcaneia/spendwise/data/model/CategoriaViewModel;", "onCategoriaSeleccionada", "", "internalShape", "Landroidx/compose/ui/graphics/Shape;", "app_debug"})
public final class UtilsDataAccessKt {
    
    /**
     * ComboBox reutilizable para la selección de **años o meses** dentro de la pantalla de historial.
     *
     * Este componente es genérico y permite recibir una lista de valores de tipo `String`, que pueden
     * representar tanto años (e.g., `"2023"`, `"2024"`) como meses numéricos (e.g., `"01"`, `"02"`).
     *
     * ## Características principales
     * - Traducción automática de números de mes a su nombre correspondiente según el
     *  **idioma del dispositivo** utilizando `Locale.getDefault()`.
     * - Detección inteligente:
     *  - Si el valor está entre **1 y 12**, se interpreta como mes y se traduce.
     *  - Si el valor no representa un mes válido (e.g. un año), se muestra tal cual.
     * - Componente visual estilizado según el tema de la aplicación.
     * - Manejo interno del estado de expansión del menú desplegable.
     * - Admite modo deshabilitado (`enabled = false`).
     *
     * ## Parámetros
     * @param modifier Modificador opcional para personalizar la apariencia externa del componente.
     * @param label Texto mostrado como placeholder cuando no hay selección.
     * @param options Lista de elementos a mostrar en el menú desplegable. Pueden ser meses o años.
     * @param selected Elemento actualmente seleccionado. Puede ser nulo.
     * @param onSelected Callback invocado cuando el usuario selecciona un valor. Devuelve el valor elegido.
     * @param enabled Permite habilitar o deshabilitar la interacción con el ComboBox.
     */
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ComboBoxHistory(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    java.lang.String label, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> options, @org.jetbrains.annotations.Nullable()
    java.lang.String selected, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSelected, boolean enabled) {
    }
    
    /**
     * ComboBox especializado para mostrar y seleccionar una **categoría** proveniente del
     * `CategoriaViewModel`.
     *
     * Este componente observa automáticamente el flujo de categorías y muestra todas las disponibles.
     * Al seleccionar una categoría, se devuelve su **ID**, permitiendo integrar fácilmente esta
     * selección dentro de la lógica del ViewModel o de la capa de datos.
     *
     * ## Características principales
     * - Lectura automática de categorías desde un `StateFlow` del `CategoriaViewModel`.
     * - Muestra el nombre de cada categoría y mantiene el estado de selección internamente.
     * - Devuelve únicamente el **ID de la categoría** seleccionada.
     * - Estilizado acorde al tema de la interfaz.
     * - Permite personalizar la forma del campo mediante `internalShape`.
     *
     * ## Parámetros
     * @param viewModel Instancia del `CategoriaViewModel` desde donde se obtienen las categorías.
     * @param onCategoriaSeleccionada Callback invocado al seleccionar una categoría, devolviendo su ID.
     * @param internalShape Forma personalizada (e.g., `RoundedCornerShape`) para el campo de texto.
     */
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ComboBoxCategorias(@org.jetbrains.annotations.NotNull()
    com.arcaneia.spendwise.data.model.CategoriaViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onCategoriaSeleccionada, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.graphics.Shape internalShape) {
    }
}