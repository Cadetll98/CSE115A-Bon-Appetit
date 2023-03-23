package com.cookingapp.presentation.component

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.cookingapp.R

@Composable
fun Rating(text: String) {
    val resource = if(text.contains("fiveStars", ignoreCase = true)){
        R.drawable.ic_ratingstars
    } else if (text.contains("fourStars", ignoreCase = true)) {
        R.drawable.ic_ratingstars
    } else if (text.contains("threeStars", ignoreCase = true)) {
        R.drawable.ic_ratingstars
    } else if (text.contains("twoStars", ignoreCase = true)) {
        R.drawable.ic_ratingstars
    } else if (text.contains("oneStar", ignoreCase = true)){
        R.drawable.ic_ratingstars
    } else {
        R.drawable.ic_ratingstars
    }

    Icon(
        painter = painterResource(id = resource),
        contentDescription = "",
        tint = Color(0xFFFFD700)
    )
}