package com.cookingapp.data.remote.dto

import com.cookingapp.domain.model.Ingredient

data class IngredientDto(
    val amount: String,
    val name: String,
    val unit: String
) {
    fun toIngredient(): Ingredient {
        return Ingredient(
            amount,
            name,
            unit,
        )
    }
}