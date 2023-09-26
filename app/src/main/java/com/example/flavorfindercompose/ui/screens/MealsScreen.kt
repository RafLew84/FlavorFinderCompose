package com.example.flavorfindercompose.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.flavorfindercompose.ui.screens.components.ShowErrorMessage
import com.example.flavorfindercompose.ui.screens.components.ShowList
import com.example.flavorfindercompose.ui.screens.components.ShowLoadingBar
import com.example.flavorfindercompose.util.Resource
import com.example.flavorfindercompose.viewmodel.FoodViewModel

@Composable
fun MealsScreen(navController: NavController, viewModel: FoodViewModel, paddingValues: PaddingValues) {
    val response by viewModel.meals.collectAsStateWithLifecycle()

    when (response) {
        is Resource.Success -> { response.data?.let { ShowList(meals = it.meals, paddingValues, navController, viewModel) } }
        is Resource.Error -> { response.message?.let { ShowErrorMessage(message = it) } }
        is Resource.Loading -> { ShowLoadingBar() }
    }
}