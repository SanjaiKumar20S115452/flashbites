package com.sanjai.newsappdemo.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.sanjai.newsappdemo.data.util.Constants.SORT_KEY
import com.sanjai.newsappdemo.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreOperationsImpl(
    private val dataStore: DataStore<Preferences>
): DataStoreOperations {

    private object PreferencesKey {
        val sortKey = booleanPreferencesKey(name = SORT_KEY)
    }

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.sortKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if(exception is IOException) {
                    emit(emptyPreferences())
                }else {
                    throw exception
                }
            }
            .map { preferences ->
                val sortKey = preferences[PreferencesKey.sortKey] ?: false
                sortKey
            }
    }

}