package com.example.flavorfindercompose.data.repository

import android.app.Application
import com.example.flavorfindercompose.data.api.RetrofitInstance
import com.example.flavorfindercompose.data.db.MealDatabase
import com.example.flavorfindercompose.data.model.Meal
import kotlinx.coroutines.flow.Flow

class FoodRepository(private val application: Application) {

    private val foodDao = MealDatabase.getDatabase(application).foodDao()

    suspend fun getFood() = RetrofitInstance.api.getFood()
    suspend fun getFoodById(id: String) = RetrofitInstance.api.getFoodById(id)

    val readAllData: Flow<List<Meal>> = foodDao.getAllMeals()
    suspend fun insert(meal: Meal) = foodDao.insert(meal)
    suspend fun delete(meal: Meal) = foodDao.delete(meal)
}