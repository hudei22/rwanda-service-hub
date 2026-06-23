package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = VibrantDarkPrimary,
    secondary = VibrantDarkSecondary,
    tertiary = VibrantDarkTertiary,
    background = VibrantDarkBg,
    surface = VibrantDarkSurface,
    onPrimary = VibrantDarkBg,
    onSecondary = VibrantAmberText,
    onTertiary = VibrantDarkBg,
    onBackground = VibrantLightBg,
    onSurface = VibrantLightSurface
)

private val LightColorScheme = lightColorScheme(
    primary = VibrantBluePrimary,
    secondary = VibrantAmberSecondary,
    tertiary = Color(0xFF008751), // Clean Kigali green prosperity indicator
    background = VibrantLightBg,
    surface = VibrantLightSurface,
    onPrimary = VibrantLightSurface,
    onSecondary = VibrantAmberText,
    onTertiary = VibrantLightSurface,
    onBackground = VibrantSlateDarkText,
    onSurface = VibrantSlateDarkText
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Locked to custom brand design
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
