package com.example.flavorfindercompose.ui

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flavorfindercompose.ui.screens.MealsScreen
import com.example.flavorfindercompose.viewmodel.FoodViewModel
import com.example.flavorfindercompose.viewmodel.FoodViewModelFactory

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: FoodViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "TaskViewModel",
        FoodViewModelFactory(LocalContext.current.applicationContext as Application)
    )

    NavHost(navController = navController, startDestination = Screens.Meals.route) {
        composable(route = Screens.Meals.route){
            MealsScreen (
                navController = navController,
                viewModel = viewModel
            )
        }

//        composable(route = Screens.Favorite.route){
//            FavoriteScreen(
//                onHome = { navController.popBackStack() },
//                viewModel = viewModel
//            )
//        }

//        composable(route = Screens.Update.route + "/{arg}"){
//            val arg = it.arguments?.getString("arg")
//            if (arg != null)
//                UpdateTaskScreen(
//                    id = arg,
//                    viewModel = viewModel,
//                    onHome = { navController.popBackStack() }
//                )
//        }
    }
}