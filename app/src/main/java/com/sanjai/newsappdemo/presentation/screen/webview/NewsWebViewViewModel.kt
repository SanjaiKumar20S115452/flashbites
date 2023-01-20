package com.sanjai.newsappdemo.presentation.screen.webview

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsWebViewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _url: MutableState<String> = mutableStateOf("")
    val url: State<String> = _url

    private val _sourceName: MutableState<String> = mutableStateOf("")
    val sourceName: State<String> = _sourceName

    init {
        savedStateHandle.get<String>("article_url")!!.let {
            _url.value = it
        }
    }

    init {
        savedStateHandle.get<String>("article_source")!!.let {
            _sourceName.value = it
        }
    }

}