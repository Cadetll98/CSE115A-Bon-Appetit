package com.cookingapp.domain.repository
// This repository package is going to define an abstraction of the actions in the Api interface
//
import com.cookingapp.domain.model.Meal
import com.cookingapp.domain.model.MealDetail
import com.cookingapp.util.Resource

// Here are the functions that we are going to implement in the repository inside the data layer
interface MealRepository {
    suspend fun getAllMeals(): Resource<List<Meal>>

    suspend fun getMeal(id: Int): Resource<MealDetail?>

    suspend fun getMealsByPlatform(platform :String): Resource<List<Meal>>

    suspend fun sortMeals(criteria: String): Resource<List<Meal>>
}