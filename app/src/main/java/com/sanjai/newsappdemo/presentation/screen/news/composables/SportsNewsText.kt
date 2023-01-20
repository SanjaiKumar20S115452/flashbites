package com.sanjai.newsappdemo.presentation.screen.news.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanjai.newsappdemo.R
import com.sanjai.newsappdemo.ui.theme.HeaderColor
import com.sanjai.newsappdemo.ui.theme.sourceFontFamily

@Composable
fun SportsNews() {
    Row(
        modifier = Modifier
            .padding(start = 15.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    fontSize = 12.sp,
                    color = HeaderColor
                )
                ) {
                    append("Latest ")
                    withStyle(style = SpanStyle(
                        fontSize = 12.sp,
                        color = HeaderColor
                    )
                    ) {
                        append("Sports ")
                        withStyle(style = SpanStyle(
                            fontSize = 12.sp
                        )
                        ) {
                            append("News")
                        }
                    }
                }
            },
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            fontFamily = sourceFontFamily
        )
        Spacer(modifier = Modifier.width(6.dp))
        Image(
            painter = painterResource(id = R.drawable.yoga),
            contentDescription = null,
            modifier = Modifier
                .size(15.dp)
        )
        Spacer(modifier = Modifier.width(3.dp))
        Image(
            painter = painterResource(id = R.drawable.right),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
        )
    }
}