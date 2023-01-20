package com.sanjai.newsappdemo.data.model.world_news

data class Article(
    val userId: String? = null,
    val articleId: String? = null,
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val source: Source? = null,
    val isSaved: Boolean = false
)