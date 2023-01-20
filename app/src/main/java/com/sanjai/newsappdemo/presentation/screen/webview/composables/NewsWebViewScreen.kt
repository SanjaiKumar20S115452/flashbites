package com.sanjai.newsappdemo.presentation.screen.webview.composables

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.sanjai.newsappdemo.presentation.screen.webview.NewsWebViewViewModel
import com.sanjai.newsappdemo.ui.theme.logoColor
import com.sanjai.newsappdemo.ui.theme.sourceFontFamily

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewsWebViewScreen(
    viewModel: NewsWebViewViewModel = hiltViewModel()
) {

    val url = viewModel.url.value
    val source = viewModel.sourceName.value
    val fontSizing = if(source.length > 18) 18.sp else 24.sp

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(
                                color = Color(0xFFf1faee),
                                fontSize = 14.sp
                            )) {
                                append("SOURCE:  ")
                                withStyle(style = SpanStyle(
                                    color = Color(0xFFe63946),
                                    fontSize = fontSizing,
                                    fontWeight = FontWeight.Bold
                                )) {
                                    append(source)
                                }
                            }
                        },
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = sourceFontFamily
                    )
                },
                backgroundColor = logoColor
            )
        }
    ) {
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        }, update = {
            it.loadUrl(url)
        }
        )
    }

}