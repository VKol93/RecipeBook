package com.vk.recipebook.data

data class Searchparameters (

    val categories: List<String>,
    val includedIngredients: List<String>,
    val excludedIngredients: List<String>,
    val diet: List<String>,
    val occasion: List<String>,

    val cuisine: List<String>,
    val maxTime: Int,

    val byFollowed: Boolean = false

)