package com.sanjai.newsappdemo.presentation.screen.search.composables

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sanjai.newsappdemo.R
import com.sanjai.newsappdemo.data.util.BottomNavItem
import com.sanjai.newsappdemo.data.util.UiEvent
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing
import com.sanjai.newsappdemo.presentation.screen.navigation.BottomBarNavigation
import com.sanjai.newsappdemo.presentation.screen.navigation.Screen
import com.sanjai.newsappdemo.presentation.screen.news.NewsEvent
import com.sanjai.newsappdemo.presentation.screen.news.NewsViewModel
import com.sanjai.newsappdemo.presentation.screen.news.composables.ArticleListItem
import com.sanjai.newsappdemo.presentation.screen.search.SearchCountries
import com.sanjai.newsappdemo.presentation.screen.search.SearchNewsEvent
import com.sanjai.newsappdemo.presentation.screen.search.SearchedNewsViewModel
import com.sanjai.newsappdemo.presentation.screen.search.toCustomCountriesName
import com.sanjai.newsappdemo.ui.theme.*
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun SearchScreen(
    viewModel: SearchedNewsViewModel = hiltViewModel(),
    navController: NavHostController,
    newsViewModel: NewsViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val searchedNews by viewModel.searchedNews.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    var appNameState by remember {
        mutableStateOf(false)
    }
    val searchContext = LocalContext.current
    LaunchedEffect(key1 = true) {
        appNameState = true
        newsViewModel.uiEvent.collectLatest { event ->
            when(event) {
                is UiEvent.ShowSnackBar -> {
                    Toast.makeText(searchContext, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    Scaffold(
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
                        visible = appNameState,
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
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = logoColor
        ) {
            Column(
                modifier = Modifier
                    .padding(all = 15.dp)
            ) {
                SearchYourHeadlines()
                Spacer(modifier = Modifier.height(10.dp))
                AnimatedVisibility(
                    visible = state.searchedCountries.name.isNotEmpty()
                ) {
                    SearchTextField(
                        text = state.query,
                        onTextChange = {
                            viewModel.onEvent(SearchNewsEvent.OnSearchQueryChange(it))
                        },
                        onCloseClicked = { /*TODO*/ },
                        clickedCountry = state.searchedCountries.name
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                AnimatedVisibility(
                    visible = appNameState,
                    enter = slideInHorizontally(
                        animationSpec = tween(
                            durationMillis = 300,
                            delayMillis = 300,
                            easing = LinearOutSlowInEasing
                        )
                    )
                ) {
                    PickCountries(
                        item = listOf(
                            SearchCountries(
                                name = "America",
                                icon = R.drawable.united_states
                            ),
                            SearchCountries(
                                name = "India",
                                icon = R.drawable.india
                            ),
                            SearchCountries(
                                name = "Canada",
                                icon = R.drawable.canada
                            ),
                            SearchCountries(
                                name = "UK",
                                icon = R.drawable.united_kingdom
                            ),
                            SearchCountries(
                                name = "Australia",
                                icon = R.drawable.australia
                            )
                        ),
                        onCountryClicked = {
                            viewModel.onEvent(SearchNewsEvent.OnCountryClicked(it))
                        }
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                SearchedScreenListContent(
                    item = searchedNews,
                    loadingState = state.isLoading,
                    onArticleClicked = { url, source ->
                        navController.navigate(Screen.NewsWebViewScreen.route + "?article_url=$url&article_source=$source")
                    },
                    onEvent = newsViewModel::onEvent,
                    searchQuery = state.query
                )
            }
        }
    }
}

@Composable
fun SearchedScreenListContent(
    item: List<ArticleListing>,
    loadingState: Boolean,
    onArticleClicked: (String, String) -> Unit,
    onEvent: (NewsEvent) -> Unit,
    searchQuery: String
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
        if(item.isNotEmpty()) {
            LazyColumn {
                items(
                    items = item
                ) { article ->
                    ArticleListItem(
                        articleListing = article,
                        onArticleClicked = onArticleClicked,
                        onEvent = onEvent
                    )
                }
            }
        }else {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No results found for '$searchQuery'",
                    fontWeight = FontWeight.Light,
                    fontFamily = righetous2,
                    color = HeaderColor,
                    modifier = Modifier
                        .padding(top = 60.dp)
                )
            }
        }
    }
}

@Composable
fun SearchTextField(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    clickedCountry: String
) {
    OutlinedTextField(
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        modifier = Modifier
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = "Search $clickedCountry headlines",
                fontWeight = FontWeight.Light,
                fontFamily = righetous2
            )
        },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = HeaderColor,
            backgroundColor = HeaderColor.copy(alpha = ContentAlpha.disabled),
            disabledBorderColor = HeaderColor.copy(alpha = ContentAlpha.disabled),
            focusedBorderColor = HeaderColor.copy(alpha = ContentAlpha.disabled),
            placeholderColor = HeaderColor
        ),
        trailingIcon = {
            if(text.isNotEmpty()) {
                IconButton(
                    onClick = {
                        if(text.isNotEmpty()) {
                            onTextChange("")
                        }else {
                            onCloseClicked()
                        }
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        tint = HeaderColor.copy(alpha = ContentAlpha.medium)
                    )
                }
            }
        }
    )

}

@Composable
fun PickCountries(
    item: List<SearchCountries>,
    onCountryClicked: (SearchCountries) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(
            items = item
        ) { country ->
            SearchCountryItem(
                searchCountries = country,
                onCountryClicked = onCountryClicked
            )
        }
    }
}

@Composable
fun SearchCountryItem(
    searchCountries: SearchCountries,
    onCountryClicked: (SearchCountries) -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                onCountryClicked(searchCountries)
            }
            .padding(end = 25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = searchCountries.icon),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = searchCountries.name,
            color = HeaderColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = sourceFontFamily
        )
    }
}

@Composable
fun SearchYourHeadlines() {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = HeaderColor,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            ) {
                append("Search your ")
            }
            withStyle(
                style = SpanStyle(
                    color = reddish,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            ) {
                append("Headlines !")
            }
        },
        fontFamily = sourceFontFamily,
        fontWeight = FontWeight.ExtraBold,
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp)
    )
}