// Filename: Theme.kt
//
// This file contains the color selection used for the theme of the application

package com.cookingapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// sets the colors for the darkColorScheme
private val DarkColorPalette = darkColorScheme(
    primary = Green80,
    onPrimary = Green20,
    primaryContainer = Green30,
    onPrimaryContainer = Green90,
    inversePrimary = Green40,
    secondary = DarkGreen80,
    onSecondary = DarkGreen20,
    secondaryContainer = DarkGreen30,
    onSecondaryContainer = DarkGreen90,
    tertiary = Violet80,
    onTertiary = Violet20,
    tertiaryContainer = Violet30,
    onTertiaryContainer = Violet90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = Grey10,
    onBackground = Grey90,
    surface = GreenGrey30,
    onSurface = GreenGrey80,
    inverseSurface = Grey90,
    inverseOnSurface = Grey10,
    surfaceVariant = GreenGrey30,
    onSurfaceVariant = GreenGrey80,
    outline = GreenGrey80
)

// sets the colors for the lightColorScheme
private val LightColorPalette = lightColorScheme(
    primary = Green40,
    onPrimary = Color.White,
    primaryContainer = Green90,
    onPrimaryContainer = Green10,
    inversePrimary = Green80,
    secondary = DarkGreen40,
    onSecondary = Color.White,
    secondaryContainer = DarkGreen90,
    onSecondaryContainer = DarkGreen10,
    tertiary = Violet40,
    onTertiary = Color.White,
    tertiaryContainer = Violet90,
    onTertiaryContainer = Violet10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Grey99,
    onBackground = Grey10,
    surface = GreenGrey90,
    onSurface = GreenGrey30,
    inverseSurface = Grey20,
    inverseOnSurface = Grey95,
    surfaceVariant = GreenGrey90,
    onSurfaceVariant = GreenGrey30,
    outline = GreenGrey50

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

// Composable for the colorSchemes. Sets requirements to select theme being used
@Composable
fun CookingAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    // sets colors for theme based on Android version. Must meet minimum requirement due to
    // Dynamic coloring being used
    val useDynamicColors = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colorScheme = when { // when to decide color scheme
        useDynamicColors && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        useDynamicColors && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }
    // set values for theme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}