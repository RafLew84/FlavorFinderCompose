package com.example.flavorfindercompose.ui

import android.app.Application
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.flavorfindercompose.ui.screens.FavoriteScreen
import com.example.flavorfindercompose.ui.screens.MealsScreen
import com.example.flavorfindercompose.viewmodel.FoodViewModel
import com.example.flavorfindercompose.viewmodel.FoodViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: FoodViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "TaskViewModel",
        FoodViewModelFactory(LocalContext.current.applicationContext as Application)
    )

    Scaffold(
        bottomBar = { BottomMenu(navController = navController)},
        content = { paddingValues ->
            BottomNavGraph(navController = navController, viewModel, paddingValues)
        }
    )

//        composable(route = Screens.Update.route + "/{arg}"){
//            val arg = it.arguments?.getString("arg")
//            if (arg != null)
//                UpdateTaskScreen(
//                    id = arg,
//                    viewModel = viewModel,
//                    onHome = { navController.popBackStack() }
//                )
//        }
//    }
}

@Composable
fun BottomNavGraph(navController: NavHostController, viewModel: FoodViewModel, paddingValues: PaddingValues){
    NavHost(navController = navController, startDestination = Screens.Meals.route) {
        composable(route = Screens.Meals.route){
            MealsScreen (
                navController = navController,
                viewModel = viewModel,
                paddingValues = paddingValues
            )
        }

        composable(route = Screens.Favorite.route){
            FavoriteScreen(
                navController = navController,
                viewModel = viewModel,
                paddingValues = paddingValues
            )
        }
    }
}

@Composable
fun BottomMenu(navController: NavHostController){
    val screens = listOf(
        BottomBar.Home, BottomBar.Favorite
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar{
        screens.forEach{screen ->
            NavigationBarItem(
                label = { Text(text = screen.title)},
                icon = {Icon(imageVector = screen.icon, contentDescription = "icon")},
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {navController.navigate(screen.route)}
            )
        }
    }
}