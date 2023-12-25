package com.app.dummyapi.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.dummyapi.ui.theme.util.*
import com.app.dummyapi.ui.theme.util.LocalElevations

private val DummyAppThemeLight = lightColors(
    primary = SlateBlue,
    onPrimary = Color.White,
    primaryVariant = SlateBlueVariant,
    secondary = Green500,
    background = Color.White,
    surface = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val DummyAppThemeDark = darkColors(
    primary = SlateBlue,
    secondary = Color.Yellow,
    primaryVariant = SlateBlueVariant,
    background = Color.White,
    surface = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun DummyAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DummyAppThemeDark
    } else {
        DummyAppThemeLight
    }
    AppTheme(darkTheme = darkTheme, colors = colors, content)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppTheme(
    darkTheme: Boolean,
    colors: Colors,
    content: @Composable () -> Unit
) {
    val elevation = if (darkTheme) DarkElevation else LightElevation
    CompositionLocalProvider(
        LocalElevations provides elevation,
        LocalSpacing provides Spacing(),
        LocalSizes provides Sizes(),
        LocalOverscrollConfiguration provides null
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

private val LightElevation = Elevations()
private val DarkElevation = Elevations(card = 1.dp)