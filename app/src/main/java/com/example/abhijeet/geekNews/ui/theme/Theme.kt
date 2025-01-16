package com.example.abhijeet.geekNews.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC), // Lighter purple for better readability
    secondary = Color(0xFF03DAC6), // Bright teal for accents
    tertiary = Color(0xFFFF8A65), // Warm orange for highlights
    background = Color(0xFF121212), // Typical dark mode background
    surface = Color(0xFF1E1E1E), // Slightly lighter than background
    onPrimary = Color.Black, // Text on primary
    onSecondary = Color.Black, // Text on secondary
    onTertiary = Color.Black, // Text on tertiary
    onBackground = Color.White, // Default text color
    onSurface = Color.White // Text on surface
)


private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE), // Deep purple for primary
    secondary = Color(0xFF018786), // Teal for secondary
    tertiary = Color(0xFFFF5722), // Orange for highlights
    background = Color(0xFFFFFFFF), // White background
    surface = Color(0xFFFAFAFA), // Very light grey for cards
    onPrimary = Color.White, // Text on primary
    onSecondary = Color.White, // Text on secondary
    onTertiary = Color.White, // Text on tertiary
    onBackground = Color.Black, // Default text color
    onSurface = Color.Black // Text on surface
)


@Composable
fun NewsNowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}