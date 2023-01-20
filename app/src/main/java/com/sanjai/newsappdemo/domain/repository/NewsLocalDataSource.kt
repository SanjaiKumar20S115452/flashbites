package com.sanjai.newsappdemo.domain.repository

import com.sanjai.newsappdemo.data.model.world_news.Article
import com.sanjai.newsappdemo.data.model.world_news.SaveArticle
import com.sanjai.newsappdemo.data.util.Resource
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {

//    fun getAllSavedNews(): Flow<List<ArticleListing>>
//
//    suspend fun saveNews(articleListing: ArticleListing)
//
//    suspend fun deleteNews(id: Int)
//
//    suspend fun deleteAllNews()

    val userId: String
    suspend fun saveNewsArticles(article: Article,onNewsSaved: (Boolean) -> Unit)
    fun getAllSavedNewsArticles(userId: String): Flow<Resource<List<SaveArticle>>>
    suspend fun deleteArticle(articleId: String, onDeleteArticle: (Boolean) -> Unit)
    suspend fun deleteAllSavedArticles(userId: String, onDeleteAllCompleted: (Boolean) -> Unit)
}