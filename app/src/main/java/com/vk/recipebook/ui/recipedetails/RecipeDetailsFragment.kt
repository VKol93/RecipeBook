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
    private val args: RecipeDetailsFragmentArgs by navArgs()
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
                val response = RemoteDataSource.getRecipeDetails(args.id)

                title_textView.text = response.title
                Picasso.with(view.context)
                    .load(response.image)
                    .into(view.recipeImage)
                ingredientsTextView.text = response.ingredients.joinToString("\n")
                preparationTextView.text = response.instructions

            }
            catch (exception: Exception){
                Log.d("_TAG", exception.message.toString())
                exception.printStackTrace()
            }
        }
    }
}
