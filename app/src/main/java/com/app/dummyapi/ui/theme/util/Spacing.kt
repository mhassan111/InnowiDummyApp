package com.app.dummyapi.ui.theme.util

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val mediumLarge: Dp = 24.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 64.dp,
    val xDp: Dp = 10.dp,
    val x2Dp: Dp = 20.dp,
    val x3Dp: Dp = 30.dp,
    val x4Dp: Dp = 40.dp,
    val x5Dp: Dp = 50.dp,
    val x6Dp: Dp = 60.dp,
    val x7Dp: Dp = 70.dp,
    val x8Dp: Dp = 80.dp,
    val x9Dp: Dp = 90.dp,
    val x10Dp: Dp = 100.dp,
    val x20Dp : Dp = 200.dp
)

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current
