package com.vk.recipebook.ui.recipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.RecipeBookApp.Companion.db
import com.vk.recipebook.R
import com.vk.recipebook.data.Recipe
import com.vk.recipebook.data.SearchParameters
import com.vk.recipebook.dataSources.RemoteDataSource
import com.vk.recipebook.databinding.FragmentRecipesBinding
import com.vk.recipebook.ui.cart.RecipesAdapter
import kotlinx.coroutines.launch

private var lastSearchRecipes: List<Recipe>? = null
private var searchInput: String? = null

class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_recipes, container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecipesBinding.bind(view)
        if (lastSearchRecipes != null) {
            val adapter = RecipesAdapter(
                lastSearchRecipes!!,
                object : RecipesAdapter.OnClickListener {
                    override fun onRegisterItemClick(id: Int) {
                        onItemClick(id)
                    }

                    override fun onRegisterFavoriteButtonClick(recipe: Recipe) {
                        onBookmarkButtonClick(recipe)
                    }
                })
            binding.recipesRecyclerView.adapter = adapter
            binding.searchButton.setText(searchInput)
        }

        binding.recipesRecyclerView.layoutManager = GridLayoutManager(context, 2)


        binding.searchButton.setOnClickListener {
            lifecycleScope.launch {
                val parameters = SearchParameters(binding.searchButton.text.toString())
                try {
                    binding.progressBar.visibility = View.VISIBLE

                    val recipesList = RemoteDataSource.searchRecipes(parameters)
                    lastSearchRecipes = recipesList
                    searchInput = binding.searchButton.text.toString()
                    if (recipesList.isNotEmpty()) {
                        val convertedRecipesList = convertedRecipes(recipesList)
                        val adapter = RecipesAdapter(
                            convertedRecipesList,
                            object : RecipesAdapter.OnClickListener {
                                override fun onRegisterItemClick(id: Int) {
                                    onItemClick(id)
                                }

                                override fun onRegisterFavoriteButtonClick(recipe: Recipe) {
                                    onBookmarkButtonClick(recipe)
                                }
                            })
                        binding.recipesRecyclerView.adapter = adapter
                        binding.errorTextView.text = recipesList.toString()
                        binding.errorTextView.visibility = View.INVISIBLE
                        binding.recipesRecyclerView.visibility = View.VISIBLE
                    } else {
                        binding.errorTextView.text = "Sorry, no results"
                        binding.recipesRecyclerView.visibility = View.INVISIBLE
                        binding.errorTextView.visibility = View.VISIBLE
                    }
                } catch (e: Exception) {
                    binding.errorTextView.text = "Error"
                    Log.d("recipeSearch", e.message.toString())
                    binding.recipesRecyclerView.visibility = View.INVISIBLE
                    binding.errorTextView.visibility = View.VISIBLE
                }
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }

    fun onBookmarkButtonClick(recipe: Recipe) {
        lifecycleScope.launch {
            if (recipe.isInFavorite) {
                recipe.isInFavorite = false
                db.recipesDAO().deleteRecipe(recipe)
            } else {
                recipe.isInFavorite = true
                db.recipesDAO().addRecipe(recipe)
            }
        }
    }

    suspend fun convertedRecipes(recipes: List<Recipe>): List<Recipe> {
        val recipesFromDB = db.recipesDAO().getSavedRecipes()
        for (recipe in recipes)
            if (recipesFromDB.contains(recipe))
                recipe.isInFavorite = true
        return recipes
    }

    fun onItemClick(id: Int) {
        val action = RecipesFragmentDirections.actionNavigationRecipesToRecipeDetailsFragment(id)
        findNavController().navigate(action)

    }
}