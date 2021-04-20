package com.vk.recipebook.dataSources.localdatasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vk.recipebook.data.Recipe

@Dao
interface RecipeDAO {
    @Query("SELECT*FROM RecipesList")
    suspend fun getSavedRecipes(): List<Recipe>
    @Delete
    suspend fun deleteRecipe(recipe: Recipe)
    @Insert
    suspend fun addRecipe(recipe:Recipe)
}