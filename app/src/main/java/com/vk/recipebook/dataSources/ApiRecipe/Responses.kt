package com.vk.recipebook.dataSources.ApiRecipe

import com.google.gson.annotations.SerializedName
import com.vk.recipebook.data.Ingredient

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
    var sourceUrl:String,
    @SerializedName(value = "aggregatedLikes") var likes:Int,
    @SerializedName(value = "extendedIngredients") var ingredients: List<Ingredient>
)


data class ResponseForInstructions(
    @SerializedName(value = "text") var instructions:String
)
