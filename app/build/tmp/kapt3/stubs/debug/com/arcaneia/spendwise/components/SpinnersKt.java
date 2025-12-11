package com.arcaneia.spendwise.components;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a&\u0010\u0006\u001a\u00020\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u00a8\u0006\n"}, d2 = {"RecurrenceSpinner", "", "selectedRecurrence", "Lcom/arcaneia/spendwise/data/model/Recurrence;", "onRecurrenceSelected", "Lkotlin/Function1;", "TypeMovSpinner", "selectedTypeMov", "Lcom/arcaneia/spendwise/data/model/TypeMov;", "onSelectedTypeMov", "app_debug"})
public final class SpinnersKt {
    
    /**
     * Componente que muestra un selector desplegable (`Spinner`) para elegir una opción
     * de la enumeración [Recurrence].
     *
     * El botón principal muestra la periodicidad actualmente seleccionada o un texto
     * predeterminado si no se ha seleccionado ninguna. Al hacer clic, se despliega
     * un menú con todas las opciones disponibles.
     *
     * @param selectedRecurrence Valor actualmente seleccionado de tipo [Recurrence], o `null`
     * si aún no hay selección.
     * @param onRecurrenceSelected Callback que se ejecuta cuando el usuario selecciona
     * una opción del menú, devolviendo la instancia seleccionada.
     */
    @androidx.compose.runtime.Composable()
    public static final void RecurrenceSpinner(@org.jetbrains.annotations.Nullable()
    com.arcaneia.spendwise.data.model.Recurrence selectedRecurrence, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.arcaneia.spendwise.data.model.Recurrence, kotlin.Unit> onRecurrenceSelected) {
    }
    
    /**
     * Componente que muestra un selector desplegable (`Spinner`) para elegir una opción
     * de la enumeración [TypeMov].
     *
     * El botón principal muestra el tipo de movimiento actualmente seleccionado o un texto
     * predeterminado si aún no hay selección. Al hacer clic, se despliega un menú con todas
     * las opciones disponibles.
     *
     * @param selectedTypeMov Valor actualmente seleccionado de tipo [TypeMov], o `null`
     * si aún no hay selección.
     * @param onSelectedTypeMov Callback que se ejecuta cuando el usuario selecciona
     * una opción del menú, devolviendo la instancia seleccionada.
     */
    @androidx.compose.runtime.Composable()
    public static final void TypeMovSpinner(@org.jetbrains.annotations.Nullable()
    com.arcaneia.spendwise.data.model.TypeMov selectedTypeMov, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.arcaneia.spendwise.data.model.TypeMov, kotlin.Unit> onSelectedTypeMov) {
    }
}