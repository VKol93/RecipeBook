package com.vk.recipebook.ui.recipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.RecipeBookApp.Companion.db
import com.vk.recipebook.R
import com.vk.recipebook.data.Recipe
import com.vk.recipebook.databinding.FragmentRecipesBinding
import com.vk.recipebook.ui.cart.RecipesAdapter
import kotlinx.android.synthetic.main.fragment_recipes.*
import kotlinx.coroutines.launch

class RecipesFragment : Fragment() {

    private val viewModel: RecipesViewModel by viewModels()

    private val presenter = RecipePresenter(this)
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
        binding.recipesRecyclerView.layoutManager = GridLayoutManager(context, 2)
        //displayLastSearchResults()

        binding.searchButton.setOnClickListener {
            viewModel.searchRecipes(binding.searchEditText.text.toString())
        }

        viewModel.recipesLiveData.observe(viewLifecycleOwner) { recipes -> // null
            if (recipes != null) {
                if (recipes.isNotEmpty())
                    displayRecipes(recipes)
                else
                    showNoResult()
            } else
                showError()
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading)
                View.VISIBLE
            else
                View.INVISIBLE
        }
    }

/*    private fun displayLastSearchResults() {
        if (presenter.lastSearchRecipes != null) {
            displayRecipes(presenter.lastSearchRecipes!!)
            binding.searchEditText.setText(presenter.searchInput)
        }
    }*/

    fun displayRecipes(recipesList: List<Recipe>) {
        val adapter = RecipesAdapter(
            recipesList,
            object : RecipesAdapter.OnClickListener {
                override fun onRegisterItemClick(id: Int) {
                    onItemClick(id)
                }

                override fun onRegisterFavoriteButtonClick(recipe: Recipe) {
                    onBookmarkButtonClick(recipe)
                }
            })
        binding.recipesRecyclerView.adapter = adapter
        binding.errorTextView.visibility = View.INVISIBLE
        binding.recipesRecyclerView.visibility = View.VISIBLE
    }

    fun showNoResult() {
        binding.errorTextView.text = "Sorry, no results"
        binding.recipesRecyclerView.visibility = View.INVISIBLE
        binding.errorTextView.visibility = View.VISIBLE
    }

    fun showError() {
        binding.errorTextView.text = "Error"
        //Log.d("recipeSearch", error.message.toString())
        binding.recipesRecyclerView.visibility = View.INVISIBLE
        binding.errorTextView.visibility = View.VISIBLE
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


    fun onItemClick(id: Int) {
        val action = RecipesFragmentDirections.actionNavigationRecipesToRecipeDetailsFragment(id)
        findNavController().navigate(action)

    }
}