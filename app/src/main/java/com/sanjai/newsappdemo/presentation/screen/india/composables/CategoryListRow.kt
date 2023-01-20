package com.sanjai.newsappdemo.presentation.screen.india.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sanjai.newsappdemo.presentation.screen.india.Category
import com.sanjai.newsappdemo.presentation.screen.india.toCustomCategoryNames
import com.sanjai.newsappdemo.ui.theme.sourceFontFamily

@Composable
fun CategoryListRow(
    onCategoryClicked: (Category) -> Unit
) {
    val category = listOf(
        Category.entertainment,
        Category.technology,
        Category.health,
        Category.science,
        Category.business,
        Category.general,
        Category.sports
    )
    LazyRow(
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth()
    ) {
        items(
            items = category
        ) { category ->
            CategorySurface(
                category = category,
                onCategoryClicked = onCategoryClicked
            )
        }
    }
}

@Composable
fun CategorySurface(
    category: Category,
    onCategoryClicked: (Category) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(end = 3.dp, start = 3.dp)
            .width(if(category.category.length >= 10) 125.dp else 110.dp)
            .height(40.dp)
            .clickable {
                onCategoryClicked(category)
            },
        shape = RoundedCornerShape(30.dp),
        elevation = 30.dp,
        color = Color(0xFF3d5a80)
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = category.toCustomCategoryNames(),
                style = MaterialTheme.typography.body1,
                color = Color(0xFFf1faee),
                fontWeight = FontWeight.Bold,
                fontFamily = sourceFontFamily
            )
        }
    }
}