package com.sanjai.newsappdemo.presentation.screen.saved

import com.sanjai.newsappdemo.data.model.world_news.SaveArticle
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing

sealed class SavedScreenEvent {
    data class OnDeleteArticleClicked(val id: String, val articleListing: SaveArticle): SavedScreenEvent()
    object OnDeleteAllArticleClicked: SavedScreenEvent()
}