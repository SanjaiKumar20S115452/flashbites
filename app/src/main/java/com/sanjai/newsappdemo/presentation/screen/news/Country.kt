package com.sanjai.newsappdemo.presentation.screen.news

enum class Country(val country: String) {
    US("US"),
    RU("RU"),
    AU("AU"),
    CA("CA"),
    GB("GB"),
    IN("IN"),
    CN("CN"),
    CH("CH"),
    UA("UA"),
    SE("SE"),
    SG("SG"),
    PL("PL"),
    JP("JP"),
    HK("HK"),
    BR("BR"),
    AR("AR"),
    BE("BE"),
    PT("PT"),
    TH("TH"),
    TR("TR"),
    BG("BG"),
    ID("ID"),
    SI("SI"),
    SK("SK"),
    NG("NG")
}

fun Country.toRequestNames(): String {
    return when(this.name) {
        "IN" -> "India"
        "US" -> "America"
        "RU" -> "Russia"
        "AU" -> "Australia"
        "CA" -> "Canada"
        "CN" -> "China"
        "GB" -> "UK"
        "CH" -> "Switzerland"
        "UA" -> "Ukraine"
        "SE" -> "Sweden"
        "SG" -> "Singapore"
        "PL" -> "Poland"
        "JP"->  "Japan"
        "HK" -> "Hong Kong"
        "BR" -> "Brazil"
        "AR" -> "Argentina"
        "BE" -> "Belgium"
        "PT" -> "Portugal"
        "TH" -> "Thailand"
        "TR" -> "Turkey"
        "BG" -> "Bulgaria"
        "ID" -> "Indonesia"
        "SI" -> "Slovenia"
        "SK" -> "Slovakia"
        "NG" -> "Nigeria"
        else -> "India"
    }
}

fun customCountryNames(country: String): String {
    return when(country) {
        "IN" -> "Indian"
        "US" -> "American"
        "RU" -> "Russian"
        "AU" -> "Australian"
        "CA" -> "Canada"
        "CN" -> "Chinese"
        "GB" -> "UK"
        "CH" -> "Swiss"
        "UA" -> "Ukraine"
        "SE" -> "Sweden"
        "SG" -> "Singapore"
        "PL" -> "Poland"
        "JP"->  "Japanese"
        "HK" -> "Hong Kong"
        "BR" -> "Brazil"
        "AR" -> "Argentina"
        "BE" -> "Belgium"
        "PT" -> "Portuguese"
        "TH" -> "Thailand"
        "TR" -> "Turkish"
        "BG" -> "Bulgaria"
        "ID" -> "Indonesia"
        "SI" -> "Slovenia"
        "SK" -> "Slovakia"
        "NG" -> "Nigeria"
        else -> "Indian"
    }
}