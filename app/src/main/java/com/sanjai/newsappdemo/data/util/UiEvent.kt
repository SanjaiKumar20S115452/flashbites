package com.sanjai.newsappdemo.data.util

sealed class UiEvent {
    data class ShowSnackBar(val message: String, val actionLabel: String? = null): UiEvent()
    data class OnNavigate(val route: String): UiEvent()
}
