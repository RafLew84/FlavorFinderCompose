package com.example.flavorfindercompose.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.flavorfindercompose.ui.screens.components.ShowList
import com.example.flavorfindercompose.viewmodel.FoodViewModel

@Composable
fun FavoriteScreen(navController: NavController, viewModel: FoodViewModel, paddingValues: PaddingValues){

    val meals by viewModel.localMeals.collectAsStateWithLifecycle()

    ShowList(
        meals = meals,
        paddingValues = paddingValues,
        navController = navController,
        viewModel = viewModel
    )
}