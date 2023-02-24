// Filename: Shape.kt
//
// This file contains the shapes definitions for the UI.
// The shapes are created and used with library compose.material3.
//
// RoundedCornerShape(<offset desired.dp>)

package com.cookingapp.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes (
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(20.dp)
)
val AppShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(6.dp),
    large = RoundedCornerShape(8.dp)
)