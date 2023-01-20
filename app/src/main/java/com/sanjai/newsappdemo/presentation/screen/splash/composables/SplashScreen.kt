package com.sanjai.newsappdemo.presentation.screen.splash.composables

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sanjai.newsappdemo.R
import com.sanjai.newsappdemo.presentation.screen.navigation.Screen
import com.sanjai.newsappdemo.presentation.screen.splash.SplashViewModel

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val onBoardingState by viewModel.onBoardingPage.collectAsState()
    val rotate = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        rotate.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navController.popBackStack()
        if(onBoardingState) {
            if(viewModel.currentUser) {
                navController.navigate(Screen.NewsScreen.route)
            }else {
                navController.navigate(Screen.LoginScreen.route)
            }
        }else {
            navController.navigate(Screen.WelcomeScreen.route)
        }
    }
    Splash(degree = rotate.value)
}

@Composable
fun Splash(degree: Float) {
    if(isSystemInDarkTheme()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier
                    .rotate(degree)
            )
        }

    }else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier
                    .rotate(degree)
            )
        }
    }

}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash(200f)
}