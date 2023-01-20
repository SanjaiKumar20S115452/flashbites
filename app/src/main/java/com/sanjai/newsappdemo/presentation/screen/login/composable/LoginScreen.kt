package com.sanjai.newsappdemo.presentation.screen.login.composable

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.sanjai.newsappdemo.data.util.UiEvent
import com.sanjai.newsappdemo.presentation.screen.login.LoginViewModel
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigate: (UiEvent.OnNavigate) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when(event) {
                is UiEvent.ShowSnackBar -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is UiEvent.OnNavigate -> {
                    onNavigate(event)
                }
            }
        }
    }
    val email = viewModel.loginEmail.value
    val password = viewModel.loginPassword.value
    Scaffold {
        LoginScreenContent(
            email = email,
            password = password,
            onEvent = viewModel::onEvent
        )
    }
}