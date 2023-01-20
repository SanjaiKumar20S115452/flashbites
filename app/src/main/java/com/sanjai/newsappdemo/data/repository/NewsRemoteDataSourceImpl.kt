package com.sanjai.newsappdemo.data.repository

import com.sanjai.newsappdemo.data.api.NewsApi
import com.sanjai.newsappdemo.data.mappers.toArticleListing
import com.sanjai.newsappdemo.data.util.Resource
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing
import com.sanjai.newsappdemo.domain.repository.NewsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRemoteDataSourceImpl(
    private val api: NewsApi
): NewsRemoteDataSource {

    override fun getAllNewsHeadlines(
        country: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<ArticleListing>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val apiResponse = api.getNewsHeadlines(country = country)
            emit(Resource.Success(data = apiResponse.articles.map {
                it.toArticleListing() })
            )
            val emptyResponse = apiResponse.articles.isEmpty() && !fetchFromRemote
            if(emptyResponse) {
                emit(Resource.Loading(isLoading = false))
                return@flow
            }
            if(fetchFromRemote) {
                emit(Resource.Loading(isLoading = true))
                emit(Resource.Success(data = apiResponse.articles.map {
                    it.toArticleListing()
                }))
                emit(Resource.Loading(isLoading = false))
            }
            emit(Resource.Loading(isLoading = false))
        }
    }

    override fun getIndianCategoryNews(
        fetchFromRemote: Boolean,
        category: String
    ): Flow<Resource<List<ArticleListing>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val apiResponse = api.getIndianCategoryNews(category = category)
            emit(Resource.Success(data = apiResponse.articles.map {
                it.toArticleListing()
            }))
            if(fetchFromRemote) {
                emit(Resource.Loading(isLoading = true))
                emit(Resource.Success(apiResponse.articles.map {
                    it.toArticleListing()
                }))
                emit(Resource.Loading(isLoading = false))
            }
            val emptyResponse = apiResponse.articles.isEmpty() && !fetchFromRemote
            if(emptyResponse) {
                emit(Resource.Loading(false))
                return@flow
            }
            emit(Resource.Loading(isLoading = false))
        }
    }

    override fun getSearchedNews(
        fetchFromRemote: Boolean,
        country: String,
        searchQuery: String
    ): Flow<Resource<List<ArticleListing>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val searchedNews = api.getSearchedNews(
                country = country,
                searchQuery = searchQuery
            )
            emit(Resource.Success(data = searchedNews.articles.map {
                it.toArticleListing()
            }))
            val emptySearchedResponse = searchedNews.articles.isEmpty() && !fetchFromRemote

            if(emptySearchedResponse) {
                emit(Resource.Loading(isLoading = false))
                return@flow
            }
            emit(Resource.Loading(isLoading = false))
        }
    }

    override fun getAllCryptoNews(fetchFromRemote: Boolean): Flow<Resource<List<ArticleListing>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val allCryptoNews = api.getAllCryptoNews()
            emit(Resource.Success(data = allCryptoNews.articles.map {
                it.toArticleListing()
            }))
            if(fetchFromRemote) {
                emit(Resource.Loading(isLoading = true))
                emit(Resource.Success(data = allCryptoNews.articles.map {
                    it.toArticleListing()
                }))
                emit(Resource.Loading(isLoading = false))
            }
            val emptyCryptoNewsResponse = allCryptoNews.articles.isEmpty() && !fetchFromRemote
            if(emptyCryptoNewsResponse) {
                emit(Resource.Loading(isLoading = false))
                return@flow
            }
            emit(Resource.Loading(isLoading = false))
        }
    }

    override fun getAllSportsNews(fetchFromRemote: Boolean): Flow<Resource<List<ArticleListing>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val allSportsNews = api.getAllSportsNews()
            emit(Resource.Success(data = allSportsNews.articles.map {
                it.toArticleListing()
            }))
            if(fetchFromRemote) {
                emit(Resource.Loading(isLoading = true))
                emit(Resource.Success(data = allSportsNews.articles.map {
                    it.toArticleListing()
                }))
                emit(Resource.Loading(isLoading = false))
            }
            val emptySportsNews = allSportsNews.articles.isEmpty() && !fetchFromRemote
            if(emptySportsNews) {
                emit(Resource.Loading(isLoading = false))
                return@flow
            }
            emit(Resource.Loading(isLoading = false))
        }
    }
}