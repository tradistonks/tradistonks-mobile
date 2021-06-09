package com.tradistonks.app.ui.theme

import android.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White

private val DarkColorPalette = darkColors(
        primary = colorPurple,
        primaryVariant = colorLightBlue,
        secondary = colorBlue,
        background = colorBackground,
        surface = colorBackground,
        onPrimary = colorPink,
        onSecondary = colorYellow,
        onBackground = textColor,
        onSurface = textColor,
)

private val LightColorPalette = lightColors(
        /*primary = Purple500,
        primaryVariant = Purple700,
        secondary = Teal200,
        background = Color.White,
        */

        /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun TradistonksAndroidTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = DarkColorPalette
    /*
    if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }*/

    MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}