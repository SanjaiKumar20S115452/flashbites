package com.sanjai.newsappdemo.presentation.screen.saved.composables

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toDrawable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sanjai.newsappdemo.R
import com.sanjai.newsappdemo.data.model.world_news.SaveArticle
import com.sanjai.newsappdemo.data.util.BottomNavItem
import com.sanjai.newsappdemo.data.util.UiEvent
import com.sanjai.newsappdemo.domain.model.world_news.ArticleListing
import com.sanjai.newsappdemo.presentation.screen.navigation.BottomBarNavigation
import com.sanjai.newsappdemo.presentation.screen.navigation.Screen
import com.sanjai.newsappdemo.presentation.screen.saved.SavedScreenEvent
import com.sanjai.newsappdemo.presentation.screen.saved.SavedScreenViewModel
import com.sanjai.newsappdemo.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SavedScreen(
    navController: NavHostController,
    viewModel: SavedScreenViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    LaunchedEffect(key1 = true) {
        viewModel.getAllSavedNewsArticles()
        viewModel.uiEvent.collectLatest { event ->
            when(event) {
                is UiEvent.ShowSnackBar -> {
                    val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.actionLabel
                    )
                    if(snackBarResult == SnackbarResult.ActionPerformed) {
//                        viewModel.onEvent(SavedScreenEvent.OnUndoClicked)
                    }
                }
            }
        }
    }
    val scope = rememberCoroutineScope()
    val allSavedNews by viewModel.allSavedNews.collectAsState()
    Log.i("saved",allSavedNews.toString())

    var appName by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        scope.launch {
            delay(300L)
            appName = true
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(hostState = it) { data ->
                Snackbar(
                    actionColor = if(isSystemInDarkTheme()) HeaderColor else HeaderColor,
                    snackbarData = data,
                    contentColor = HeaderColor,
                    backgroundColor = if(isSystemInDarkTheme()) Color(0xFF353535) else Color(0xFF353535)
                )
            }
        },
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
                        visible = appName,
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
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.NewsScreen.route)
                },
                modifier = Modifier
                    .padding(bottom = 40.dp),
                backgroundColor = Color(0xFFe63946),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = null,
                    tint = Color.White
                )
            }
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
                            icon = Icons.Filled.Favorite,
                            badgeCount = allSavedNews.size
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
                    .padding(all = 10.dp)
                    .fillMaxSize()
            ) {
                SavedHeadlines()
                AnimatedVisibility(
                    visible = appName,
                    enter = expandVertically(
                        animationSpec = tween(
                            durationMillis = 300
                        )
                    ),
                    exit = shrinkVertically(
                        animationSpec = tween(
                            durationMillis = 300
                        )
                    )
                ) {
                    SavedScreenContent(
                        item = allSavedNews,
                        contentScale = ContentScale.Crop,
                        onArticleClicked = { url, source ->
                            navController.navigate(Screen.NewsWebViewScreen.route + "?article_url=$url&article_source=$source")
                        },
                        onDeleteArticleClicked = {
                            it.articleId?.let { it1 ->
                                SavedScreenEvent.OnDeleteArticleClicked(
                                    it1, articleListing = it)
                            }?.let { it2 -> viewModel.onEvent(it2) }
                        },
                        onFindArticlesClicked = {
                            navController.navigate(Screen.NewsScreen.route) {
                                popUpTo(Screen.NewsScreen.route) { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SavedHeadlines() {
    Text(text = buildAnnotatedString {
        withStyle(style = SpanStyle(
            color = HeaderColor,
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold
        )) {
            append("Saved News ")
        }
        withStyle(style = SpanStyle(
            color = reddish,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )
        ) {
            append(" Articles !")
        }
    },
        fontFamily = sourceFontFamily,
        fontWeight = FontWeight.ExtraBold,
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp)
    )
}

@Composable
fun SavedScreenContent(
    item: List<SaveArticle>,
    contentScale: ContentScale,
    onArticleClicked: (String, String) -> Unit,
    onDeleteArticleClicked: (SaveArticle) -> Unit,
    onFindArticlesClicked: () -> Unit
) {
    if(item.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No saved articles found !",
                    fontWeight = FontWeight.Light,
                    fontFamily = righetous2,
                    color = HeaderColor
                )
                Spacer(modifier = Modifier.height(5.dp))
                Button(onClick = {
                    onFindArticlesClicked()
                }, colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = reddish
                ),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Text(
                        text = "Find Articles !",
                        fontWeight = FontWeight.Light,
                        fontFamily = righetous2
                    )
                }
            }
        }
    }else {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(
                items = item
            ) { article ->
                SavedScreenContentItem(
                    saveArticle = article,
                    onArticleClicked = onArticleClicked,
                    contentScale = contentScale,
                    onDeleteArticleClicked = onDeleteArticleClicked
                )
            }
        }
    }
}

@Composable
fun SavedScreenContentItem(
    saveArticle: SaveArticle,
    onArticleClicked: (String, String) -> Unit,
    contentScale: ContentScale,
    onDeleteArticleClicked: (SaveArticle) -> Unit
) {
    Box(
        modifier = Modifier
            .height(250.dp)
            .clickable {
                onArticleClicked(saveArticle.article?.url!!, saveArticle.article.source?.name!!)
            }
            .background(color = logoColor),
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            modifier = Modifier
                .padding(all = 12.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("${saveArticle.article?.urlToImage}")
                    .crossfade(true)
                    .crossfade(500)
                    .error(drawable = R.drawable.pl.toDrawable())
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth(),
                contentScale = contentScale
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Surface(
                    modifier = Modifier
                        .size(30.dp),
                    color = HeaderColor,
                    shape = RoundedCornerShape(20.dp)
                ) {
                    IconButton(
                        onClick = {
                            onDeleteArticleClicked(saveArticle)
                        },
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_remove_circle_outline_24),
                            contentDescription = null,
                            tint = logoColor
                        )
                    }
                }
            }
        }
        Surface(
            modifier = Modifier
                .padding(all = 12.dp)
                .fillMaxHeight(0.3f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = 20.dp,
                bottomEnd = 20.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .clickable {
                        onArticleClicked(saveArticle.article?.url!!, saveArticle.article.source?.name!!)
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                saveArticle.article.let {
                    it?.title?.let { it1 ->
                        Text(
                            text = it1,
                            style = MaterialTheme.typography.caption,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center,
                            color = Color.White.copy(alpha = ContentAlpha.medium),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 3
                        )
                    }
                }
            }
        }
    }
}