package com.vk.recipebook.utils

import com.RecipeBookApp
import com.vk.recipebook.data.Recipe

suspend fun favoriteCheckedRecipes(recipes: List<Recipe>): List<Recipe> {
    val recipesFromDB = RecipeBookApp.db.recipesDAO().getSavedRecipes()
    for (recipe in recipes)
        if (recipesFromDB.contains(recipe))
            recipe.isInFavorite = true
    return recipes
}

suspend fun favoriteCheckRecipe(recipe: Recipe): Recipe {
    val recipesFromDB = RecipeBookApp.db.recipesDAO().getSavedRecipes()
    if(recipesFromDB.contains(recipe))
        recipe.isInFavorite = true
    return recipe
}