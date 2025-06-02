package com.aamagon.regucars.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.aamagon.regucars.R

val LibreBaskervilleFamily = FontFamily(
    Font(R.font.librebaskerville_bold, FontWeight.Bold)
)

val WinkySansFamily = FontFamily(
    Font(R.font.winkysans_bold, FontWeight.Bold)
)

val ManropeFamily = FontFamily(
    Font(R.font.manrope_regular, FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = ManropeFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)