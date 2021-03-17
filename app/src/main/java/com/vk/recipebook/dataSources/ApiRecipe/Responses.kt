package com.vk.recipebook.dataSources.ApiRecipe

import com.google.gson.annotations.SerializedName

data class ResponseFromSearchRecipesResult(
        val id: Int,
        val title: String,
        val image: String
)

data class ResponseFromSearchRecipes (
    @SerializedName(value = "results")val recipes: List<ResponseFromSearchRecipesResult>

        )


data class ResponsesFromRecipeDetails(

    var title: String,
    var image: String,
    var vegetarian: Boolean,
    var vegan:Boolean,
    var glutenFree:Boolean,
    var dairyFree: Boolean,
    var servings: Int,
    @SerializedName(value = "aggregatedLikes") var likes:Int,
    @SerializedName(value = "extendedIngredients") var ingredients: List<Ingredients>
)

data class Ingredients(
    var name: String,
    var amount: Int,
    var unit: String
)
