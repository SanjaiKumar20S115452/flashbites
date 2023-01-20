package com.sanjai.newsappdemo.presentation.screen.news.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ContentAlpha.disabled
import androidx.compose.material.ContentAlpha.high
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.createSavedStateHandle
import com.sanjai.newsappdemo.presentation.screen.news.Country
import com.sanjai.newsappdemo.presentation.screen.news.CountryListScroll
import com.sanjai.newsappdemo.presentation.screen.news.NewsState
import com.sanjai.newsappdemo.presentation.screen.news.toRequestNames
import com.sanjai.newsappdemo.ui.theme.*
import retrofit2.http.Header

@Composable
fun CountryListRow(
    onCountryClicked: (CountryListScroll) -> Unit,
    newsState: NewsState
) {
    val countries = listOf(
        CountryListScroll.America,
        CountryListScroll.India,
        CountryListScroll.UK,
        CountryListScroll.Australia,
        CountryListScroll.Canada,
        CountryListScroll.Russia,
        CountryListScroll.China,
        CountryListScroll.Switzerland,
        CountryListScroll.Ukraine,
        CountryListScroll.Sweden,
        CountryListScroll.Singapore,
        CountryListScroll.Poland,
        CountryListScroll.Japan,
        CountryListScroll.HongKong,
        CountryListScroll.Brazil,
        CountryListScroll.Argentina,
        CountryListScroll.Belgium,
        CountryListScroll.Portugal,
        CountryListScroll.Thailand,
        CountryListScroll.Turkey,
        CountryListScroll.Bulgaria,
        CountryListScroll.Indonesia,
        CountryListScroll.Slovenia,
        CountryListScroll.Slovakia,
        CountryListScroll.Nigeria
    )
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(
            items = countries
        ) { country ->
            CountrySurface(
                country = country,
                onCountryClicked = onCountryClicked,
                newsState = newsState
            )
        }
    }
}

@Composable
fun CountrySurface(
    country: CountryListScroll,
    onCountryClicked: (CountryListScroll) -> Unit,
    newsState: NewsState
) {
//    Surface(
//        modifier = Modifier
//            .padding(4.dp)
//            .width(if (country.country.length > 6) 110.dp else 60.dp)
//            .height(23.dp)
//            .clickable {
//                onCountryClicked(country)
//            }
//            .border(
//                width = 1.dp,
//                color = if (
//                    country.countryQuery == newsState.newsCountry
//                ) reddish else Color.DarkGray.copy(alpha = 0.2f),
//                shape = RoundedCornerShape(30.dp)
//            ),
//        color = if(
//            country.countryQuery == newsState.newsCountry
//        ) reddish else
//            Color.LightGray.copy(0.2f),
//        shape = RoundedCornerShape(30.dp)
//    ) {
//    }
    Surface(
        modifier = Modifier
            .padding(end = 5.dp, top = 6.dp)
            .clickable {
                onCountryClicked(country)
            },
        color = if(country.countryQuery == newsState.newsCountry) Color(0xFFeaeaea).copy(alpha = 0.8f) else Color.Transparent,
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(7.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = country.image),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 5.dp)
                    .size(30.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = country.country,
                modifier = Modifier.padding(end = 4.dp),
                style = MaterialTheme.typography.body1,
                color = if(country.countryQuery == newsState.newsCountry) logoColor.copy(high) else HeaderColor.copy(high),
                fontWeight = if(country.countryQuery == newsState.newsCountry) FontWeight.Bold else FontWeight.Medium,
                fontFamily = sourceFontFamily,
                fontSize = 13.sp
            )
        }
    }
}