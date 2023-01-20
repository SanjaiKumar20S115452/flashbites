package com.sanjai.newsappdemo.presentation.screen.signUp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjai.newsappdemo.data.util.UiEvent
import com.sanjai.newsappdemo.domain.repository.AuthRepository
import com.sanjai.newsappdemo.presentation.screen.login.LoginScreenEvent
import com.sanjai.newsappdemo.presentation.screen.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
 ) : ViewModel() {

     private val _signUpEmail = mutableStateOf("")
     val signUpEmail: State<String> = _signUpEmail

    private val _signUpPassword = mutableStateOf("")
    val signUpPassword: State<String> = _signUpPassword

    private val _signUpUsername = mutableStateOf("")
    val signUpUsername: State<String> = _signUpUsername

    private val _signUpAge = mutableStateOf("")
    val signUpAge: State<String> = _signUpAge

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SignUpScreenEvent) {
        when(event) {
            is SignUpScreenEvent.SignUpEmail -> {
                _signUpEmail.value = event.signUpEmail
            }
            is SignUpScreenEvent.SignUpPassword -> {
                _signUpPassword.value = event.signUpPassword
            }
            is SignUpScreenEvent.SignUpUsername -> {
                _signUpUsername.value = event.signUpUsername
            }
            is SignUpScreenEvent.SignUpAge -> {
                _signUpAge.value = event.signUpAge
            }
            is SignUpScreenEvent.OnSignUpClicked -> {
                signUp()
            }
            is SignUpScreenEvent.OnLoginClicked -> {
                sendUiEvent(UiEvent.OnNavigate(route = Screen.LoginScreen.route))
            }
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            if(_signUpUsername.value.isEmpty()) {
                sendUiEvent(UiEvent.ShowSnackBar(message = "Username found empty"))
                return@launch
            }
            if(_signUpEmail.value.isEmpty()) {
                sendUiEvent(UiEvent.ShowSnackBar(message = "Email found empty"))
                return@launch
            }
            if(_signUpPassword.value.isEmpty()) {
                sendUiEvent(UiEvent.ShowSnackBar(message = "Password found empty"))
                return@launch
            }
            if(_signUpAge.value.isEmpty()) {
                sendUiEvent(UiEvent.ShowSnackBar(message = "Enter your age!"))
                return@launch
            }
            repository.signUp(
                email = _signUpEmail.value,
                password = _signUpPassword.value,
                username = _signUpUsername.value,
                age = _signUpAge.value.toInt(),
                usernameAlreadyExists = {
                    sendUiEvent(UiEvent.ShowSnackBar(message = "Username already exists! Try other names!"))
                },
                onSignUpCompleted = { result ->
                    if(result) {
                        sendUiEvent(UiEvent.ShowSnackBar(message = "Done!"))
                        sendUiEvent(UiEvent.OnNavigate(route = Screen.NewsScreen.route))
                    }
                },
                signUpException = {
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