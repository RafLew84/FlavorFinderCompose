package com.example.flavorfindercompose.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBar(Screens.Meals.route, "Meals", Icons.Default.Home)
    object Favorite : BottomBar(Screens.Favorite.route, "Favorite", Icons.Default.Favorite)
}