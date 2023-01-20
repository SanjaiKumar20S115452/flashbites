package com.sanjai.newsappdemo.presentation.screen.news

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjai.newsappdemo.data.model.world_news.UserData
import com.sanjai.newsappdemo.data.util.Resource
import com.sanjai.newsappdemo.data.util.UiEvent
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing
import com.sanjai.newsappdemo.domain.repository.AuthRepository
import com.sanjai.newsappdemo.domain.repository.NewsLocalDataSource
import com.sanjai.newsappdemo.domain.repository.NewsRepository
import com.sanjai.newsappdemo.presentation.screen.navigation.Screen
import com.sanjai.newsappdemo.presentation.screen.saved.SavedScreenViewModel
import com.sanjai.newsappdemo.presentation.screen.signUp.SignUpScreenEvent
import com.sanjai.newsappdemo.ui.theme.colorsList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val repository2: NewsLocalDataSource,
    private val repository3: AuthRepository
): ViewModel() {

    private var newsJob: Job? = null

    init {
        newsJob?.cancel()
        newsJob = viewModelScope.launch(Dispatchers.Main) {
            delay(50L)
            getAllNewsHeadlines()
            getAllCryptoNews()
            getAllSportsNews()
            getUserData()
        }
    }

    val userData = repository3.userData.value

    val userId = repository3.userId

    private val _userDatas = MutableStateFlow<UserData?>(null)
    val userDatas: StateFlow<UserData?> = _userDatas

    private fun getUserData() {
        viewModelScope.launch {
            repository3.getSignedInUserDetails(userId = userId!!).collectLatest { result ->
                when(result) {
                    is Resource.Success -> {
                        result.data?.let {
                            _userDatas.value = it
                        }
                    }
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                }
            }
        }
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var newsState by mutableStateOf(NewsState())

    private val _newsHeadlines = MutableStateFlow<List<ArticleListing>>(emptyList())
    val newsHeadlines: StateFlow<List<ArticleListing>> = _newsHeadlines

    fun onEvent(event: NewsEvent) {
        when(event) {
            is NewsEvent.OnCountryClicked -> {
                getAllNewsHeadlines(country = event.country.countryQuery)
                newsState = newsState.copy(
                    country = event.country.countryQuery,
                    newsCountry = event.country.countryQuery
                )
            }
            is NewsEvent.OnSwipeRefreshed -> {
                getAllNewsHeadlines(country = newsState.newsCountry,fetchFromRemote = true)
            }
            is NewsEvent.OnSaveNewsClicked -> {
                viewModelScope.launch {
                    repository2.saveNewsArticles(
                        article = event.article,
                        onNewsSaved = { result ->
                            if(result) {
                            sendUiEvent(UiEvent.ShowSnackBar(message = "Article Saved!"))
                            }
                        }
                    )
                }
            }
            is NewsEvent.OnLogoutClicked -> {
                viewModelScope.launch {
                    repository3.signOut()
                    sendUiEvent(UiEvent.OnNavigate(route = Screen.LoginScreen.route))
                }
            }
        }
    }

    //NETWORK COMPONENTS
    private fun getAllNewsHeadlines(
        country: String = "us",
        fetchFromRemote: Boolean = false
    ) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getAllNewsHeadlines(country, fetchFromRemote)
                    .collectLatest { result ->
                        when(result) {
                            is Resource.Success -> {
                                result.data?.let {
                                    _newsHeadlines.value = it
                                }
                            }
                            is Resource.Loading -> {
                                newsState = newsState.copy(
                                    isLoading = result.isLoading
                                )
                            }
                            is Resource.Error -> {

                            }
                        }
                    }
            }
        }catch (e: HttpException) {
            
        }
    }

    private val _allCryptoNews = MutableStateFlow<List<ArticleListing>>(emptyList())
    val alCryptoNews: StateFlow<List<ArticleListing>> = _allCryptoNews

    private fun getAllCryptoNews(fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            repository.getAllCryptoNews(fetchFromRemote).collectLatest { result ->
                when(result) {is Resource.Success -> { result.data?.let {
                    _allCryptoNews.value = it } }
                    is Resource.Loading -> { newsState = newsState.copy(
                        isLoading = result.isLoading)
                    }
                    is Resource.Error -> Unit
                }
            }
        }
    }

    private val _allSportsNews = MutableStateFlow<List<ArticleListing>>(emptyList())
    val allSportsNews: StateFlow<List<ArticleListing>> = _allSportsNews

    private fun getAllSportsNews(fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            repository.getAllSportsNews(fetchFromRemote = fetchFromRemote).collectLatest { result ->
                when(result) {
                    is Resource.Success -> {
                        result.data?.let {
                            _allSportsNews.value = it
                        }
                    }
                    is Resource.Loading -> {
                        newsState = newsState.copy(
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