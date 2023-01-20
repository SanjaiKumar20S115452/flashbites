package com.sanjai.newsappdemo.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjai.newsappdemo.data.model.world_news.UserData
import com.sanjai.newsappdemo.data.util.Resource
import com.sanjai.newsappdemo.domain.repository.AuthRepository
import com.sanjai.newsappdemo.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val repository2: AuthRepository
): ViewModel() {

    val currentUser = repository2.currentUser

    private var job: Job? = null

    val userId = repository2.userId

    private val _onBoardingState = MutableStateFlow(false)
    val onBoardingPage: StateFlow<Boolean> = _onBoardingState

    init {
        viewModelScope.launch {
            _onBoardingState.value = repository.readOnBoardingState().stateIn(viewModelScope).value
        }
    }

}