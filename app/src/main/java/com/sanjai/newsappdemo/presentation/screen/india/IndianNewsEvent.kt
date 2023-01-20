package com.sanjai.newsappdemo.presentation.screen.india

sealed class IndianNewsEvent {
    object OnSwipeToRefresh: IndianNewsEvent()
    data class OnCategoryClicked(val category: Category): IndianNewsEvent()
}