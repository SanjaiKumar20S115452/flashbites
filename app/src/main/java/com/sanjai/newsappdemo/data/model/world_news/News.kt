package com.sanjai.newsappdemo.data.model.world_news

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)