package com.app.dummyapi.ui.styles

import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.app.dummyapi.ui.theme.BlackColor
import com.app.dummyapi.ui.theme.PoppinsFontFamily

val normalTextFieldStyle = TextStyle(
    color = BlackColor,
    fontFamily = PoppinsFontFamily,
    fontSize = 19.sp,
    fontWeight = FontWeight.W500,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    ),
)

val normalTextHintStyle = TextStyle(
    color = BlackColor,
    textAlign = TextAlign.Start,
    fontFamily = PoppinsFontFamily,
    fontSize = 15.sp,
    fontWeight = FontWeight.W300,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    ),
)