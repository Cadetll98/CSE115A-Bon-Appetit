package com.cookingapp.domain.model

// The domain layer is independent of the rest of the other layers.
// It can contain use cases layer (single action that user can do usually
// overrides operator invoke so we can call the class of the use case as
// a function

// Model layer contains the mapped objects from the DTO objects

data class Meal(
//    val id: Int,
//    val title: String,
//    val thumbnail: String,
//    val shortDescription: String,
//    val mealUrl: String,
//    val rating: String,
//    val cuisine: String,


    val developer: String,
    val freeToGameProfileUrl: String,
    val mealUrl: String,
    val genre: String,
    val id: Int,
    val platform: String,
    val publisher: String,
    val releaseDate: String,
    val shortDescription: String,
    val thumbnail: String,
    val title: String
)
