package com.sanjai.newsappdemo.presentation.screen.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjai.newsappdemo.data.util.UiEvent
import com.sanjai.newsappdemo.domain.repository.AuthRepository
import com.sanjai.newsappdemo.presentation.screen.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
 ) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

     private val _loginEmail = mutableStateOf("")
     val loginEmail: State<String> = _loginEmail

    private val _loginPassword = mutableStateOf("")
    val loginPassword: State<String> = _loginPassword

    fun onEvent(event: LoginScreenEvent) {
        when(event) {
            is LoginScreenEvent.OnLoginClicked -> {
                login()
            }
            is LoginScreenEvent.OnSignUpClicked -> {
                sendUiEvent(UiEvent.OnNavigate(route = Screen.SignUpScreen.route))
            }
            is LoginScreenEvent.OnLoginEmail -> {
                _loginEmail.value = event.loginEmail
            }
            is LoginScreenEvent.OnLoginPassword -> {
                _loginPassword.value = event.loginPassword
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            if(_loginEmail.value.isEmpty()) {
                sendUiEvent(UiEvent.ShowSnackBar(message = "Fill in the Email Address to Proceed!"))
                return@launch
            }
            if(_loginPassword.value.isEmpty()) {
                sendUiEvent(UiEvent.ShowSnackBar(message = "Fill in the Password to Proceed!"))
                return@launch
            }
            repository.login(
                _loginEmail.value,
                _loginPassword.value,
                onLoginCompleted = { result ->
                    if(result) {
                        sendUiEvent(UiEvent.ShowSnackBar(message = "Done!"))
                        sendUiEvent(UiEvent.OnNavigate(route = Screen.NewsScreen.route))
                    }
                },
                loginException = {
                    sendUiEvent(UiEvent.ShowSnackBar(message = it))
                }
            )
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

 }