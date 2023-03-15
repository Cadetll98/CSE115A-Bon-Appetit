package com.cookingapp.data.repository

import com.cookingapp.data.remote.api.Api
import com.cookingapp.domain.model.Meal
import com.cookingapp.domain.model.MealDetail
import com.cookingapp.domain.repository.MealRepository
import com.cookingapp.util.Resource
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val api: Api
): BaseRepository(), MealRepository {


    override suspend fun getAllMeals(): Resource<List<Meal>> {
        val response = invokeApi {
            api.getAllMeals()
        }
        return when(response){
            is Resource.Error -> Resource.Error(error = response.error)
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> Resource.Success(
                data = response.data?.map { it.toMeal() }?: emptyList()
            )
        }
    }

    override suspend fun getMeal(id: String): Resource<MealDetail?> {
        val response = invokeApi {
            api.getMeal(id = id)
        }
        return when(response){
            is Resource.Error -> Resource.Error(error = response.error)
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> Resource.Success(
                data = response.data?.toMealDetail()
            )
        }
    }

    override suspend fun getMealsByPlatform(platform: String): Resource<List<Meal>> {
        val response = invokeApi {
            api.getMealsByPlatform(platform = platform)
        }
        return when(response){
            is Resource.Error -> Resource.Error(error  = response.error)
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> Resource.Success(
                data = response.data?.map { it.toMeal() }?: emptyList()
            )
        }
    }

    override suspend fun sortMeals(criteria: String): Resource<List<Meal>> {
        val response = invokeApi {
            api.sortMeals(criteria = criteria)
        }
        return when(response){
            is Resource.Error -> Resource.Error(error  = response.error)
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> Resource.Success(
                data = response.data?.map { it.toMeal() }?: emptyList()
            )
        }
    }

}