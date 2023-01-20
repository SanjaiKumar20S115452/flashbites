package com.sanjai.newsappdemo.presentation.screen.news.composables

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sanjai.newsappdemo.data.util.BottomNavItem
import com.sanjai.newsappdemo.data.util.UiEvent
import com.sanjai.newsappdemo.presentation.screen.navigation.BottomBarNavigation
import com.sanjai.newsappdemo.presentation.screen.navigation.Screen
import com.sanjai.newsappdemo.presentation.screen.news.NewsEvent
import com.sanjai.newsappdemo.presentation.screen.news.NewsViewModel
import com.sanjai.newsappdemo.ui.theme.logoColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel(),
    navController: NavHostController,
    onNavigate: (UiEvent.OnNavigate) -> Unit
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when(event) {
                is UiEvent.OnNavigate -> {
                    onNavigate(event)
                }
            }
        }
    }
    val state = viewModel.newsState
    val newsHeadlines by viewModel.newsHeadlines.collectAsState()
    val swipeToRefresh = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)
    val backStackEntry by navController.currentBackStackEntryAsState()
    val scope = rememberCoroutineScope()
    val allCryptoNews by viewModel.alCryptoNews.collectAsState()
    val allSportsNews by viewModel.allSportsNews.collectAsState()
    var appName by remember {
        mutableStateOf(false)
    }

    val newsContext = LocalContext.current
    val userDatas by viewModel.userDatas.collectAsState()
    Log.i("users",userDatas.toString())

    LaunchedEffect(key1 = true) {
        scope.launch {
            delay(300L)
            appName = true
        }
        viewModel.uiEvent.collectLatest { event ->
            when(event) {
                is UiEvent.ShowSnackBar -> {
                    Toast.makeText(newsContext, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    Scaffold(
        backgroundColor = logoColor,
        bottomBar = {
            if(backStackEntry?.destination?.route == Screen.NewsScreen.route || backStackEntry?.destination?.route == Screen.IndiaScreen.route || backStackEntry?.destination?.route == Screen.SearchScreen.route) {
                BottomBarNavigation(
                    items = listOf(
                        BottomNavItem(
                            route = Screen.NewsScreen.route,
                            icon = Icons.Filled.Star,
                            badgeCount = 10
                        ),
                        BottomNavItem(
                            route = Screen.IndiaScreen.route,
                            icon = Icons.Filled.Home,
                        ),
                        BottomNavItem(
                            route = Screen.SearchScreen.route,
                            icon = Icons.Filled.Search
                        ),
                        BottomNavItem(
                            route = Screen.SavedScreen.route,
                            icon = Icons.Filled.Favorite
                        )
                    ),
                    navController = navController,
                    onItemClick = {
                        navController.navigate(it.route)
                    }
                )
            }
        }
    ) {
        val userData = viewModel.userData
        Log.i("username",userData)
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .background(color = logoColor) //Changed
                .verticalScroll(state = scrollState)
        ) {
            TopCountriesText(userData = userDatas, onLogoutClicked = {
                viewModel.onEvent(NewsEvent.OnLogoutClicked)
            })
            Spacer(modifier = Modifier.height(7.dp))
            Row(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CountryListRow(
                    onCountryClicked = {
                        viewModel.onEvent(NewsEvent.OnCountryClicked(it))
                    },
                    newsState = viewModel.newsState
                )
            }
            SwipeRefresh(
                state = swipeToRefresh,
                onRefresh = {
                    viewModel.onEvent(NewsEvent.OnSwipeRefreshed)
                }
            ) {
                AnimatedVisibility(
                    visible = appName,
                    enter = expandVertically(
                        animationSpec = tween(
                            durationMillis = 300
                        )
                    ),
                    exit = shrinkVertically(
                        animationSpec = tween(
                            durationMillis = 700
                        )
                    )
                ) {
                    NewsListContent(
                        item = newsHeadlines,
                        loadingState = state.isLoading,
                        onArticleClicked = { url, source ->
                            navController.navigate(Screen.NewsWebViewScreen.route + "?article_url=$url&article_source=$source")
                        },
                        onEvent = viewModel::onEvent
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            CryptoNewsContent(
                item = allCryptoNews,
                onCryptoNewsClicked = { url , source ->
                    navController.navigate(Screen.NewsWebViewScreen.route + "?article_url=$url&article_source=$source")
                },
                loadingState = false
            )
            SportsNews()
            Spacer(modifier = Modifier.height(5.dp))
            SportsNewsContent(
                item = allSportsNews,
                loadingState = state.isLoading,
                onSportsNewsClicked = { url, source ->
                    navController.navigate(Screen.NewsWebViewScreen.route + "?article_url=$url&article_source=$source")
                }
            )
        }
    }
}
