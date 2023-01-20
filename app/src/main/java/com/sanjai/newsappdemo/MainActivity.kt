package com.sanjai.newsappdemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sanjai.newsappdemo.data.util.BottomNavItem
import com.sanjai.newsappdemo.presentation.screen.navigation.BottomBarNavigation
import com.sanjai.newsappdemo.presentation.screen.navigation.Screen
import com.sanjai.newsappdemo.presentation.screen.navigation.SetUpNavigation
import com.sanjai.newsappdemo.ui.theme.NewsAppDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppDemoTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = Color.Black)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberAnimatedNavController()
                    val backStackEntry by navController.currentBackStackEntryAsState()
                    Scaffold(
                        bottomBar = {
                            if(
                                backStackEntry?.destination?.route == Screen.NewsScreen.route
                                || backStackEntry?.destination?.route == Screen.IndiaScreen.route
                                || backStackEntry?.destination?.route == Screen.SearchScreen.route
                                || backStackEntry?.destination?.route == Screen.SavedScreen.route
                                || backStackEntry?.destination?.route == Screen.NewsWebViewScreen.route
                            ) {
                                BottomBarNavigation(
                                    items = listOf(
                                        BottomNavItem(
                                            route = Screen.NewsScreen.route,
                                            icon = Icons.Filled.Home,
                                            badgeCount = 10
                                        ),
                                        BottomNavItem(
                                            route = Screen.IndiaScreen.route,
                                            icon = Icons.Filled.Star,
                                        ),
                                        BottomNavItem(
                                            route = Screen.SearchScreen.route,
                                            icon = Icons.Filled.Search,
                                        ),
                                        BottomNavItem(
                                            route = Screen.SavedScreen.route,
                                            icon = Icons.Default.Favorite
                                        )
                                    ),
                                    navController = navController,
                                    onItemClick = {
                                        navController.navigate(it.route) {
                                            popUpTo(it.route) { inclusive = true }
                                        }
                                    }
                                )
                            }
                        }
                    ) {
                        SetUpNavigation(navController = navController)
                    }
                }
            }
        }
    }
}