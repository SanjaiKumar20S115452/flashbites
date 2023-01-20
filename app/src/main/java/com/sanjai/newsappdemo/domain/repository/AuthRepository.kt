package com.sanjai.newsappdemo.domain.repository

import androidx.compose.runtime.MutableState
import com.sanjai.newsappdemo.data.model.world_news.UserData
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: Boolean
    val userId: String?
    suspend fun login(email: String, password: String, onLoginCompleted: (Boolean) -> Unit, loginException: (String) -> Unit)
    suspend fun signUp(email: String, password: String, username: String, age: Int,usernameAlreadyExists: (Boolean) -> Unit, onSignUpCompleted: (Boolean) -> Unit, signUpException: (String) -> Unit)
    suspend fun signOut()
    val userData: MutableState<String>
    fun getSignedInUserDetails(userId: String): Flow<com.sanjai.newsappdemo.data.util.Resource<UserData>>
}