package com.sanjai.newsappdemo.presentation.screen.search

sealed class SearchNewsEvent {
    data class OnSearchQueryChange(val searchQuery: String): SearchNewsEvent()
    object OnSwipeToRefresh: SearchNewsEvent()
    data class OnCountryClicked(val searchCountries: SearchCountries): SearchNewsEvent()
}
