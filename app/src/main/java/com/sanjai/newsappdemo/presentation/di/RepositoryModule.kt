package com.sanjai.newsappdemo.presentation.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sanjai.newsappdemo.data.api.NewsApi
import com.sanjai.newsappdemo.data.repository.*
import com.sanjai.newsappdemo.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSourceInstance(
        api: NewsApi
    ): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(api = api)
    }

    @Singleton
    @Provides
    fun provideDataStorePreferences(
        @ApplicationContext
        context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(name = "app_name")
        }
    }

    @Singleton
    @Provides
    fun provideDataStoreOperationsInstance(
        dataStore: DataStore<Preferences>
    ): DataStoreOperations {
        return DataStoreOperationsImpl(dataStore = dataStore)
    }

    @Singleton
    @Provides
    fun provideNewsLocalDataSourceInstance(
        auth: FirebaseAuth,
        db: FirebaseFirestore
    ): NewsLocalDataSource {
        return NewsLocalDataSourceImpl(auth, db)
    }

    @Singleton
    @Provides
    fun provideNewsRepositoryInstance(
        newsRemoteDataSource: NewsRemoteDataSource,
        dataStoreOperations: DataStoreOperations,
        newsLocalDataSource: NewsLocalDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(
            newsRemoteDataSource,
            dataStoreOperations,
            newsLocalDataSource
        )
    }

    @Singleton
    @Provides
    fun provideAuthRepositoryInstance(
        auth: FirebaseAuth,
        db: FirebaseFirestore
    ): AuthRepository {
        return AuthRepositoryImpl(auth, db)
    }

}