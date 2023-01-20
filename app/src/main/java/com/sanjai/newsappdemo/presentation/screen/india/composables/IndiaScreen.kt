package com.sanjai.newsappdemo.presentation.screen.india.composables

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sanjai.newsappdemo.data.util.BottomNavItem
import com.sanjai.newsappdemo.data.util.UiEvent
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing
import com.sanjai.newsappdemo.presentation.screen.india.IndianNewsEvent
import com.sanjai.newsappdemo.presentation.screen.india.IndianNewsViewModel
import com.sanjai.newsappdemo.presentation.screen.navigation.BottomBarNavigation
import com.sanjai.newsappdemo.presentation.screen.navigation.Screen
import com.sanjai.newsappdemo.presentation.screen.news.NewsEvent
import com.sanjai.newsappdemo.presentation.screen.news.NewsViewModel
import com.sanjai.newsappdemo.presentation.screen.news.composables.ArticleListItem
import com.sanjai.newsappdemo.ui.theme.HeaderColor
import com.sanjai.newsappdemo.ui.theme.logoColor
import com.sanjai.newsappdemo.ui.theme.righetous
import com.sanjai.newsappdemo.ui.theme.sourceFontFamily
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun IndiaScreen(
    viewModel: IndianNewsViewModel = hiltViewModel(),
    navController: NavHostController,
    newsViewModel: NewsViewModel = hiltViewModel()
) {
    val indianNewsHeadlines by viewModel.indianCategoryNews.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val indianNewsState = viewModel.indianNewsState
    val scaffoldState = rememberScaffoldState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = indianNewsState.isRefreshing)
    var appNameAnimation by remember {
        mutableStateOf(false)
    }
    val indiaContext = LocalContext.current
    LaunchedEffect(key1 = true) {
        appNameAnimation = true
        newsViewModel.uiEvent.collectLatest { event ->
            when(event) {
                is UiEvent.ShowSnackBar -> {
                    Toast.makeText(indiaContext, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when(event) {
                is UiEvent.ShowSnackBar -> {
                    Toast.makeText(indiaContext, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                                  },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null,
                            tint = HeaderColor
                        )
                    }
                },
                title = {
                    AnimatedVisibility(
                        visible = appNameAnimation,
                        enter = slideInVertically(
                            animationSpec = tween(
                                durationMillis = 500,
                                delayMillis = 500,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(
                                    fontSize = 20.sp,
                                    color = Color(0xFFef476f), //Changed
                                    fontWeight = FontWeight.ExtraBold)
                                ) {
                                    append("F")
                                }
                                withStyle(style = SpanStyle(
                                    fontSize = 20.sp,
                                    color = Color(0xFFffd166), //Changed
                                    fontWeight = FontWeight.ExtraBold
                                )
                                ) {
                                    append("L")
                                }
                                withStyle(style = SpanStyle(
                                    fontSize = 20.sp,
                                    color = Color(0xFF06d6a0), //Changed
                                    fontWeight = FontWeight.ExtraBold
                                )
                                ) {
                                    append("A")
                                }
                                withStyle(style = SpanStyle(
                                    fontSize = 20.sp,
                                    color = Color(0xFF118ab2), //Changed
                                    fontWeight = FontWeight.ExtraBold
                                )
                                ) {
                                    append("S")
                                }
                                withStyle(style = SpanStyle(
                                    fontSize = 20.sp,
                                    color = Color(0xFF90dbf4), //Changed
                                    fontWeight = FontWeight.ExtraBold
                                )
                                ) {
                                    append("H")
                                }
                                withStyle(style = SpanStyle(
                                    fontSize = 20.sp,
                                    color = Color(0xFFef476f), //Changed
                                    fontWeight = FontWeight.ExtraBold
                                )
                                ) {
                                    append(" B")
                                }
                                withStyle(style = SpanStyle(
                                    fontSize = 20.sp,
                                    color = Color(0xFFffd166), //Changed
                                    fontWeight = FontWeight.ExtraBold
                                )
                                ) {
                                    append("I")
                                }
                                withStyle(style = SpanStyle(
                                    fontSize = 20.sp,
                                    color = Color(0xFF06d6a0), //Changed
                                    fontWeight = FontWeight.ExtraBold
                                )
                                ) {
                                    append("T")
                                }
                                withStyle(style = SpanStyle(
                                    fontSize = 20.sp,
                                    color = Color(0xFF118ab2), //Changed
                                    fontWeight = FontWeight.ExtraBold
                                )
                                ) {
                                    append("E")
                                }
                                withStyle(style = SpanStyle(
                                    fontSize = 20.sp,
                                    color = Color(0xFF90dbf4), //Changed
                                    fontWeight = FontWeight.ExtraBold
                                )
                                ) {
                                    append("S")
                                }
                            },
                            letterSpacing = 3.sp,
                            fontFamily = righetous,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                },
                backgroundColor = logoColor
            )
        },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .background(color = logoColor)
        ) {
            WhatsHappening()
            CategoryListRow(onCategoryClicked = {
                viewModel.onEvent(IndianNewsEvent.OnCategoryClicked(it))
            })
            Spacer(modifier = Modifier.height(5.dp))
            SwipeRefresh(state = swipeRefreshState, onRefresh = {
                viewModel.onEvent(IndianNewsEvent.OnSwipeToRefresh)
            }) {
                IndianNewsContent(
                    item = indianNewsHeadlines,
                    loadingState = indianNewsState.isLoading,
                    onIndianArticleClicked = { url, source ->
                        navController.navigate(
                            Screen.NewsWebViewScreen.route + "?article_url=$url&article_source=$source"
                        )
                    },
                    onEvent = newsViewModel::onEvent
                )
            }
        }
    }
}

@Composable
fun WhatsHappening() {
    Surface(
        modifier = Modifier
            .padding(start = 10.dp, end = 40.dp, bottom = 5.dp)
            .fillMaxWidth(),
        color = logoColor
    ) {
        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle(
                color = Color(0xFFedf2f4),
                fontSize = 17.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 3.sp
            )) {
                append("What's Happening in ".uppercase())
            }
            withStyle(style = SpanStyle(
                color = Color(0xFFFF9933),
                fontSize = 27.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp
            )) {
                append("IN")
            }
            withStyle(style = SpanStyle(
                color = Color(0xFFFFFFFF),
                fontSize = 27.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp
            )) {
                append("D")
            }
            withStyle(style = SpanStyle(
                color = Color(0xFF138808),
                fontSize = 27.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp
            )) {
                append("IA")
            }
        },
            textAlign = TextAlign.Center,
            fontFamily = sourceFontFamily,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun IndianNewsContent(
    item: List<ArticleListing>,
    loadingState: Boolean,
    onIndianArticleClicked: (String, String) -> Unit,
    onEvent: (NewsEvent) -> Unit
) {
    if(loadingState) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = Color(0xffe63946)
            )
        }
    }else {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(
                items = item
            ) { article ->
                ArticleListItem(
                    articleListing = article,
                    onArticleClicked = onIndianArticleClicked,
                    contentScale = ContentScale.Fit,
                    onEvent = onEvent
                )
            }
        }
    }
}
