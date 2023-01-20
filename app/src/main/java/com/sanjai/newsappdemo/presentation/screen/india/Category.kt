package com.sanjai.newsappdemo.presentation.screen.india

enum class Category(val category: String) {
    business("business"),
    entertainment("entertainment"),
    general("general"),
    health("health"),
    science("science"),
    sports("sports"),
    technology("technology")
}

fun Category.toCustomCategoryNames(): String {
    return when(this.name) {
        "business" -> "Business"
        "entertainment" -> "Entertainment"
        "general" -> "General"
        "health" -> "Health"
        "science" -> "Science"
        "sports" -> "Sports"
        "technology" -> "Technology"
        else -> "Entertainment"
    }
}