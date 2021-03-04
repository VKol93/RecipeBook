package com.vk.recipebook.data

data class Recipe ( //предоставляет АПИ
    val source: APIs = APIs.Recipe,

    val id: Int,
    val title: String,
    val image: String = "",


    val ingredients: List<String> = emptyList(),
    val instructions: String = "",
    val calories: Int = 0,
    val servings: Int = 0,



    val diet:List<String> = emptyList(),
    val occasion: List<String> = emptyList(),
    val cuisine: String = "",
    val time: Int = 0,


    val likes: Int = 0

)


enum class APIs{
    Recipe
}