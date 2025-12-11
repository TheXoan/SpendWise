package com.arcaneia.spendwise.ui.theme;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a/\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00040\t\u00a2\u0006\u0002\b\nH\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"LightColors", "Landroidx/compose/material3/ColorScheme;", "DarkColors", "SpendWiseTheme", "", "darkTheme", "", "dynamicColor", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "app_debug"})
public final class ThemeKt {
    
    /**
     * Conjunto de colores utilizado cuando el tema claro (Light Mode) está activo.
     *
     * Esta paleta define los colores principales del tema, asegurando
     * coherencia visual en todos los componentes Material 3.
     */
    @org.jetbrains.annotations.NotNull()
    private static final androidx.compose.material3.ColorScheme LightColors = null;
    
    /**
     * Conjunto de colores utilizado en el tema oscuro (Dark Mode).
     *
     * Incluye colores base y un fondo personalizado negro para ofrecer
     * un contraste adecuado en entornos de poca luz.
     */
    @org.jetbrains.annotations.NotNull()
    private static final androidx.compose.material3.ColorScheme DarkColors = null;
    
    /**
     * Tema principal de la aplicación **SpendWise** basado en Material 3.
     *
     * Esta función:
     * - Aplica automáticamente el tema claro u oscuro según el parámetro [darkTheme].
     * - Permite habilitar *Material You* dinámico en Android 12+ mediante [dynamicColor].
     * - Utiliza los esquemas de color definidos en [LightColors] y [DarkColors].
     * - Aplica la tipografía personalizada mediante [SpendWiseTypography].
     *
     * @param darkTheme Indica si debe usarse el tema oscuro.
     *                 Por defecto está en `true`, aunque puede vincularse a
     *                 [isSystemInDarkTheme] si se quiere que siga el sistema.
     *
     * @param dynamicColor Habilita el uso de colores dinámicos (Material You)
     *                    siempre que el dispositivo lo soporte (Android 12+).
     *                    Actualmente está desactivado por defecto.
     *
     * @param content Contenido composable al que se le aplicará este tema.
     *
     * ### Notas:
     * - Si [dynamicColor] es `true`, el sistema genera automáticamente una paleta
     *  basada en el fondo de pantalla del usuario.
     * - El bloque que comenta el comportamiento dinámico está desactivado según
     *  el comentario del autor del código.
     */
    @androidx.compose.runtime.Composable()
    public static final void SpendWiseTheme(boolean darkTheme, boolean dynamicColor, @org.jetbrains.annotations.NotNull()
    androidx.compose.runtime.internal.ComposableFunction0<kotlin.Unit> content) {
    }
}