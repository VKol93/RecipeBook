package com.vk.recipebook.dataSources

import com.vk.recipebook.data.APIs
import com.vk.recipebook.data.Recipe
import com.vk.recipebook.data.SearchParameters
import com.vk.recipebook.dataSources.ApiRecipe.ResponseFromSearchRecipes
import com.vk.recipebook.dataSources.ApiRecipe.ResponsesFromRecipeDetails
import okhttp3.Call
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


const val defaultNumbersOfRecipies = 15

interface Api {
    @Headers(
        "x-rapidapi-key: 5d094140cemsh5c766eb58516c73p1b5d0djsn9d0410a9138c",
        "x-rapidapi-host: webknox-recipes.p.rapidapi.com"
    )
    @GET("recipes/search")
    suspend fun searchRecipes(
        @Query("query") query: String = "",
        @Query("type") type: String = "",
        @Query("cuisine") cuisine: String = "",
        @Query("number") number: Int = defaultNumbersOfRecipies,
        @Query("diet") diet: String = "",
        @Query("intolerance") intolerance: String = "",
    ): ResponseFromSearchRecipes


    @Headers(
        "x-rapidapi-key: 5d094140cemsh5c766eb58516c73p1b5d0djsn9d0410a9138c",
        "x-rapidapi-host: webknox-recipes.p.rapidapi.com"
    )
    @GET("recipes/{id}/information")
    suspend fun getRecipeDetails(@Path("id") id: Int): ResponsesFromRecipeDetails
}

object RemoteDataSource {
    private val BASE_URL = "https://webknox-recipes.p.rapidapi.com/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val RETROFIT_SERVICE: Api = retrofit.create(Api::class.java)

    private fun booleansToDiets(response: ResponsesFromRecipeDetails): List<String>{
        val result = mutableListOf<String>()
        if(response.dairyFree)
            result.add("dairy-free")
        if(response.glutenFree)
            result.add("gluten-free")
        if(response.vegan)
            result.add("vegan")
        if(response.vegetarian)
            result.add("vegetarian")
        return result
    }

    suspend fun getRecipeDetails(id: Int): Recipe {
        val response = RETROFIT_SERVICE.getRecipeDetails(id)
        val convertedResult = Recipe(
            source = APIs.RecipeByWebknox,
            id = id,
            title = response.title,
            image = response.image,
            ingredients = response.ingredients,
            servings = response.servings,
            diet = booleansToDiets(response)
        )
        return convertedResult
    }

    suspend fun searchRecipes(searchParameters: SearchParameters): List<Recipe> {

        val response = RETROFIT_SERVICE.searchRecipes(
            searchParameters.naturalLanguageQuery,
            searchParameters.type.joinToString(","),
            searchParameters.cuisine.joinToString(","),
            searchParameters.number,
            searchParameters.diet.joinToString(","),
            searchParameters.excludedIngredients.joinToString(","),
        )

        val convertedResult = mutableListOf<Recipe>()
        for (recipe in response.recipes)
            convertedResult.add(
                Recipe(
                    source = APIs.RecipeByWebknox,
                    id = recipe.id,
                    title = recipe.title,
                    image = recipe.image
                )
            )
        return convertedResult
    }

}