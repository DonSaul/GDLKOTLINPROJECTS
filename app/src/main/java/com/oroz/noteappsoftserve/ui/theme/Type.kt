package com.oroz.noteappsoftserve.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import com.oroz.noteappsoftserve.R

// Set of Material typography styles to start with
val interFontFamily = FontFamily(
    Font(R.font.light, FontWeight.Light),
    Font(R.font.medium, FontWeight.Medium),
    Font(R.font.regular, FontWeight.Normal),
)

val lambdaTypography = FontFamily(
    Font(R.font.lambaregular)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontSize = 24.sp,
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Black,
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Normal,
        color = Color.Black
    ),
    labelLarge = TextStyle(
        fontSize = 14.sp,
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Normal,
    ),
)