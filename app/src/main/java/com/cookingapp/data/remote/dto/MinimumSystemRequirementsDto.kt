package com.cookingapp.data.remote.dto

import com.cookingapp.domain.model.MinimumSystemRequirements


data class MinimumSystemRequirementsDto(
    val graphics: String,
    val memory: String,
    val os: String,
    val processor: String,
    val storage: String
){
    fun toMinimumSystemRequirements(): MinimumSystemRequirements {
        return MinimumSystemRequirements(
            graphics,
            memory,
            os,
            processor,
            storage
        )
    }
}