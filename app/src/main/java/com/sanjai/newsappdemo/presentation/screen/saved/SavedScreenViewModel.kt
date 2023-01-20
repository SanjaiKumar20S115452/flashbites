package com.sanjai.newsappdemo.presentation.screen.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjai.newsappdemo.data.model.world_news.SaveArticle
import com.sanjai.newsappdemo.data.util.Resource
import com.sanjai.newsappdemo.data.util.UiEvent
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing
import com.sanjai.newsappdemo.domain.repository.NewsLocalDataSource
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
class SavedScreenViewModel @Inject constructor(
    private val repository: NewsLocalDataSource
 ) : ViewModel() {

    private var job: Job? = null

    init {
        job = viewModelScope.launch {
            delay(10L)
            getAllSavedNewsArticles()
        }
    }

    private val _allSavedNews = MutableStateFlow<List<SaveArticle>>(emptyList())
    val allSavedNews: StateFlow<List<SaveArticle>> = _allSavedNews

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val userId = repository.userId

    fun onEvent(event: SavedScreenEvent) {
        when(event) {
            is SavedScreenEvent.OnDeleteArticleClicked -> {
                viewModelScope.launch {
                    event.articleListing.articleId?.let {
                        repository.deleteArticle(
                            articleId = it,
                            onDeleteArticle = { result ->
                                if(result) {
                                    sendUiEvent(UiEvent.ShowSnackBar(message = "Article Deleted!"))
                                }
                            }
                        )
                    }
                }
            }
            is SavedScreenEvent.OnDeleteAllArticleClicked -> {
                viewModelScope.launch {
                    repository.deleteAllSavedArticles(
                        userId = userId,
                        onDeleteAllCompleted = { completed ->
                            if(completed) {
                                sendUiEvent(UiEvent.ShowSnackBar(message = "Deleted!"))
                            }
                        }
                    )
                }
            }
        }
    }

    fun getAllSavedNewsArticles() {
        viewModelScope.launch {
            repository.getAllSavedNewsArticles(userId).collectLatest { result ->
                when(result) {
                    is Resource.Success -> {
                        result.data?.let {
                            _allSavedNews.value = it
                        }
                    }
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
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