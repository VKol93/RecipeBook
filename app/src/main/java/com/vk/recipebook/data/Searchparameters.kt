package com.vk.recipebook.data

data class SearchParameters (
    val naturalLanguageQuery: String,
    val categories: List<String> = emptyList(),
    val includedIngredients: List<String>,
    val excludedIngredients: List<String>,
    val diet: List<String>,
    val type: List<String>,  //meal
    val cuisine: List<String>,
    val readyInMinutes: Int,
    val number: Int = 20
)