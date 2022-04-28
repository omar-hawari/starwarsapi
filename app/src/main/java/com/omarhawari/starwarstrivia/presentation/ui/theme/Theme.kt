package com.omarhawari.starwarstrivia.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

private val DarkColorPalette = darkColors(
    primary = STAR_WARS_YELLOW,
    primaryVariant = Color.White,
    secondary = LIGHT_SABER_RED,
    onError = LIGHT_SABER_RED_DARK
)

private val LightColorPalette = lightColors(
    primary = STAR_WARS_YELLOW,
    primaryVariant = Color.Black,
    secondary = LIGHT_SABER_RED_DARK,
    onError = LIGHT_SABER_RED_DARK
)

@Composable
fun StarWarsTriviaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = {
            ProvideTextStyle(
                value = TextStyle(color = colors.primaryVariant),
                content = content
            )
        }

    )
}