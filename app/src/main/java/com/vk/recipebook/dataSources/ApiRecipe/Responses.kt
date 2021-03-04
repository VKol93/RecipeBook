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