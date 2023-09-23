package com.example.flavorfindercompose.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
data class Meal(
    @PrimaryKey
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)