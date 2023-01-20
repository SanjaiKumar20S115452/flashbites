package com.sanjai.newsappdemo.domain.model.world_news

data class ArticleListing(
    val userId: String?,
    val articleId: String?,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
    val source: SourceListing?,
    val isSaved: Boolean?
)