package com.example.flavorfindercompose.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.flavorfindercompose.data.model.MealResponse
import com.example.flavorfindercompose.ui.Screens
import com.example.flavorfindercompose.util.Resource
import com.example.flavorfindercompose.viewmodel.FoodViewModel

@Composable
fun MealsScreen(navController: NavController, viewModel: FoodViewModel, paddingValues: PaddingValues) {
    val response by viewModel.meals.collectAsStateWithLifecycle()

    when (response) {
        is Resource.Success -> { response.data?.let { ShowList(meals = it, paddingValues, navController, viewModel) } }
        is Resource.Error -> { response.message?.let { ShowErrorMessage(message = it) } }
        is Resource.Loading -> { ShowLoadingBar() }
    }
}

@Composable
private fun ShowList(
    meals: MealResponse,
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: FoodViewModel
) {
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        items(meals.meals) { meal ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 4.dp)
                        .clickable {
                            viewModel.getMealById(meal.idMeal)
                            navController.navigate(Screens.Details.route)
                        }
                ) {
                    AsyncImage(
                        model = meal.strMealThumb,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Text(
                        text = meal.strMeal,
                        Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp
                    )
                }
                Spacer(modifier = Modifier.padding(12.dp))
            }
        }
    }
}