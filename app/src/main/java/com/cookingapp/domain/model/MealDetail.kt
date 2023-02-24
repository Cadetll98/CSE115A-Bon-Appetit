package com.cookingapp.domain.model

import com.cookingapp.data.remote.dto.MinimumSystemRequirementsDto
import com.cookingapp.data.remote.dto.ScreenshotDto

data class MealDetail(
    val description: String,
    val developer: String,
    val freeToGameProfileUrl: String,
    val mealUrl: String,
    val genre: String,
    val id: Int,
    val minimumSystemRequirements: MinimumSystemRequirementsDto?,
    val platform: String,
    val publisher: String,
    val releaseDate: String,
    val screenShots: List<ScreenshotDto>,
    val shortDescription: String,
    val status: String,
    val thumbnail: String,
    val title: String

//    val id: Int,
//    val title: String,
//    val thumbnail: String,
//    val shortDescription: String,
//    val mealUrl: String,
//    val rating: String,
//    val cuisine: String,
)