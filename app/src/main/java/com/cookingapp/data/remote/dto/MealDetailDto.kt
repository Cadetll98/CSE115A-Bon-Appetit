package com.cookingapp.data.remote.dto

import com.cookingapp.domain.model.MealDetail
import com.google.gson.annotations.SerializedName

data class MealDetailDto(
    val description: String,
    val developer: String,
    @SerializedName("freetogame_profile_url")
    val freeToGameProfileUrl: String,
    @SerializedName("game_url")
    val mealUrl: String,
    val genre: String,
    val id: Int,
    @SerializedName("minimum_system_requirements")
    val minimumSystemRequirements: MinimumSystemRequirementsDto?,
    val platform: String,
    val publisher: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("screenshots")
    val screenShots: List<ScreenshotDto>,
    @SerializedName("short_description")
    val shortDescription: String,
    val status: String,
    val thumbnail: String,
    val title: String
){
    fun toMealDetail(): MealDetail {
        return MealDetail(
            description,
            developer,
            freeToGameProfileUrl,
            mealUrl,
            genre,
            id,
            minimumSystemRequirements,
            platform,
            publisher,
            releaseDate,
            screenShots,
            shortDescription,
            status,
            thumbnail,
            title
        )
    }
}