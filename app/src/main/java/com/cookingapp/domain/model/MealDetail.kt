package com.cookingapp.domain.model

import com.cookingapp.data.remote.dto.*

data class MealDetail(
    val shortDescription: String,
    val mealUrl: String?,
    val id: String,
    val thumbnail: String,
    val ingredient: List<IngredientDto>,
    val ingredientGroup: List<IngredientGroupDto>?,
    val title: String,
    val notes: String?,
    val step: List<StepDto>,
    val tag: List<String>,
    val screenShots: List<ScreenshotDto>?
)