package com.vk.recipebook.data

data class SearchParameters (
    val naturalLanguageQuery: String = "",
    val categories: List<String> = emptyList(),
    val includedIngredients: List<String> = emptyList(),
    val excludedIngredients: List<String> = emptyList(),
    val diet: List<String> = emptyList(),
    val type: List<String> = emptyList(),  //meal
    val cuisine: List<String> = emptyList(),
    val readyInMinutes: Int = 0,
    val number: Int = 20
)