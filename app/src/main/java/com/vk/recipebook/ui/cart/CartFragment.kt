package com.vk.recipebook.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.RecipeBookApp.Companion.db
import com.vk.recipebook.R
import com.vk.recipebook.data.Recipe
import com.vk.recipebook.data.SearchParameters
import com.vk.recipebook.dataSources.RemoteDataSource
import com.vk.recipebook.databinding.FragmentCartBinding
import kotlinx.coroutines.launch

class CartFragment : Fragment(R.layout.fragment_cart) {


    val viewModel: CartViewModel by viewModels()

    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(
            R.layout.fragment_cart, container, false
        )
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)
        lifecycleScope.launch {
            val ingredients = db.cartDAO().getIngredients()
            val adapter = CartAdapter(ingredients)
            binding.cartParentRecyclerView.adapter = adapter
        }




        /* lifecycleScope.launch {
            val parameters = SearchParameters(binding.searchButton.text.toString())
            try {
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
    }*/
    }
}