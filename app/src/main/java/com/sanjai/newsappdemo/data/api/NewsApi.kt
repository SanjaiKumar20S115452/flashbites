package com.sanjai.newsappdemo.data.api

import com.sanjai.newsappdemo.data.model.world_news.News
import com.sanjai.newsappdemo.data.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getNewsHeadlines(
        @Query("country")country: String = "in",
        @Query("apiKey")apiKey: String = API_KEY
    ): News

    @GET("/v2/top-headlines")
    suspend fun getIndianCategoryNews(
        @Query("country")country: String = "in",
        @Query("apiKey")apiKey: String = API_KEY,
        @Query("category")category: String = "entertainment"
    ): News

    @GET("/v2/top-headlines")
    suspend fun getSearchedNews(
        @Query("country")country: String = "au",
        @Query("apiKey")apiKey: String = API_KEY,
        @Query("q")searchQuery: String
    ): News

    @GET("/v2/everything")
    suspend fun getAllCryptoNews(
        @Query("q")query: String = "bitcoin",
        @Query("apiKey")apiKey: String = API_KEY
    ): News

    @GET("/v2/top-headlines")
    suspend fun getAllSportsNews(
        @Query("country")country: String = "au",
        @Query("apiKey")apiKey: String = API_KEY,
        @Query("category")category: String = "sports"
    ): News

}