package com.vk.recipebook.dataSources.localdatasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vk.recipebook.data.Ingredient
import com.vk.recipebook.data.Recipe

@Database(entities = [Recipe::class, Ingredient::class], version = 1)
abstract class RecipeDB : RoomDatabase(){
    abstract fun recipesDAO(): RecipeDAO
    abstract fun cartDAO(): CartDAO
}