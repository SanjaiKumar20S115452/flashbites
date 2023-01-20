package com.sanjai.newsappdemo.presentation.screen.signUp.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import com.sanjai.newsappdemo.presentation.screen.signUp.SignUpScreenEvent
import com.sanjai.newsappdemo.ui.theme.*

@Composable
fun SignUpScreenContent(
    username: String,
    email: String,
    password: String,
    age: String,
    onEvent: (SignUpScreenEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = logoColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.report), contentDescription = null, modifier = Modifier.size(80.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = log1
                )
                ) {
                    append("S")
                    withStyle(style = SpanStyle(
                        color = log2
                    )
                    ) {
                        append("I")
                        withStyle(style = SpanStyle(
                            color = log3
                        )
                        ) {
                            append("G")
                            withStyle(style = SpanStyle(
                                color = log4
                            )
                            ) {
                                append("N")
                                withStyle(style = SpanStyle(
                                    color = log5
                                )
                                ) {
                                    append(" U")
                                    withStyle(style = SpanStyle(
                                        color = log1
                                    )
                                    ) {
                                        append("P")
                                    }
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
            value = username,
            onValueChange = {
                onEvent(SignUpScreenEvent.SignUpUsername(it))
            },
            label = {
                Text(text = "Username", color = HeaderColor)
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = HeaderColor,
                focusedIndicatorColor = HeaderColor,
                unfocusedIndicatorColor = HeaderColor.copy(alpha = ContentAlpha.disabled)
            ),
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_username_lead), contentDescription = null, tint = HeaderColor)
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
                onEvent(SignUpScreenEvent.SignUpEmail(it))
            },
            label = {
                Text(text = "Email", color = HeaderColor)
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = HeaderColor,
                focusedIndicatorColor = HeaderColor,
                unfocusedIndicatorColor = HeaderColor.copy(alpha = ContentAlpha.disabled)
            ),
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_email_lead), contentDescription = null, tint = HeaderColor)
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                onEvent(SignUpScreenEvent.SignUpPassword(it))
            },
            label = {
                Text(text = "Password", color = HeaderColor)
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = HeaderColor,
                focusedIndicatorColor = HeaderColor,
                unfocusedIndicatorColor = HeaderColor.copy(alpha = ContentAlpha.disabled)
            ),
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_password_lead), contentDescription = null, tint = HeaderColor)
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = age,
            onValueChange = {
                onEvent(SignUpScreenEvent.SignUpAge(it))
            },
            label = {
                Text(text = "Age", color = HeaderColor)
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = HeaderColor,
                focusedIndicatorColor = HeaderColor,
                unfocusedIndicatorColor = HeaderColor.copy(alpha = ContentAlpha.disabled)
            ),
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_age_lead), contentDescription = null, tint = HeaderColor)
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = {
            onEvent(SignUpScreenEvent.OnSignUpClicked)
        },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = HeaderColor,
                contentColor = logoColor
            ),
            modifier = Modifier
                .width(150.dp)
        ) {
            Text(text = "SIGN UP", fontFamily = righetous)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Already have an account ? Login ?",
            modifier = Modifier
                .clickable {
                    onEvent(SignUpScreenEvent.OnLoginClicked)
                },
            fontFamily = righetous2,
            color = HeaderColor,
            fontSize = 10.sp
        )
    }
}