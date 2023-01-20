package com.sanjai.newsappdemo.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.unit.sp
import com.sanjai.newsappdemo.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val roboto = FontFamily(
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_regular, FontWeight.Black),
    Font(R.font.roboto_medium_italic, FontWeight.ExtraBold)
)

val sourceFontFamily = FontFamily(
    Font(R.font.sourcesanspro_blackitalic, FontWeight.ExtraBold),
    Font(R.font.sourcesanspro_italic, FontWeight.Bold)
)

val rubik = FontFamily(
    Font(R.font.rubikdirt_regular, FontWeight.Light)
)

val righetous = FontFamily(
    Font(R.font.righteous_regular, FontWeight.ExtraBold)
)

val righetous2 = FontFamily(
    Font(R.font.righteous_regular, FontWeight.Light)
)

val kranky = FontFamily(
    Font(R.font.kranky_regular, FontWeight.ExtraBold)
)

val chicle = FontFamily(
    Font(R.font.chicle_regular, FontWeight.ExtraBold)
)

val metalMania =  FontFamily(
Font(R.font.metalmania_regular, FontWeight.Light)
)