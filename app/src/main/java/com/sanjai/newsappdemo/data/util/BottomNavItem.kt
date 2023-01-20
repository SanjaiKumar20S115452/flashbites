package com.sanjai.newsappdemo.data.util

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
)