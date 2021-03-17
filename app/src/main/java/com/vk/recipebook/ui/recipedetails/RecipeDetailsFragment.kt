package com.vk.recipebook.ui.recipedetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.vk.recipebook.R
import com.vk.recipebook.data.APIs
import com.vk.recipebook.data.Recipe
import com.vk.recipebook.dataSources.RemoteDataSource
import kotlinx.android.synthetic.main.fragment_recipe_details.*
import kotlinx.android.synthetic.main.fragment_recipe_details.view.*
import kotlinx.coroutines.launch


class RecipeDetailsFragment : Fragment() {
    val args: RecipeDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            try {
                val response = RemoteDataSource.getRecipeDetails(id)
                title_textView.text = response.title

            }
            catch (exception: Exception){
                Log.d("_TAG", exception.message.toString())
                exception.printStackTrace()

            }
           /* val fakeResponse = Recipe(
                source = APIs.SomeOtherAPI,
                id = 1,
                title = "sdfsdf")
            Picasso.with(view.context)
                .load(response.image)
                .into(view.recipeImage)*/

        }
    }

}
/*//вариант с неработающий апи
val response = Recipe(
    source = APIs.SomeOtherAPI,
    id = 1,
    title = "sdfsdf"*/

/*lifecycleScope.launch{
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
}*/