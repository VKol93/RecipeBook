package com.vk.recipebook.data

import com.vk.recipebook.dataSources.ApiRecipe.Ingredients

data class Recipe (
    val source: APIs = APIs.RecipeByWebknox,

    val id: Int,
    val title: String,
    val image: String = "",


    val ingredients: List<Ingredients> = emptyList(),
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
    RecipeByWebknox, SomeOtherAPI,
}
