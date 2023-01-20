package com.sanjai.newsappdemo.presentation.screen.login.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ContentAlpha.disabled
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanjai.newsappdemo.R
import com.sanjai.newsappdemo.presentation.screen.login.LoginScreenEvent
import com.sanjai.newsappdemo.ui.theme.*
import kotlin.math.log2

@Composable
fun LoginScreenContent(
    email: String,
    password: String,
    onEvent: (LoginScreenEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(logoColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.report), contentDescription = null, modifier = Modifier.size(80.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = log1
                )) {
                    append("L")
                    withStyle(style = SpanStyle(
                        color = log2
                    )
                    ) {
                        append("O")
                        withStyle(style = SpanStyle(
                            color = log3
                        )) {
                            append("G")
                            withStyle(style = SpanStyle(
                                color = log4
                            )) {
                                append("I")
                                withStyle(style = SpanStyle(
                                    color = log5
                                )) {
                                    append("N")
                                }
                            }
                        }
                    }
                }
            },
            color = HeaderColor,
            fontSize = 30.sp,
            fontFamily = righetous
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
                onEvent(LoginScreenEvent.OnLoginEmail(it))
            },
            label = {
                Text(text = "Email", color = HeaderColor)
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = HeaderColor,
                focusedIndicatorColor = HeaderColor,
                unfocusedIndicatorColor = HeaderColor.copy(alpha = disabled)
            ),
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_email_lead), contentDescription = null, tint = HeaderColor)
            }
        )
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                onEvent(LoginScreenEvent.OnLoginPassword(it))
            },
            label = {
                Text(text = "Password", color = HeaderColor)
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = HeaderColor,
                focusedIndicatorColor = HeaderColor,
                unfocusedIndicatorColor = HeaderColor.copy(alpha = disabled)
            ),
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_password_lead), contentDescription = null, tint = HeaderColor)
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = {
            onEvent(LoginScreenEvent.OnLoginClicked)
        },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = HeaderColor,
                contentColor = logoColor
            ),
            modifier = Modifier
                .width(150.dp)
        ) {
            Text(text = "LOGIN", fontFamily = righetous)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Don't have an account ? sign In ?",
            modifier = Modifier.clickable {
                onEvent(LoginScreenEvent.OnSignUpClicked)
            },
            fontFamily = righetous2,
            color = HeaderColor,
            fontSize = 10.sp
        )
    }
}