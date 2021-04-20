package com.vk.recipebook.dataSources.localdatasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vk.recipebook.data.Ingredient

@Dao
interface CartDAO {
    @Query ("SELECT*FROM CartList")
    suspend fun getIngredients(): List<Ingredient>
    @Delete
    suspend fun deleteIngredient(ingredient: Ingredient)
    @Insert
    suspend fun addIngredient(ingredient: Ingredient)
}