package com.arcaneia.spendwise.components;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a:\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u00a8\u0006\b"}, d2 = {"EditarEliminar", "", "title", "", "onEdit", "Lkotlin/Function0;", "onDelete", "onDismiss", "app_debug"})
public final class DialogKt {
    
    /**
     * Muestra un cuadro de diálogo con opciones para **editar** o **eliminar**
     * un elemento, junto con la posibilidad de **cerrar** el diálogo.
     *
     * Este componente utiliza `AlertDialog` de Jetpack Compose y presenta un título,
     * un texto descriptivo y dos botones de acción principales.
     *
     * @param title Título que se mostrará en la parte superior del diálogo.
     * @param onEdit Acción que se ejecutará cuando el usuario presione el botón **Editar**.
     * @param onDelete Acción que se ejecutará cuando el usuario presione el botón **Eliminar**.
     * @param onDismiss Acción que se invoca cuando el diálogo se cierra sin seleccionar ninguna acción.
     */
    @androidx.compose.runtime.Composable()
    public static final void EditarEliminar(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onEdit, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
}