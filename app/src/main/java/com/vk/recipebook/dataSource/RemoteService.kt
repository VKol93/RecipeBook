package com.vk.recipebook.dataSource

import com.vk.recipebook.data.Recipe
import com.vk.recipebook.data.Searchparameters


interface ApiRecipe {
    @GET("search")
    suspend fun searchRecipe(apisearchparameters: ApiSearchparameters): List<Recipe>

    @GET("findByIngredients")
    suspend fun searchByIngredients(ingredients:String): List<Recipe>


    @GET("search")
    suspend fun searchNaturalLanguageRecipe(query: String): List<Recipe>
}

data class ApiSearchparameters (
    val query: String,
    val type: String,
    val cuisine: String,

    )


object RemoteDataSource {
    private val BASE_URL = "https://hacker-news.firebaseio.com/v0/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    private val retrofitService: ApiHackerNews = retrofit.create(ApiHackerNews::class.java)

    suspend fun searchRecipe(searchparameters: Searchparameters):List<Story>{
        val storyList:MutableList<Story> = mutableListOf()
        val topStoriesIDs: List<Int> = retrofitService.getTopStoriesID()
        val topTenStories = topStoriesIDs.take(20)
        for (id in topTenStories){
            val story = retrofitService.getStory(id)
            storyList.add(story)
        }
        Log.d("_TAG", storyList.toString())
        return storyList

    }
}