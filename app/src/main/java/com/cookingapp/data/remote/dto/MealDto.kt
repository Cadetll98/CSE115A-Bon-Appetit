package com.cookingapp.data.remote.dto

// MealDto stands for Meal Data Transfer Object
// this file will read the information from the json file provided by the url. val's must
// match up to the files that are provided in the JSON file description
// if you do not like the name provided: use @SerializedName("<current name">)
// ex.
// @SerializedName("cooking_profile_url")
// val cookingProfileUrl: String,

import com.cookingapp.domain.model.Meal
import com.google.gson.annotations.SerializedName

data class MealDto(
    val developer: String,
    @SerializedName("freetogame_profile_url")
    val freeToGameProfileUrl: String,
    @SerializedName("game_url")
    val mealUrl: String,
    val genre: String,
    val id: Int,
    val platform: String,
    val publisher: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("short_description")
    val shortDescription: String,
    val thumbnail: String,
    val title: String
){
    fun toMeal(): Meal {
        return Meal(
            developer,
            freeToGameProfileUrl,
            mealUrl,
            genre,
            id,
            platform,
            publisher,
            releaseDate,
            shortDescription,
            thumbnail,
            title
        )
    }
}