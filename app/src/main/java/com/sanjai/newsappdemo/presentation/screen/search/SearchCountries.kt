package com.sanjai.newsappdemo.presentation.screen.search

data class SearchCountries(
    val name: String,
    val icon: Int
)

fun SearchCountries.toCustomCountriesName(): String {
    return when(this.name) {
        "India" -> "in"
        "Australia" -> "au"
        "Canada" -> "ca"
        "UK" -> "gb"
        "America" -> "us"
        else -> "America"
    }
}
