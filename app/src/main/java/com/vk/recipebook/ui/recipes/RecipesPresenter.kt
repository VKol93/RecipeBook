package com.vk.recipebook.ui.recipes

import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.vk.recipebook.data.Recipe
import com.vk.recipebook.data.SearchParameters
import com.vk.recipebook.dataSources.RemoteDataSource
import com.vk.recipebook.ui.cart.RecipesAdapter
import com.vk.recipebook.utils.favoriteCheckedRecipes
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RecipePresenter(val view: RecipesFragment) {

    var lastSearchRecipes: List<Recipe>? = null
    var searchInput: String? = null

    fun searchRecipes(input: String) {
        val parameters = SearchParameters(input)
        GlobalScope.launch {

            try {
                view.showProgressBar()
                val recipesList = RemoteDataSource.searchRecipes(parameters)
                lastSearchRecipes = recipesList
                searchInput = input
                if (recipesList.isNotEmpty()) {
                    val convertedRecipesList = favoriteCheckedRecipes(recipesList)
                    view.displayRecipes(convertedRecipesList)

                } else
                    view.showNoResult()
            } catch (e: Exception) {
                view.showError(e)
            }
            view.hideProgressBar()
        }
    }
}