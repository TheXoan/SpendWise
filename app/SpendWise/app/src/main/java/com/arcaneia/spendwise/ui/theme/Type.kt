package com.arcaneia.spendwise.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.arcaneia.spendwise.R

val Inter = FontFamily(
    Font(R.font.inter_18pt_regular, FontWeight.Normal),
    Font(R.font.inter_18pt_medium, FontWeight.Medium),
    Font(R.font.inter_24pt_bold, FontWeight.Bold)
)

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
 * TYPES CUSTOM
 */

// Títulos grandes
val TitleTextStyle = SpendWiseTypography.displayLarge.copy(
    fontSize = 50.sp,
    letterSpacing = (-0.3).sp
)

// Subtítulo o encabezado secundario
val SubtitleTextStyle = SpendWiseTypography.titleLarge.copy(
    fontWeight = FontWeight.Medium,
    fontSize = 20.sp
)

val TitleTopBar = SpendWiseTypography.titleLarge.copy(
    fontWeight = FontWeight.Medium,
    fontSize = 20.sp
)


/**
 * Titulos BOX
 */
val TitleBox = SpendWiseTypography.displayMedium.copy(
    fontWeight = FontWeight.Medium,
    fontSize = 15.sp
)

val TextBoxBold = SpendWiseTypography.displayMedium.copy(
    fontWeight = FontWeight.Bold,
    fontSize = 15.sp
)

val TittleTopBox = SpendWiseTypography.labelSmall.copy(
    fontWeight = FontWeight.Medium,
    fontSize = 15.sp
)