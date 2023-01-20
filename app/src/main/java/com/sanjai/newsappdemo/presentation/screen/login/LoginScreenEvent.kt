package com.sanjai.newsappdemo.presentation.screen.login

sealed class LoginScreenEvent {
    data class OnLoginEmail(val loginEmail: String): LoginScreenEvent()
    data class OnLoginPassword(val loginPassword: String): LoginScreenEvent()
    object OnLoginClicked: LoginScreenEvent()
    object OnSignUpClicked: LoginScreenEvent()
}