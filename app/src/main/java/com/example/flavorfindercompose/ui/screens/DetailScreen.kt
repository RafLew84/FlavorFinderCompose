package com.example.flavorfindercompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.flavorfindercompose.data.model.Meal
import com.example.flavorfindercompose.util.Resource
import com.example.flavorfindercompose.viewmodel.FoodViewModel

@Composable
fun DetailScreen(
    viewModel: FoodViewModel,
    paddingValues: PaddingValues
){
    val response by viewModel.meal.collectAsStateWithLifecycle()

    when (response) {
        is Resource.Success -> {
            response.data?.let {
                val meal = it.meals.first()
                ShowMeal(meal, viewModel, paddingValues)
            }
        }
        is Resource.Error -> { response.message?.let { ShowErrorMessage(message = it) } }
        is Resource.Loading -> { ShowLoadingBar() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowMeal(
    meal: Meal,
    viewModel: FoodViewModel,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = Modifier.padding(paddingValues),
        floatingActionButton = {
            AddToFavoriteFAB(onClick = { viewModel.insert(meal) })
        },
    ) { scaffoldPaddingValues ->
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(scaffoldPaddingValues)
                .verticalScroll(rememberScrollState()),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            ) {
                MealImage(meal.strMealThumb)
                Gradient()
                Text(
                    text = meal.strMeal,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp),
                    fontSize = 24.sp,
                    style = TextStyle(color = Color.White),
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = meal.strCategory,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = meal.strInstructions,
                    modifier = Modifier.padding(start = 16.dp, top = 24.dp, end = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun Gradient() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black
                    ),
                    startY = 450f
                )
            )
    )
}

@Composable
private fun MealImage(url: String) {
    AsyncImage(
        model = url,
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun AddToFavoriteFAB(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(10.dp),
        containerColor = Color(100, 220, 237),
        shape = FloatingActionButtonDefaults.smallShape
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add item"
        )
    }
}