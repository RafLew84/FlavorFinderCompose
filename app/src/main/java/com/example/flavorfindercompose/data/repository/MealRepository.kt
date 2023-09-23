package com.example.flavorfindercompose.data.repository

import android.app.Application
import com.example.flavorfindercompose.data.api.RetrofitInstance
import com.example.flavorfindercompose.data.db.MealDatabase
import com.example.flavorfindercompose.data.model.Meal
import kotlinx.coroutines.flow.Flow

class MealRepository(private val application: Application) {

    private val mealDao = MealDatabase.getDatabase(application).mealDao()

    suspend fun getMeal() = RetrofitInstance.api.getFood()
    suspend fun getMealById(id: String) = RetrofitInstance.api.getFoodById(id)

    val readAllData: Flow<List<Meal>> = mealDao.getAllMeals()
    suspend fun insert(meal: Meal) = mealDao.insert(meal)
    suspend fun delete(meal: Meal) = mealDao.delete(meal)
}