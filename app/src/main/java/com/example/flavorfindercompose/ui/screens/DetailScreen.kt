package com.example.flavorfindercompose.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.flavorfindercompose.viewmodel.FoodViewModel

@Composable
fun DetailScreen(
    id: String,
    onHome: () -> Unit,
    viewModel: FoodViewModel
){
    Text(text = id)
}