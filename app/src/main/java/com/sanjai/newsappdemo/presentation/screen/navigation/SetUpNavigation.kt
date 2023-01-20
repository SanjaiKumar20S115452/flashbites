package com.sanjai.newsappdemo.presentation.screen.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentScope.SlideDirection.*
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.sanjai.newsappdemo.presentation.screen.india.composables.IndiaScreen
import com.sanjai.newsappdemo.presentation.screen.login.composable.LoginScreen
import com.sanjai.newsappdemo.presentation.screen.news.composables.NewsScreen
import com.sanjai.newsappdemo.presentation.screen.saved.composables.SavedScreen
import com.sanjai.newsappdemo.presentation.screen.search.composables.SearchScreen
import com.sanjai.newsappdemo.presentation.screen.signUp.composable.SignUpScreen
import com.sanjai.newsappdemo.presentation.screen.splash.composables.SplashScreen
import com.sanjai.newsappdemo.presentation.screen.webview.composables.NewsWebViewScreen
import com.sanjai.newsappdemo.presentation.screen.welcome.WelcomeScreen

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetUpNavigation(
    navController: NavHostController
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(onNavigate = {
                if(it.route == Screen.SignUpScreen.route) {
                    navController.navigate(Screen.SignUpScreen.route) {
                        popUpTo(Screen.LoginScreen.route) { inclusive = true }
                    }
                }
                navController.navigate(it.route) {
                    popUpTo(Screen.LoginScreen.route) { inclusive = true }
                }
            })
        }
        composable(
            route = Screen.SignUpScreen.route
        ) {
            SignUpScreen(
                onNavigate = {
                    if (it.route == Screen.LoginScreen.route) {
                        navController.navigate(Screen.LoginScreen.route) {
                            popUpTo(Screen.SignUpScreen.route) { inclusive = true }
                        }
                    }
                    navController.navigate(it.route) {
                        popUpTo(Screen.SignUpScreen.route) { inclusive = true }
                    }
                }
            )
        }
        composable(
            route = Screen.SplashScreen.route,
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(durationMillis = 700))
            }
        ) {
            SplashScreen(navController = navController)
        }
        composable(
            route = Screen.WelcomeScreen.route,
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(durationMillis = 700))
            }
        ) {
            WelcomeScreen(navController = navController)
        }
        composable(
            route = Screen.NewsScreen.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(durationMillis = 700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(durationMillis = 700))
            }
        ) {
            NewsScreen(navController = navController, onNavigate = {
                if(it.route == Screen.LoginScreen.route) {
                    navController.navigate(it.route) {
                        popUpTo(Screen.NewsScreen.route) { inclusive = true }
                    }
                }
            })
        }
        composable(
            route = Screen.NewsWebViewScreen.route + "?article_url={article_url}&article_source={article_source}",
            arguments = listOf(
                navArgument("article_url") {
                    type = NavType.StringType
                },
                navArgument("article_source") {
                    type = NavType.StringType
                }
            ),
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(durationMillis = 700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(durationMillis = 700))
            }
        ) {
            NewsWebViewScreen()
        }
        composable(
            route = Screen.IndiaScreen.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(durationMillis = 700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(durationMillis = 700))
            }
        ) {
            IndiaScreen(navController = navController)
        }
        composable(
            route = Screen.SearchScreen.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(durationMillis = 700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(durationMillis = 700))
            }
        ) {
            SearchScreen(navController = navController)
        }
        composable(
            route = Screen.SavedScreen.route
        ) {
            SavedScreen(navController = navController)
        }
    }
}