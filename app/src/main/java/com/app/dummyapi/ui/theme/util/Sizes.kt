package com.app.dummyapi.ui.theme.util

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class Sizes(
    val default: TextUnit = 15.sp,
    val tooSmall: TextUnit = 6.sp,
    val extraSmall: TextUnit = 11.sp,
    val small: TextUnit = 12.sp,
    val medium: TextUnit = 14.sp,
    val mediumLarge: TextUnit = 16.sp,
    val large: TextUnit = 18.sp,
    val extraLarge: TextUnit = 23.sp,
    val xLarge: TextUnit = 10.sp,
    val x2Large: TextUnit = 20.sp,
    val x3Large: TextUnit = 30.sp,
    val x4Large: TextUnit = 40.sp
)

val LocalSizes = compositionLocalOf { Sizes() }

val MaterialTheme.sizes: Sizes
    @Composable
    @ReadOnlyComposable
    get() = LocalSizes.current