package com.sanjai.newsappdemo.presentation.screen.news.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing
import com.sanjai.newsappdemo.ui.theme.HeaderColor
import com.sanjai.newsappdemo.ui.theme.sourceFontFamily

@Composable
fun SportsNewsContentItem(
    articleListing: ArticleListing,
    onSportsNewsClicked: (String, String) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, bottom = 8.dp)
            .clickable {
                articleListing.url?.let { articleListing.source!!.name?.let { it1 ->
                    onSportsNewsClicked(it,
                        it1
                    )
                } }
            },
        color = Color.DarkGray.copy(alpha = 0.4f),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier
                .border(
                    width = 0.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(15.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data("${articleListing.urlToImage}")
                    .crossfade(true)
                    .crossfade(400)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .height(90.dp)
                    .width(160.dp)
                    .border(
                        width = 0.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(topStart = 15.dp)
                    ),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(5.dp))
            Column(
                modifier = Modifier
                    .padding(start = 5.dp, top = 8.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = articleListing.title!!,
                    fontSize = 12.sp,
                    color = HeaderColor,
                    fontWeight = FontWeight.Bold,
                    fontFamily = sourceFontFamily
                )
                Spacer(modifier = Modifier.height(8.dp))
                articleListing.content?.let {
                    Text(
                        text = it,
                        fontSize = 9.sp,
                        color = Color.LightGray.copy(medium),
                        fontWeight = FontWeight.Medium,
                        fontFamily = sourceFontFamily,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Start
//        ) {
//
//            Column(
//                modifier = Modifier
//                    .padding(start = 10.dp)
//                    .fillMaxWidth()
//            ) {
//                Text(
//                    text = articleListing.title!!,
//                    fontSize = 14.sp,
//                    color = HeaderColor,
//                    fontFamily = sourceFontFamily
//                )
//                Spacer(modifier = Modifier.height(5.dp))
//                Text(
//                    text = articleListing.content!!,
//                    fontSize = 9.sp,
//                    fontFamily = sourceFontFamily,
//                    maxLines = 2,
//                    overflow = TextOverflow.Ellipsis,
//                    color = Color.LightGray.copy(medium)
//                )
//            }
//        }
//    }
    }
}