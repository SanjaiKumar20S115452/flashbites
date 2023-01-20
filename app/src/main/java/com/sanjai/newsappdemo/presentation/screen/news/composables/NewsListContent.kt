package com.sanjai.newsappdemo.presentation.screen.news.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing
import com.sanjai.newsappdemo.presentation.screen.news.NewsEvent

@Composable
fun NewsListContent(
    item: List<ArticleListing>,
    loadingState: Boolean,
    onArticleClicked: (String, String) -> Unit,
    onEvent: (NewsEvent) -> Unit
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
    } else {
        LazyColumn (
            modifier = Modifier
                .height(550.dp)
        ) {
            items(
                items = item
            ) { article ->
                ArticleListItem(
                    articleListing = article,
                    onArticleClicked = onArticleClicked,
                    onEvent = onEvent
                )
            }
        }
    }
}