package com.arcaneia.spendwise.data.model;

/**
 * ViewModel encargado de gestionar la lógica del proceso de inicio de sesión.
 *
 * Expone el estado necesario para la pantalla de login, incluyendo:
 * - Email y contraseña ingresados.
 * - Indicador de carga mientras se procesa la autenticación.
 * - Mensajes de error si ocurre un problema.
 * - Indicador de éxito cuando el login ha sido completado correctamente.
 *
 * Este ViewModel interactúa directamente con la API mediante [RetrofitClient]
 * y guarda la sesión del usuario en [TokenDataStore].
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"R+\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR+\u0010\r\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0010\u0010\f\u001a\u0004\b\u000e\u0010\b\"\u0004\b\u000f\u0010\nR+\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00118F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R/\u0010\u0017\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\f\u001a\u0004\b\u0018\u0010\b\"\u0004\b\u0019\u0010\nR+\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00118F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001e\u0010\f\u001a\u0004\b\u001c\u0010\u0013\"\u0004\b\u001d\u0010\u0015\u00a8\u0006#"}, d2 = {"Lcom/arcaneia/spendwise/data/model/LoginViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "<set-?>", "", "email", "getEmail", "()Ljava/lang/String;", "setEmail", "(Ljava/lang/String;)V", "email$delegate", "Landroidx/compose/runtime/MutableState;", "password", "getPassword", "setPassword", "password$delegate", "", "isLoading", "()Z", "setLoading", "(Z)V", "isLoading$delegate", "errorMessage", "getErrorMessage", "setErrorMessage", "errorMessage$delegate", "loginSuccess", "getLoginSuccess", "setLoginSuccess", "loginSuccess$delegate", "login", "", "context", "Landroid/content/Context;", "app_debug"})
public final class LoginViewModel extends androidx.lifecycle.ViewModel {
    
    /**
     * Email ingresado por el usuario en la pantalla de login.
     */
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState email$delegate = null;
    
    /**
     * Contraseña ingresada por el usuario.
     */
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState password$delegate = null;
    
    /**
     * Indica si se está procesando la solicitud de login.
     */
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState isLoading$delegate = null;
    
    /**
     * Contiene el mensaje de error a mostrar en caso de fallo.
     */
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState errorMessage$delegate = null;
    
    /**
     * Indica si el usuario logró autenticarse exitosamente.
     */
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState loginSuccess$delegate = null;
    
    public LoginViewModel() {
        super();
    }
    
    /**
     * Email ingresado por el usuario en la pantalla de login.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEmail() {
        return null;
    }
    
    /**
     * Email ingresado por el usuario en la pantalla de login.
     */
    public final void setEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    /**
     * Contraseña ingresada por el usuario.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPassword() {
        return null;
    }
    
    /**
     * Contraseña ingresada por el usuario.
     */
    public final void setPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    /**
     * Indica si se está procesando la solicitud de login.
     */
    public final boolean isLoading() {
        return false;
    }
    
    /**
     * Indica si se está procesando la solicitud de login.
     */
    public final void setLoading(boolean p0) {
    }
    
    /**
     * Contiene el mensaje de error a mostrar en caso de fallo.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getErrorMessage() {
        return null;
    }
    
    /**
     * Contiene el mensaje de error a mostrar en caso de fallo.
     */
    public final void setErrorMessage(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    /**
     * Indica si el usuario logró autenticarse exitosamente.
     */
    public final boolean getLoginSuccess() {
        return false;
    }
    
    /**
     * Indica si el usuario logró autenticarse exitosamente.
     */
    public final void setLoginSuccess(boolean p0) {
    }
    
    /**
     * Intenta iniciar sesión con las credenciales ingresadas.
     *
     * Flujo del proceso:
     * 1. Evita múltiples clics si ya se está cargando.
     * 2. Llama al endpoint de autenticación con el email y contraseña.
     * 3. Si la respuesta es correcta, guarda el token y el userId en [TokenDataStore].
     * 4. Si ocurre una excepción, establece un mensaje de error para la UI.
     * 5. Finaliza el estado de carga en el bloque `finally`.
     *
     * @param context Contexto necesario para acceder a recursos (strings) y DataStore.
     */
    public final void login(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
}