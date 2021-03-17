package com.vk.recipebook.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vk.recipebook.R
import com.vk.recipebook.data.SearchParameters
import com.vk.recipebook.dataSources.RemoteDataSource
import com.vk.recipebook.databinding.FragmentRecipesBinding
import com.vk.recipebook.ui.cart.RecipesAdapter
import kotlinx.coroutines.launch

class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(
            R.layout.fragment_recipes, container, false
        )
        //val manager = GridLayoutManager(activity, 5, GridLayoutManager.HORIZONTAL, false)
        //binding.recipesRecyclerView.layoutManager = manager
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
              binding = FragmentRecipesBinding.bind(view)
              val search = binding.testSearch
              val searchButton = binding.testSearchButton
              val errorTextView = binding.errorTextView

              searchButton.setOnClickListener {
                  lifecycleScope.launch{
                      val parameters = SearchParameters(search.text.toString())
                      try {
                          binding.progressBar.visibility = View.VISIBLE
                          val response = RemoteDataSource.searchRecipes(parameters)
                          if (response.isNotEmpty()){//
                              val adapter = RecipesAdapter(response, object: RecipesAdapter.OnClickListener{
                                  override fun onRegisterItemClick(id: Int) {
                                      onItemClick(id)
                                  }

                              })
                              binding.recipesRecyclerView.adapter = adapter
                              errorTextView.text = response.toString()
                              errorTextView.visibility = View.INVISIBLE
                              binding.recipesRecyclerView.visibility = View.VISIBLE
                          }else {//
                              errorTextView.text = "Sorry, no results"
                              binding.recipesRecyclerView.visibility = View.INVISIBLE
                              errorTextView.visibility = View.VISIBLE
                          }
                      } catch (e: Exception){//
                          errorTextView.text = "Error"
                          binding.recipesRecyclerView.visibility = View.INVISIBLE
                          errorTextView.visibility = View.VISIBLE
                      }
                      binding.progressBar.visibility = View.INVISIBLE
                  }
              }


    }

    private fun onItemClick(id: Int) {
         val action = RecipesFragmentDirections.actionNavigationRecipesToRecipeDetailsFragment(id)
         findNavController().navigate(action)
    }
}