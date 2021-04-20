package com.vk.recipebook.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "RecipesList")
data class Recipe (
    @Ignore
    var source: APIs = APIs.RecipeByWebknox,

    @PrimaryKey
    var id: Int = 0,
    var title: String = "",
    var image: String? = "",
    var isInFavorite: Boolean = false,

    @Ignore
    var ingredients: List<Ingredient> = emptyList(),
    var instructions: String? = "",
    var calories: Int = 0,
    var servings: Int = 0,
    var url: String = "",

    @Ignore
    var diet:List<String> = emptyList(),
    @Ignore
    var occasion: List<String> = emptyList(),
    var cuisine: String = "",
    var time: Int = 0,
    var likes: Int = 0
){
    override fun equals(other: Any?): Boolean {
        if (other is Recipe)
            return id == other.id
        else
            return false
    }
}

enum class APIs{
    RecipeByWebknox, SomeOtherAPI,
}
