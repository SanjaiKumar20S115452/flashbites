package com.sanjai.newsappdemo.presentation.screen.india

data class IndianNewsState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val category: String = "entertainment"
)