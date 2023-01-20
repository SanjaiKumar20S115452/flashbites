package com.sanjai.newsappdemo.presentation.screen.search

data class SearchNewsState(
    val query: String = "",
    val clickedCountry: String = "us",
    val isLoading: Boolean = false,
    val searchedCountries: SearchCountries = SearchCountries(name = "", icon = 0)
)
