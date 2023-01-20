package com.sanjai.newsappdemo.presentation.screen.navigation

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object LoginScreen: Screen("login_screen")
    object SignUpScreen: Screen("signUp_screen")
    object WelcomeScreen: Screen("welcome_screen")
    object NewsScreen: Screen("news_screen")
    object NewsWebViewScreen: Screen("news_webView_screen")
    object IndiaScreen: Screen("india_screen")
    object SearchScreen: Screen("search_screen")
    object SavedScreen: Screen("saved_screen")
}