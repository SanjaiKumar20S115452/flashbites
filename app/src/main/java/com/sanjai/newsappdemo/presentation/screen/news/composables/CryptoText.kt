package com.sanjai.newsappdemo.presentation.screen.news.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha.disabled
import androidx.compose.material.ContentAlpha.high
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
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
import com.sanjai.newsappdemo.ui.theme.logoColor
import com.sanjai.newsappdemo.ui.theme.reddish
import com.sanjai.newsappdemo.ui.theme.sourceFontFamily

@Composable
fun CryptoText() {
    Row(
        modifier = Modifier
            .padding(start = 20.dp, top = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f)
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
                            color = HeaderColor)) {
                            append("Crypto ")
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
                painter = painterResource(id = R.drawable.bitcoin),
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
        Row(
            modifier = Modifier
                .padding(end = 8.dp)
                .fillMaxWidth()
                .weight(4f),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "See article",
                fontSize = 10.sp,
                fontFamily = sourceFontFamily,
                color = HeaderColor.copy(alpha = high),
                fontWeight = FontWeight.Light
            )
        }
    }
}