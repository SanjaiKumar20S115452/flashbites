package com.sanjai.newsappdemo.presentation.screen.signUp

sealed class SignUpScreenEvent {
    data class SignUpEmail(val signUpEmail: String): SignUpScreenEvent()
    data class SignUpPassword(val signUpPassword: String): SignUpScreenEvent()
    data class SignUpUsername(val signUpUsername: String): SignUpScreenEvent()
    data class SignUpAge(val signUpAge: String): SignUpScreenEvent()
    object OnSignUpClicked: SignUpScreenEvent()
    object OnLoginClicked: SignUpScreenEvent()
}
