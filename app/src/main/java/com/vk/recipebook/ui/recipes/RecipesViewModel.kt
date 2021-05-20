package com.vk.recipebook.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.recipebook.data.Recipe
import com.vk.recipebook.data.SearchParameters
import com.vk.recipebook.dataSources.RemoteDataSource
import com.vk.recipebook.utils.favoriteCheckedRecipes
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel() {

    private var _recipesLiveData = MutableLiveData<List<Recipe>>()
    var recipesLiveData: LiveData<List<Recipe>> = _recipesLiveData

/*    private val _searchInputLiveData = MutableLiveData<String>()
    var searchInputLiveData: LiveData<String> = _searchInputLiveData*/

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    fun searchRecipes(input: String) {
        val parameters = SearchParameters(input)
        viewModelScope.launch {
            _loadingLiveData.value = true
            val recipesList = RemoteDataSource.searchRecipes(parameters)
            val convertedRecipesList = favoriteCheckedRecipes(recipesList)
            _recipesLiveData.value = convertedRecipesList
            _loadingLiveData.value = false
        }
    }
}

/*
fun searchRecipes(input: String) {
    val parameters = SearchParameters(input)
    viewModelScope.launch {
        try {
            _loadingLiveData.value = true
            val recipesList = RemoteDataSource.searchRecipes(parameters)
*/
/*                lastSearchRecipes = recipesList
                searchInput = input*//*

            if (recipesList.isNotEmpty()) {
                val convertedRecipesList = favoriteCheckedRecipes(recipesList)
                displayRecipes(convertedRecipesList)

            } else
                showNoResult()
        } catch (e: Exception) {
            showError(e)
        }
        _loadingLiveData.value = false

    }
}*/
