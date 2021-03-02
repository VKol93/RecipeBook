package com.vk.recipebook.data

data class Recipe (
    val source: APIs = APIs.Recipe,
    val id: Int,
    val title: String,
    val ingredients: List<String>,
    val instructions: String,

    val diet:List<String>,
    val occasion: List<String>,
    val cuisine: String,
    val time: Int,


    val likes: Int,
    val comments: List<String>

)


enum class APIs{
    Recipe
}