package com.sanjai.newsappdemo.presentation.screen.news.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha.disabled
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanjai.newsappdemo.R
import com.sanjai.newsappdemo.data.model.world_news.UserData
import com.sanjai.newsappdemo.ui.theme.*
import java.text.DateFormat
import java.util.*
import kotlin.random.Random

@Composable
fun TopCountriesText(
    userData: UserData?,
    onLogoutClicked: () -> Unit
) {
    val date = System.currentTimeMillis()
    val formattedDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(date)
    Column(
        modifier = Modifier
            .padding(top = 18.dp, start = 15.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formattedDate,
                fontWeight = FontWeight.Bold,
                color = Color.White.copy(alpha = medium),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 5.dp),
                fontFamily = sourceFontFamily
            )
            Image(
                painter = painterResource(id = R.drawable.logout),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(35.dp)
                    .clickable {
                        onLogoutClicked()
                    }
            )
        }
        if (userData != null) {
            Text(text = "Welcome, ${userData.username}",
                color = HeaderColor,
                fontFamily = sourceFontFamily,
                fontSize = 35.sp
            )
        }
    }
//    Row(
//        modifier = Modifier
//            .padding(start = 15.dp)
//            .fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.Start
//    ) {
//        Text(
//            text = buildAnnotatedString {
//                withStyle(style = SpanStyle(
//                    fontSize = 14.sp,
//                    color = HeaderColor
//                )
//                ) {
//                    append("Top ")
//                    withStyle(style = SpanStyle(
//                        fontSize = 14.sp,
//                        color = HeaderColor
//                    )
//                    ) {
//                        append("Countries ")
//                        withStyle(style = SpanStyle(
//                            fontSize = 14.sp,
//                            color = HeaderColor
//                        )
//                        ) {
//                            append("Headlines")
//                        }
//                    }
//                }
//            },
//            fontWeight = FontWeight.Bold,
//            fontSize = 14.sp,
//            fontFamily = sourceFontFamily
//        )
//        Spacer(modifier = Modifier.width(3.dp))
//        Image(
//            painter = painterResource(id = R.drawable.right),
//            contentDescription = null,
//            modifier = Modifier
//                .size(20.dp)
//        )
//    }
}