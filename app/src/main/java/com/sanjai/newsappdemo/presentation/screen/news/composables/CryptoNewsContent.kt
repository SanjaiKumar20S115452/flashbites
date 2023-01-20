package com.sanjai.newsappdemo.presentation.screen.news.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sanjai.newsappdemo.R
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing
import com.sanjai.newsappdemo.ui.theme.HeaderColor
import com.sanjai.newsappdemo.ui.theme.logoColor
import com.sanjai.newsappdemo.ui.theme.sourceFontFamily

@Composable
fun CryptoNewsContent(
    item: List<ArticleListing>,
    onCryptoNewsClicked: (String, String) -> Unit,
    loadingState: Boolean
) {
    if(loadingState) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(top = 20.dp),
                color = Color(0xffe63946)
            )
        }
    }else {
        LazyRow {
            items(
                items = item
            ) { cryptoNews ->
                CryptoListItem(
                    articleListing = cryptoNews,
                    onCryptoNewsClicked
                )
            }
        }
    }
}

@Composable
fun CryptoListItem(
    articleListing: ArticleListing,
    onCryptoNewsClicked: (String, String) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(all = 10.dp)
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.5f),
                shape = RoundedCornerShape(20.dp)
            ).clickable {
                articleListing.url?.let { articleListing.source!!.name?.let { it1 ->
                    onCryptoNewsClicked(it,
                        it1
                    )
                } }
            },
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data("${articleListing.urlToImage}")
                    .crossfade(true)
                    .crossfade(400)
                    .error(R.drawable.warning)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .height(70.dp)
                    .border(width = 0.dp, color = Color.Transparent, shape = RoundedCornerShape(topStart = 20.dp))
            )
//            Spacer(modifier = Modifier.width(5.dp))
//            Text(
//                text = articleListing.source!!.name!!,
//                modifier = Modifier
//                    .padding(start = 5.dp, end = 10.dp),
//                fontSize = 15.sp,
//                color = HeaderColor,
//                fontFamily = sourceFontFamily
//            )
        }
    }
}