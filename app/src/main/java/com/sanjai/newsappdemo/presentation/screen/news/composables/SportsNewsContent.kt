package com.sanjai.newsappdemo.presentation.screen.news.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing

@Composable
fun SportsNewsContent(
    item: List<ArticleListing>,
    loadingState: Boolean,
    onSportsNewsClicked: (String, String) -> Unit
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
        LazyColumn(
            modifier = Modifier
                .height(220.dp)
        ) {
            items(
                items = item
            ) { cryptoNews ->
                SportsNewsContentItem(
                    articleListing = cryptoNews,
                    onSportsNewsClicked
                )
            }
        }
    }
}