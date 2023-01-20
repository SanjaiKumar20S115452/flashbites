package com.sanjai.newsappdemo.presentation.screen.india

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjai.newsappdemo.data.util.Resource
import com.sanjai.newsappdemo.data.util.UiEvent
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing
import com.sanjai.newsappdemo.domain.repository.NewsRepository
import com.sanjai.newsappdemo.presentation.screen.news.NewsEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IndianNewsViewModel @Inject constructor(
    private val repository: NewsRepository
 ) : ViewModel() {

     private var indianNewsJob: Job? = null

    var indianNewsState by mutableStateOf(IndianNewsState())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        indianNewsJob?.cancel()
        indianNewsJob = viewModelScope.launch {
            delay(50L)
            getIndianNews()
        }
    }

    fun onEvent(event: IndianNewsEvent) {
        when(event) {
            is IndianNewsEvent.OnSwipeToRefresh -> {
                viewModelScope.launch {
                    getIndianNews(
                        fetchFromRemote = true,
                        category = indianNewsState.category
                    )
                }
            }
            is IndianNewsEvent.OnCategoryClicked -> {
                viewModelScope.launch {
                    getIndianNews(category = event.category.category)
                    indianNewsState = indianNewsState.copy(
                        category = event.category.category
                    )
                }
                sendUiEvent(UiEvent.ShowSnackBar(message = "${event.category.category.first().uppercase()}${event.category.category.drop(1)} News !"))
            }
        }
    }

    private val _indianCategoryNews = MutableStateFlow<List<ArticleListing>>(emptyList())
    val indianCategoryNews: StateFlow<List<ArticleListing>> = _indianCategoryNews

    private fun getIndianNews(
        fetchFromRemote: Boolean = false,
        category: String = "entertainment"
    ) {
        viewModelScope.launch {
            repository.getIndianCategoryNews(fetchFromRemote, category).collectLatest { result ->
                when(result) {
                    is Resource.Success -> {
                        result.data?.let {
                            _indianCategoryNews.value = it
                        }
                    }
                    is Resource.Loading -> {
                        indianNewsState = indianNewsState.copy(
                            isLoading = result.isLoading
                        )
                    }
                    is Resource.Error -> Unit
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

 }