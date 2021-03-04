package com.vk.recipebook.dataSources

import com.vk.recipebook.data.APIs
import com.vk.recipebook.data.Recipe
import com.vk.recipebook.data.SearchParameters
import com.vk.recipebook.dataSources.ApiRecipe.ResponseFromSearchRecipes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


const val defaultNumbersOfRecipies = 15

interface Api {
    @Headers("x-rapidapi-key: 5d094140cemsh5c766eb58516c73p1b5d0djsn9d0410a9138c", "x-rapidapi-host: webknox-recipes.p.rapidapi.com")
    @GET("recipe/search")
    suspend fun searchRecipes(
        @Query("query") query: String = "",
        @Query("type") type: String = "",
        @Query("cuisine") cuisine: String = "",
        @Query("number") number: Int = defaultNumbersOfRecipies,
        @Query("diet") diet: String = "",
        @Query("intolerance") intolerance: String = "",
        ): ResponseFromSearchRecipes

    @GET("Recipe Information")
    suspend fun getRecipeDetails(id: Int): Recipe
}

object RemoteDataSource {
    private val BASE_URL = "https://webknox-recipes.p.rapidapi.com/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    private val RETROFIT_SERVICE: Api = retrofit.create(Api::class.java)

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
                    source = APIs.Recipe,
                    id = recipe.id,
                    title = recipe.title,
                    image = recipe.image
                    )
            )
        return convertedResult
    }

}

/*    @GET("recipe/mealplans/generate")
    suspend fun computeDailyMealPlan(
            @Query("targetCalories")calories:Int,
            @Query("timeFrame")time:Int
    ):List<String>*/

/*    @GET("recipe/findByIngredients")
    suspend fun searchByIngredients(apisearchparameters: ApiSearchparameters): List<Recipe>

    @GET("food/ingredients")
    suspend fun autocompleteIngredientSearch(query:String): List<ApiSearchparameters>*/


/*data class ApiSearchParametersForSearchByIngredients(
    val ingredients: String = "",
    val number:Int = defaultNumbersOfRecipies
)*/
/*    val targetCalories: Int,
    val readyInMinutes: Int,
    val instructions: List<String>,
    val servings: Int,
    val measure: String, // измерения
    val featuredBy: String, // ?
    val query2: String, // partial complete ingredients name
    val url: String, // extract recipe list
    val author: Boolean = false // ?*/