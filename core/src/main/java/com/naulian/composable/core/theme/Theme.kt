package com.naulian.composable.core.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.naulian.modify.Black
import com.naulian.modify.DarkGray
import com.naulian.modify.LightGray
import com.naulian.modify.White
import com.naulian.neumorphic.NeumorphicTheme
import com.naulian.neumorphic.darkNeumorphicColorScheme
import com.naulian.neumorphic.lightNeumorphicColorScheme

private val DarkColorScheme = darkColorScheme(
    primary = DarkGreen,
    onPrimary = White,
    onPrimaryContainer = White,

    background = DarkBackground,
    onBackground = White,

    tertiary = DarkGray,

    surface = Black,
    onSurface = LightGray,
    surfaceContainer = DarkContainer,

    surfaceBright = DarkLight,
    surfaceDim = Shadow
)

private val LightColorScheme = lightColorScheme(
    primary = Green,
    onPrimary = White,
    onPrimaryContainer = Black,

    background = LightBackground,
    onBackground = DarkGray,

    tertiary = White,

    surface = White,
    onSurface = DarkGray,
    surfaceContainer = LightContainer,

    surfaceBright = Light,
    surfaceDim = Shadow,
)

@Composable
fun ComposableTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val neumorphicColorScheme = when {
        darkTheme -> darkNeumorphicColorScheme()
        else -> lightNeumorphicColorScheme()
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    NeumorphicTheme(
        neumorphicColorScheme = neumorphicColorScheme,
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}