package com.example.flavorfindercompose.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flavorfindercompose.data.model.Meal
import com.example.flavorfindercompose.data.model.MealResponse
import com.example.flavorfindercompose.data.repository.MealRepository
import com.example.flavorfindercompose.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class FoodViewModel(application: Application) : ViewModel() {

    private val repository = MealRepository(application)

    private var _meals: MutableStateFlow<Resource<MealResponse>> = MutableStateFlow(Resource.Loading())
    val meals: StateFlow<Resource<MealResponse>> = _meals

    private var _meal: MutableStateFlow<Resource<MealResponse>> = MutableStateFlow(Resource.Loading())
    val meal: StateFlow<Resource<MealResponse>> = _meal

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
        val response = repository.getMeal()
        delay(2000L)
        _meals.value = handleMealResponse(response)
    }

    private fun handleMealResponse(response: Response<MealResponse>)
            : Resource<MealResponse> {
        if (response.isSuccessful)
            response.body()?.let { return Resource.Success(it) }
        return Resource.Error(response.message())
    }

    fun getMealById(id: String) = viewModelScope.launch {
        _meal.value = Resource.Loading()
        val response = repository.getMealById(id)
        delay(2000L)
        _meal.value = handleMealResponse(response)
    }

    fun insert(meal: Meal) = viewModelScope.launch {
        repository.insert(meal)
    }
//
//    fun delete(meal: Meal) = viewModelScope.launch {
//        repository.delete(meal)
//    }
}