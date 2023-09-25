package com.example.flavorfindercompose.ui

sealed class Screens(
    val route: String
) {
    object Meals : Screens("meals")
    object Favorite : Screens("favorite")
    object Details : Screens("detail")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}