package com.sanjai.newsappdemo.data.model.world_news

data class UserData(
    val userId: String? = null,
    val email: String? = null,
    val username: String? = null,
    val age: Int? = null
) {
    fun toMap() = hashMapOf(
        "userId" to userId,
        "email" to email,
        "username" to username,
        "age" to age
    )
}