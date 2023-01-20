package com.sanjai.newsappdemo.presentation.screen.welcome

import androidx.annotation.DrawableRes
import com.sanjai.newsappdemo.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First: OnBoardingPage(
        image = R.drawable.greetings,
        title = "Greetings",
        description = "It's amazing that the amount of news that happens in the world every day always just exactly fits the newspaper."
    )
    object Second: OnBoardingPage(
        image = R.drawable.explore,
        title = "Explore",
        description = "Headlines, in a way, are what mislead you because bad news is a headline, and gradual improvement is not."
    )
    object Third: OnBoardingPage(
        image = R.drawable.power,
        title = "Power",
        description = "I don't have time for the news clippings. I got my own mission"
    )
}
