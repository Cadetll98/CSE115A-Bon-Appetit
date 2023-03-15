package com.cookingapp.data.remote.dto

import com.cookingapp.domain.model.Step

data class StepDto(
    val description: String
) {
    fun toStep(): Step {
        return Step(
            description
        )
    }
}