package com.sanjai.newsappdemo.presentation.screen.welcome

sealed class WelcomeScreenEvent {
    data class SaveOnBoardingState(val completed: Boolean = false): WelcomeScreenEvent()
}
