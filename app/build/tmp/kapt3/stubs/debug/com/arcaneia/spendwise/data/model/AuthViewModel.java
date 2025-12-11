package com.arcaneia.spendwise.data.model;

/**
 * ViewModel responsable de gestionar el estado de autenticación de la aplicación.
 *
 * Este ViewModel expone un estado observable que permite bloquear o permitir la
 * navegación en función de si el usuario ya pasó la pantalla de carga/autenticación.
 *
 * `isAuthenticated` se utiliza directamente en la `SplashScreen` para desencadenar
 * la navegación a la pantalla principal una vez que su valor se establece en `true`.
 *
 * @constructor Crea un [AuthViewModel] inicializando el estado como no autenticado.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006R*\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\f"}, d2 = {"Lcom/arcaneia/spendwise/data/model/AuthViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "value", "Landroidx/compose/runtime/MutableState;", "", "isAuthenticated", "()Landroidx/compose/runtime/MutableState;", "setAuthenticated", "", "success", "app_debug"})
public final class AuthViewModel extends androidx.lifecycle.ViewModel {
    
    /**
     * Estado observable que indica si el usuario está autenticado.
     *
     * - `false` → La app permanece en la SplashScreen.
     * - `true` → La app navega a la pantalla principal.
     *
     * Solo puede modificarse desde dentro del ViewModel, lo que expone un patrón
     * de encapsulación adecuado.
     */
    @org.jetbrains.annotations.NotNull()
    private androidx.compose.runtime.MutableState<java.lang.Boolean> isAuthenticated;
    
    public AuthViewModel() {
        super();
    }
    
    /**
     * Estado observable que indica si el usuario está autenticado.
     *
     * - `false` → La app permanece en la SplashScreen.
     * - `true` → La app navega a la pantalla principal.
     *
     * Solo puede modificarse desde dentro del ViewModel, lo que expone un patrón
     * de encapsulación adecuado.
     */
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.MutableState<java.lang.Boolean> isAuthenticated() {
        return null;
    }
    
    /**
     * Actualiza el estado de autenticación.
     *
     * Se invoca típicamente tras completar los procesos de verificación o carga
     * inicial de datos.
     *
     * @param success `true` si la autenticación/verificación fue exitosa.
     */
    public final void setAuthenticated(boolean success) {
    }
}