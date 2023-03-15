package com.cookingapp.domain.model

import com.cookingapp.data.remote.dto.IngredientDto
import com.cookingapp.data.remote.dto.IngredientGroupDto
import com.cookingapp.data.remote.dto.ScreenshotDto
import com.cookingapp.data.remote.dto.StepDto
import com.google.gson.annotations.SerializedName

// The domain layer is independent of the rest of the other layers.
// It can contain use cases layer (single action that user can do usually
// overrides operator invoke so we can call the class of the use case as
// a function

// Model layer contains the mapped objects from the DTO objects

data class Meal(
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
