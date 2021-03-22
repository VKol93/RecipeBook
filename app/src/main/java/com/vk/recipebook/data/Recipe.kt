package com.vk.recipebook.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "RecipesList")
data class Recipe (
    @Ignore
    val source: APIs = APIs.RecipeByWebknox,

    @PrimaryKey
    val id: Int,
    val title: String,
    val image: String = "",
    val isInFavorite: Boolean = false,

    @Ignore
    val ingredients: List<Ingredient> = emptyList(),
    val instructions: String = "",
    val calories: Int = 0,
    val servings: Int = 0,
    var url: String = "",


    @Ignore
    val diet:List<String> = emptyList(),
    @Ignore
    val occasion: List<String> = emptyList(),
    val cuisine: String = "",
    val time: Int = 0,


    val likes: Int = 0

)


enum class APIs{
    RecipeByWebknox, SomeOtherAPI,
}
