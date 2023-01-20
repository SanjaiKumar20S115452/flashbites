package com.sanjai.newsappdemo.presentation.screen.news

import androidx.annotation.DrawableRes
import com.sanjai.newsappdemo.R

sealed class CountryListScroll(
    val id: Int,
    val countryQuery: String,
    val country: String,
    @DrawableRes
    val image: Int
) {
    object India: CountryListScroll(
        id = 1,
        countryQuery = "in",
        country = "India",
        R.drawable.india
    )
    object America: CountryListScroll(
        id = 2,
        countryQuery = "us",
        country = "America",
        R.drawable.united_states
    )
    object Russia: CountryListScroll(
        id = 3,
        countryQuery = "ru",
        country = "Russia",
        R.drawable.russia
    )
    object Australia: CountryListScroll(
        id = 4,
        countryQuery = "au",
        country = "Australia",
        R.drawable.australia
    )
    object Canada: CountryListScroll(
        id = 5,
        countryQuery = "ca",
        country = "Canada",
        R.drawable.canada
    )
    object China: CountryListScroll(
        id = 6,
        countryQuery = "cn",
        country = "China",
        R.drawable.china
    )
    object UK: CountryListScroll(
        id = 7,
        countryQuery = "gb",
        country = "United Kingdom",
        R.drawable.united_kingdom
    )
    object Switzerland: CountryListScroll(
        id = 8,
        countryQuery = "ch",
        country = "Switzerland",
        R.drawable.switzerland
    )
    object Ukraine: CountryListScroll(
        id = 9,
        countryQuery = "ua",
        country = "Ukraine",
        R.drawable.ukraine
    )
    object Sweden: CountryListScroll(
        id = 10,
        countryQuery = "se",
        country = "Sweden",
        R.drawable.sweden
    )
    object Singapore: CountryListScroll(
        id = 11,
        countryQuery = "sg",
        country = "Singapore",
        R.drawable.singapore
    )
    object Poland: CountryListScroll(
        id = 12,
        countryQuery = "pl",
        country = "Poland",
        R.drawable.poland
    )
    object Japan: CountryListScroll(
        id = 13,
        countryQuery = "jp",
        country = "Japan",
        R.drawable.japan
    )
    object HongKong: CountryListScroll(
        id = 14,
        countryQuery = "hk",
        country = "Hong Kong",
        R.drawable.hongkong
    )
    object Brazil: CountryListScroll(
        id = 15,
        countryQuery = "br",
        country = "Brazil",
        R.drawable.brazil
    )
    object Argentina: CountryListScroll(
        id = 16,
        countryQuery = "ar",
        country = "Argentina",
        R.drawable.argentina
    )
    object Belgium: CountryListScroll(
        id = 17,
        countryQuery = "be",
        country = "Belgium",
        R.drawable.belgium
    )
    object Portugal: CountryListScroll(
        id = 18,
        countryQuery = "pt",
        country = "Portugal",
        R.drawable.portugal
    )
    object Thailand: CountryListScroll(
        id = 19,
        countryQuery = "th",
        country = "Thailand",
        R.drawable.thailand
    )
    object Turkey: CountryListScroll(
        id = 20,
        countryQuery = "tr",
        country = "Turkey",
        R.drawable.united_states
    )
    object Bulgaria: CountryListScroll(
        id = 21,
        countryQuery = "bg",
        country = "Bulgaria",
        R.drawable.bulgaria
    )
    object Indonesia: CountryListScroll(
        id = 22,
        countryQuery = "id",
        country = "Indonesia",
        R.drawable.indonesia
    )
    object Slovenia: CountryListScroll(
        id = 23,
        countryQuery = "si",
        country = "Slovenia",
        R.drawable.slovenia
    )
    object Slovakia: CountryListScroll(
        id = 24,
        countryQuery = "sk",
        country = "Slovakia",
        R.drawable.slovakia
    )
    object Nigeria: CountryListScroll(
        id = 25,
        countryQuery = "ng",
        country = "Nigeria",
        R.drawable.nigeria
    )
}