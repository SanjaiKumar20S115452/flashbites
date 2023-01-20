package com.sanjai.newsappdemo.presentation.screen.news

import com.sanjai.newsappdemo.data.model.world_news.Article
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing

sealed class NewsEvent {
    data class OnCountryClicked(val country: CountryListScroll): NewsEvent()
    object OnSwipeRefreshed: NewsEvent()
    data class OnSaveNewsClicked(val article: Article): NewsEvent()
    object OnLogoutClicked: NewsEvent()
}