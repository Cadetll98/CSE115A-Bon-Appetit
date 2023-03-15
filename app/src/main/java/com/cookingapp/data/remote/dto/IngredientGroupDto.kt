package com.cookingapp.data.remote.dto

import com.cookingapp.domain.model.IngredientGroup

data class IngredientGroupDto(
    val ingredient: List<Any>
){
    fun toIngredientGroup(): IngredientGroup {
        return IngredientGroup(
            ingredient
        )
    }
}