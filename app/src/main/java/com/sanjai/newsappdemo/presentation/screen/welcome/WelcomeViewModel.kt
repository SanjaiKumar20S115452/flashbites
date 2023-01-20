package com.sanjai.newsappdemo.presentation.screen.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjai.newsappdemo.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val repository: NewsRepository
): ViewModel() {


    fun onEvent(event: WelcomeScreenEvent) {
        when(event) {
            is WelcomeScreenEvent.SaveOnBoardingState -> {
                viewModelScope.launch {
                    repository.saveOnBoardingState(completed = event.completed)
                }
            }
        }
    }

}