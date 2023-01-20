package com.sanjai.newsappdemo.presentation.screen.news

data class NewsState(
    val isLoading: Boolean = false,
    val country: String = "American",
    val isRefreshing: Boolean = false,
    val newsCountry: String = "us",
    val errorText: String = "Error Loading Headlines",
    val errorState: Boolean = false
)