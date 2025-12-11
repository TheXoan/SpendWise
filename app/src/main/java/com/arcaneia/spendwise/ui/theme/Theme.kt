package com.arcaneia.spendwise.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

/**
 * Conjunto de colores utilizado cuando el tema claro (Light Mode) está activo.
 *
 * Esta paleta define los colores principales del tema, asegurando
 * coherencia visual en todos los componentes Material 3.
 */
private val LightColors = lightColorScheme(
    primary = BluePrimary,
    secondary = BlueSecondary,
    tertiary = BlueTertiary
)

/**
 * Conjunto de colores utilizado en el tema oscuro (Dark Mode).
 *
 * Incluye colores base y un fondo personalizado negro para ofrecer
 * un contraste adecuado en entornos de poca luz.
 */
private val DarkColors = darkColorScheme(
    primary = DarkPrimary,
    secondary = DarkSecondary,
    tertiary = DarkTertiary,
    background = Color.Black
)

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
 *                  Por defecto está en `true`, aunque puede vincularse a
 *                  [isSystemInDarkTheme] si se quiere que siga el sistema.
 *
 * @param dynamicColor Habilita el uso de colores dinámicos (Material You)
 *                     siempre que el dispositivo lo soporte (Android 12+).
 *                     Actualmente está desactivado por defecto.
 *
 * @param content Contenido composable al que se le aplicará este tema.
 *
 * ### Notas:
 * - Si [dynamicColor] es `true`, el sistema genera automáticamente una paleta
 *   basada en el fondo de pantalla del usuario.
 * - El bloque que comenta el comportamiento dinámico está desactivado según
 *   el comentario del autor del código.
 */
@Composable
fun SpendWiseTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false, // Material You si está disponible
    content: @Composable () -> Unit
)
{
    val colorScheme = when {
        dynamicColor -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = SpendWiseTypography,
        content = content
    )
}