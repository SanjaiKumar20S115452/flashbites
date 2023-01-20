package com.sanjai.newsappdemo.data.mappers

import com.sanjai.newsappdemo.data.model.world_news.Article
import com.sanjai.newsappdemo.data.model.world_news.Source
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing
import com.sanjai.newsappdemo.domain.model.world_news.SourceListing

fun Article.toArticleListing(): ArticleListing {
    return ArticleListing(
        userId = userId,
        articleId = articleId,
        content = content,
        description = description,
        publishedAt = publishedAt,
        title = title,
        url = url,
        urlToImage = urlToImage,
        source = SourceListing(
            id = source!!.id,
            name = source.name
        ),
        isSaved = isSaved!!,
        author = author
    )
}

fun ArticleListing.toArticle(): Article {
    return Article(
        userId = userId,
        articleId = articleId,
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        title = title,
        url = url,
        urlToImage = urlToImage,
        source = Source(
            id = source!!.id,
            name = source.name
        ),
        isSaved = isSaved!!
    )
}