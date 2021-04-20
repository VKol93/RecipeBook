package com

import android.app.Application
import androidx.room.Room
import com.vk.recipebook.dataSources.localdatasource.RecipeDB

class RecipeBookApp: Application() {
    companion object {
        lateinit var db: RecipeDB
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, RecipeDB::class.java, "db").build()
    }
}