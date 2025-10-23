package com.arcaneia.spendwise.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary = BluePrimary,
    secondary = BlueSecondary,
    tertiary = BlueTertiary
)

private val DarkColors = darkColorScheme(
    primary = DarkPrimary,
    secondary = DarkSecondary,
    tertiary = DarkTertiary,
)

@Composable
fun SpendWiseTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false, // Material You si está disponible
    content: @Composable () -> Unit
)
        /**
         * Cambia de forma dinámica el tema y la paleta de colores según el que tengamos habilitado en el dispositivo
         *  en función de un tema generado automáticamente Material you.
         *  #######DISABLED
         *  darkTheme: Boolean = isSystemInDarkTheme()
         */
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