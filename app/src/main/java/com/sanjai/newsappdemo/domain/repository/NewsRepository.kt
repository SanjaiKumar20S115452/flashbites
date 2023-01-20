package com.sanjai.newsappdemo.domain.repository

import com.sanjai.newsappdemo.data.util.Resource
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getAllNewsHeadlines(country: String, fetchFromRemote: Boolean): Flow<Resource<List<ArticleListing>>>
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
    fun getIndianCategoryNews(fetchFromRemote: Boolean, category: String): Flow<Resource<List<ArticleListing>>>
    fun getSearchedNews(fetchFromRemote: Boolean, country: String, searchQuery: String): Flow<Resource<List<ArticleListing>>>
//    fun getAllSavedNews(): Flow<List<ArticleListing>>
//    suspend fun saveNews(articleListing: ArticleListing)
//    suspend fun deleteNews(id: Int)
//    suspend fun deleteAllNews()
    fun getAllCryptoNews(fetchFromRemote: Boolean): Flow<Resource<List<ArticleListing>>>
    fun getAllSportsNews(fetchFromRemote: Boolean): Flow<Resource<List<ArticleListing>>>
}