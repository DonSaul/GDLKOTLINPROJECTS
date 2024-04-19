package com.oroz.noteappsoftserve.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    onTertiary = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Purple80,
    secondary = Color.Gray,
    onTertiary = Color.White

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MainTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        shapes = Shapes,
        content = content,
        typography = Typography,
        colorScheme = AppColors,
    )
}