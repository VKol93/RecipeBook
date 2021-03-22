package com.vk.recipebook.dataSources.localdatasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vk.recipebook.data.Recipe

@Database(entities = [Recipe::class], version = 1)
abstract class RecipeDB : RoomDatabase(){
    abstract fun recipesDAO(): RecipeDAO
}