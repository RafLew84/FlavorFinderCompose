package com.example.flavorfindercompose.ui

sealed class Screens(
    val route: String
) {
    object Meals : Screens("meals")
    object Favorite : Screens("favorite")
}