package com.cookingapp.data.remote.dto

import com.cookingapp.domain.model.Screenshot

data class ScreenshotDto(
    val id: Int,
    val image: String
) {
    fun toScreenshot(): Screenshot {
        return Screenshot(
            id,
            image
        )
    }
}