package com.vk.recipebook.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.RecipeBookApp
import com.vk.recipebook.R
import com.vk.recipebook.dataSources.RemoteDataSource
import com.vk.recipebook.ui.cart.RecipesAdapter
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.recipe_item.*
import kotlinx.coroutines.launch

class SavedFragment : Fragment() {

    private lateinit var savedViewModel: SavedViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        savedViewModel =
                ViewModelProvider(this).get(SavedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_saved, container, false)
        /*val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            val recipes = RecipeBookApp.db.recipesDAO().getSavedRecipes()
            val filteredRecipes = recipes.filter { it.isInFavorite }
            val adapter = RecipesAdapter(filteredRecipes, null)
            recipesRecyclerView.adapter = adapter
            recipesRecyclerView.layoutManager = LinearLayoutManager(context)
        }

        /*lifecycleScope.launch {
            favoriteImageView.setOnClickListener {
                val recipes = RemoteDataSource.searchRecipes()
            }
        }*/
    }



    /*val response = RemoteDataSource.searchRecipes(parameters)
        val adapter = RecipesAdapter(object: RecipesAdapter.OnClickListener{
            override fun onRegisterItemClick(id: Int) {
                onItemClick(id)
            }
    }*/
}