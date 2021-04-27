package com.vk.recipebook.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.RecipeBookApp
import com.RecipeBookApp.Companion.db
import com.vk.recipebook.R
import com.vk.recipebook.data.Recipe
import com.vk.recipebook.databinding.FragmentSavedBinding
import com.vk.recipebook.ui.cart.RecipesAdapter
import kotlinx.coroutines.launch

private var searchInput: String? = null

class SavedFragment : Fragment() {

    private lateinit var savedViewModel: SavedViewModel
    private lateinit var binding: FragmentSavedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedViewModel =
            ViewModelProvider(this).get(SavedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_saved, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedBinding.bind(view)
        lifecycleScope.launch {
            getRecipiesFromDbAndDisplay()
            binding.savedSearchEditText.setText(searchInput)
        }
        binding.savedRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.savedSearchEditText.setText(searchInput)
    }

    fun onBookmarkButtonClick(recipe: Recipe) {
        lifecycleScope.launch {
            if (recipe.isInFavorite)
                db.recipesDAO().deleteRecipe(recipe)
            getRecipiesFromDbAndDisplay()
        }
    }

    private suspend fun getRecipiesFromDbAndDisplay() {
        val recipes = db.recipesDAO().getSavedRecipes()
        val filteredRecipes = recipes.filter { it.isInFavorite }
        val adapter = RecipesAdapter(filteredRecipes, object : RecipesAdapter.OnClickListener {
            override fun onRegisterItemClick(id: Int) {
                onItemClick(id)
            }

            override fun onRegisterFavoriteButtonClick(recipe: Recipe) {
                onBookmarkButtonClick(recipe)
            }
        })
        binding.savedRecyclerView.adapter = adapter
    }

    fun onItemClick(id: Int) {
        val action = SavedFragmentDirections.actionNavigationSavedToRecipeDetailsFragment(id)
        findNavController().navigate(action)
    }
}
