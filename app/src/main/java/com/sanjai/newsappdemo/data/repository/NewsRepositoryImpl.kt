package com.sanjai.newsappdemo.data.repository

import com.sanjai.newsappdemo.data.util.Resource
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing
import com.sanjai.newsappdemo.domain.repository.DataStoreOperations
import com.sanjai.newsappdemo.domain.repository.NewsLocalDataSource
import com.sanjai.newsappdemo.domain.repository.NewsRemoteDataSource
import com.sanjai.newsappdemo.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val dataStoreOperations: DataStoreOperations,
    private val newsLocalDataSource: NewsLocalDataSource
): NewsRepository {

    override fun getAllNewsHeadlines(
        country: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<ArticleListing>>> {
        return newsRemoteDataSource.getAllNewsHeadlines(country, fetchFromRemote)
    }

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStoreOperations.saveOnBoardingState(completed = completed)
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStoreOperations.readOnBoardingState()
    }

    override fun getIndianCategoryNews(
        fetchFromRemote: Boolean,
        category: String
    ): Flow<Resource<List<ArticleListing>>> {
        return newsRemoteDataSource.getIndianCategoryNews(fetchFromRemote, category)
    }

    override fun getSearchedNews(
        fetchFromRemote: Boolean,
        country: String,
        searchQuery: String
    ): Flow<Resource<List<ArticleListing>>> {
        return newsRemoteDataSource.getSearchedNews(fetchFromRemote, country, searchQuery)
    }
//
//    override fun getAllSavedNews(): Flow<List<ArticleListing>> {
//        return newsLocalDataSource.getAllSavedNews()
//    }
//
//    override suspend fun saveNews(articleListing: ArticleListing) {
//        newsLocalDataSource.saveNews(articleListing)
//    }
//
//    override suspend fun deleteNews(id: Int) {
//        newsLocalDataSource.deleteNews(id)
//    }
//
//    override suspend fun deleteAllNews() {
//        newsLocalDataSource.deleteAllNews()
//    }

    override fun getAllCryptoNews(fetchFromRemote: Boolean): Flow<Resource<List<ArticleListing>>> {
        return newsRemoteDataSource.getAllCryptoNews(fetchFromRemote)
    }

    override fun getAllSportsNews(fetchFromRemote: Boolean): Flow<Resource<List<ArticleListing>>> {
        return newsRemoteDataSource.getAllSportsNews(fetchFromRemote)
    }

}