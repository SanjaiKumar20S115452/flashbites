package com.sanjai.newsappdemo.presentation.screen.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjai.newsappdemo.data.util.Resource
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing
import com.sanjai.newsappdemo.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchedNewsViewModel @Inject constructor(
    private val repository: NewsRepository
 ) : ViewModel() {

     var state by mutableStateOf(SearchNewsState())

    private var job: Job? = null

    init {
        job?.cancel()
        job = viewModelScope.launch {
            delay(50L)
            getSearchedNews(country = "us")
        }
    }

    private val _searchedNews = MutableStateFlow<List<ArticleListing>>(emptyList())
    val searchedNews: StateFlow<List<ArticleListing>> = _searchedNews

    fun onEvent(event: SearchNewsEvent) {
        when(event) {
            is SearchNewsEvent.OnSearchQueryChange -> {
                state = state.copy(
                    query = event.searchQuery
                )
                job?.cancel()
                job = viewModelScope.launch {
                    delay(500L)
                    getSearchedNews(
                        country = state.clickedCountry,
                        searchQuery = state.query
                    )
                }
            }
            is SearchNewsEvent.OnCountryClicked -> {
                state = state.copy(
                    clickedCountry = event.searchCountries.toCustomCountriesName(),
                    searchedCountries = event.searchCountries
                )
            }
            is SearchNewsEvent.OnSwipeToRefresh -> {
                getSearchedNews(
                    fetchFromRemote = true,
                    country = state.clickedCountry,
                    searchQuery = state.query
                )
            }
        }
    }

    private fun getSearchedNews(
        fetchFromRemote: Boolean = false,
        country: String = "au",
        searchQuery: String = state.query
    ) {
        viewModelScope.launch {
            repository.getSearchedNews(
                fetchFromRemote, country, searchQuery
            ).collectLatest { result ->
                when(result) {
                    is Resource.Success -> {
                        result.data?.let {
                            _searchedNews.value = it
                        }
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = result.isLoading
                        )
                    }
                    is Resource.Error -> Unit
                }
            }
        }
    }
 }