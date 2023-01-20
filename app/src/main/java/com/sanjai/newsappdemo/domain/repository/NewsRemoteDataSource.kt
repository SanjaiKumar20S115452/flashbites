package com.sanjai.newsappdemo.domain.repository

import com.sanjai.newsappdemo.data.util.Resource
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing
import kotlinx.coroutines.flow.Flow

interface NewsRemoteDataSource {
    fun getAllNewsHeadlines(country: String, fetchFromRemote: Boolean): Flow<Resource<List<ArticleListing>>>
    fun getIndianCategoryNews(fetchFromRemote: Boolean, category: String): Flow<Resource<List<ArticleListing>>>
    fun getSearchedNews(fetchFromRemote: Boolean, country: String, searchQuery: String): Flow<Resource<List<ArticleListing>>>
    fun getAllCryptoNews(fetchFromRemote: Boolean): Flow<Resource<List<ArticleListing>>>
    fun getAllSportsNews(fetchFromRemote: Boolean): Flow<Resource<List<ArticleListing>>>
}