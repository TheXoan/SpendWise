package com.arcaneia.spendwise.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.arcaneia.spendwise.R

/**
 * Familia tipográfica principal utilizada en toda la aplicación.
 *
 * Se basa en la fuente **Inter**, cargada manualmente desde `res/font`.
 * Se definen tres pesos:
 * - Normal
 * - Medium
 * - Bold
 *
 * Esta familia es reutilizada en todos los estilos de texto de la app.
 */
val Inter = FontFamily(
    Font(R.font.inter_18pt_regular, FontWeight.Normal),
    Font(R.font.inter_18pt_medium, FontWeight.Medium),
    Font(R.font.inter_24pt_bold, FontWeight.Bold)
)

/**
 * Tipografía base del sistema Material 3 para la aplicación SpendWise.
 *
 * Aquí se redefinen algunos estilos principales:
 * - displayLarge
 * - displayMedium
 * - titleLarge
 * - bodyLarge
 * - labelSmall
 *
 * Estos estilos se utilizan como base y luego se especializan en estilos
 * personalizados más abajo.
 */
val SpendWiseTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        letterSpacing = (-0.5).sp
    ),
    displayMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    )
)

/**
 * ==============================
 * TIPOGRAFÍAS PERSONALIZADAS
 * ==============================
 * Estos estilos se utilizan para títulos, cajas y elementos
 * visuales importantes de la interfaz.
 */

/**
 * Títulos grandes, utilizados en pantallas principales como Splash o MainScreen.
 */
val TitleTextStyle = SpendWiseTypography.displayLarge.copy(
    fontSize = 50.sp,
    letterSpacing = (-0.3).sp
)

/**
 * Títulos medianos como subtítulos destacados o encabezados de secciones importantes.
 */
val TitleTextLittle = SpendWiseTypography.displayLarge.copy(
    fontSize = 27.sp,
    letterSpacing = (-0.3).sp
)

/**
 * Subtítulos o encabezados secundarios coherentes con el estilo de la app.
 */
val SubtitleTextStyle = SpendWiseTypography.titleLarge.copy(
    fontWeight = FontWeight.Medium,
    fontSize = 20.sp
)

/**
 * Títulos superiores utilizados en barras superiores o pantallas con encabezado.
 */
val TitleTopBar = SpendWiseTypography.titleLarge.copy(
    fontWeight = FontWeight.Medium,
    fontSize = 20.sp
)

/**
 * Texto de los títulos dentro de Box o contenedores UI.
 */
val TitleBox = SpendWiseTypography.displayMedium.copy(
    fontWeight = FontWeight.Medium,
    fontSize = 15.sp
)

/**
 * Variante en negrita para resaltar texto dentro de cajas.
 */
val TextBoxBold = SpendWiseTypography.displayMedium.copy(
    fontWeight = FontWeight.Bold,
    fontSize = 15.sp
)

/**
 * Títulos pequeños usados en secciones secundarias o labels superiores.
 */
val TittleTopBox = SpendWiseTypography.labelSmall.copy(
    fontWeight = FontWeight.Medium,
    fontSize = 15.sp
)