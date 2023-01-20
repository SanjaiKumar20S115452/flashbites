package com.sanjai.newsappdemo.presentation.screen.signUp.composable

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.sanjai.newsappdemo.data.util.UiEvent
import com.sanjai.newsappdemo.presentation.screen.signUp.SignUpViewModel
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onNavigate: (UiEvent.OnNavigate) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when(event) {
                is UiEvent.OnNavigate -> {
                    onNavigate(event)
                }
                is UiEvent.ShowSnackBar -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    val username = viewModel.signUpUsername.value
    val email = viewModel.signUpEmail.value
    val password = viewModel.signUpPassword.value
    val age = viewModel.signUpAge.value
    Scaffold {
        SignUpScreenContent(
            username = username,
            email = email,
            password = password,
            age = age,
            onEvent = viewModel::onEvent
        )
    }
}