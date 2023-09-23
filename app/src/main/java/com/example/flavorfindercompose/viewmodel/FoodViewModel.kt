package com.example.flavorfindercompose.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flavorfindercompose.data.model.MealResponse
import com.example.flavorfindercompose.data.repository.FoodRepository
import com.example.flavorfindercompose.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class FoodViewModel(application: Application) : ViewModel() {

    private val repository = FoodRepository(application)
    private var _meals: MutableStateFlow<Resource<MealResponse>> = MutableStateFlow(Resource.Loading())
    val meals: StateFlow<Resource<MealResponse>> = _meals

//    val mealList: LiveData<Resource<MealResponse>>
//        get() = _mealList
//
//    val meal: LiveData<Resource<MealResponse>>
//        get() = _meal
//
//    val readAllData: LiveData<List<Meal>>

    init {
        getMealList()
        //val foodDao = MealDatabase.getDatabase(application).foodDao()
        //repository = FoodRepository(foodDao)
        //readAllData = repository.readAllData
    }

    private fun getMealList() = viewModelScope.launch {
        _meals.value = Resource.Loading()
        val response = repository.getFood()
        _meals.value = handleMealResponse(response)
    }

    private fun handleMealResponse(response: Response<MealResponse>)
            : Resource<MealResponse> {
        if (response.isSuccessful)
            response.body()?.let { return Resource.Success(it) }
        return Resource.Error(response.message())
    }

//    fun getMealById(id: String) = viewModelScope.launch {
//        _meal.postValue(Resource.Loading())
//        val response = repository.getFoodById(id)
//        _meal.postValue(handleMealResponse(response))
//    }
//
//    fun insert(meal: Meal) = viewModelScope.launch {
//        repository.insert(meal)
//    }
//
//    fun delete(meal: Meal) = viewModelScope.launch {
//        repository.delete(meal)
//    }
}