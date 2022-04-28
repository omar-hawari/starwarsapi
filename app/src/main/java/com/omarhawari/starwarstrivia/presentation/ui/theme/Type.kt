package com.omarhawari.starwarstrivia.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.omarhawari.starwarstrivia.R

val starJediFont = FontFamily(
    Font(R.font.starwars_1, FontWeight.Normal),
)


val Typography = Typography(
    h1 = TextStyle(
        fontFamily = starJediFont,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp,
        color = STAR_WARS_YELLOW,
        lineHeight = 30.sp
    ),
    body1 = TextStyle(
        fontFamily = starJediFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    )
)