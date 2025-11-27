package com.arcaneia.spendwise.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Paleta de colores utilizada en el tema claro (Light Theme) de la aplicación.
 *
 * Estos colores pueden ser usados para elementos de UI como botones,
 * barras de navegación, fondos o acentos dentro del modo claro.
 */
val BluePrimary = Color(0xFF1565C0)
val BlueSecondary = Color(0xFF64B5F6)
val BlueTertiary = Color(0xFF82B1FF)

/**
 * Paleta de colores utilizada en el tema oscuro (Dark Theme).
 *
 * Incluye tonos neutros y fríos utilizados principalmente para fondos,
 * iconos o detalles cuando el modo oscuro está activado.
 */

// BLANCO
val DarkPrimary = Color(0xFF000000)

// ROSA
val DarkSecondary = Color(0xFFC4A3A3)

val DarkTertiary = Color(0xFF0D47A1)

/**
 * Colores personalizados utilizados a lo largo de la aplicación.
 * Contienen colores para fondos, cajas, botones, contenedores y detalles visuales.
 *
 * Esta sección agrupa tonalidades únicas que no forman parte directamente
 * del tema claro u oscuro, pero son esenciales para el diseño general.
 */

// Cajas y Botones
/** Fondo base para cajas, campos de texto y contenedores. */
val BackgroundBoxColorOne = Color(0xFFC7CAF1)

/** Fondo resaltado para cajas/inputs cuando están seleccionados o enfocados. */
val BackgroundBoxColorOneSelected = Color(0xFFD7D9F1)

/** Color utilizado en botones o indicadores de acción positiva (aceptar/guardar). */
val BackgroundBoxColorGreen = Color(0xFF3DB743)

/** Color utilizado para acciones de advertencia o eliminación. */
val BackgroundBoxColorRed = Color(0xFFF62525)

/** Fondo semitransparente utilizado en elementos de historial. */
val BackgroundBoxHistory = Color(0xA632374C)

/** Color de encabezados pequeños dentro de tarjetas o secciones superiores. */
val ColorTittleTopBox = Color(0xA6FFFFFF)

/** Fondo amarillo para botones destacados o categorías particulares. */
val BackgroundBoxCategory = Color(0xFFFFEB3B)

/**
 * Colores de subtítulos y textos secundarios.
 *
 * Usados para elementos menos relevantes visualmente pero necesarios para jerarquía.
 */
val SubtitleColor = Color(0xFFC4A3A3)
val SubtitleColorn2 = Color(0xFF9E9C9C)

/**
 * Colores para títulos principales de la interfaz.
 */
val TitleTopBarColor = Color(0xFF1F13B0)

/**
 * Colores específicos de la barra de navegación inferior.
 */
val SelectedItemBottomBar = Color(0xFF707ADB)

/**
 * Color predeterminado para textos de hint en campos de texto.
 */
val ColorHint = Color(0xFF8C8C8C)