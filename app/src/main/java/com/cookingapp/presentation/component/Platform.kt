package com.cookingapp.presentation.component

import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.cookingapp.R

@Composable
fun Platform(text: String) {

    val resource = if (text.contains("fiveStars", ignoreCase = true)) {
        R.drawable.ic_ratingstars
    } else {
        R.drawable.ic_ratingstars
    }

    Icon(
        painter = painterResource(id = resource),
        contentDescription = "",
        tint = MaterialTheme.colors.primaryVariant
    )
}