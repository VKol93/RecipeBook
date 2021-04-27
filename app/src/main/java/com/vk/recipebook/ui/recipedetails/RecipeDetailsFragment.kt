package com.vk.recipebook.ui.recipedetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.RecipeBookApp.Companion.db
import com.squareup.picasso.Picasso
import com.vk.recipebook.R
import com.vk.recipebook.dataSources.RemoteDataSource
import com.vk.recipebook.databinding.FragmentRecipeDetailsBinding
import kotlinx.android.synthetic.main.fragment_recipe_details.*
import kotlinx.android.synthetic.main.fragment_recipe_details.view.*
import kotlinx.coroutines.launch

class RecipeDetailsFragment : Fragment() {
    private val args: RecipeDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentRecipeDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecipeDetailsBinding.bind(view)
        lifecycleScope.launch {
            try {
                val recipe = RemoteDataSource.getRecipeDetails(args.id)

                title_textView.text = recipe.title
                Picasso.with(view.context)
                    .load(recipe.image)
                    .into(view.recipeImage)
                ingredientsTextView.text = recipe.ingredients.joinToString("\n")

                preparationTextView.text = recipe.instructions

                if(recipe.isInFavorite)
                    binding.saveButton.visibility = View.VISIBLE
                else
                    binding.saveButton.visibility = View.INVISIBLE
                binding.addToCartButton.setOnClickListener {
                    lifecycleScope.launch {
                        val ingredients = recipe.ingredients
                        for(ingredient in ingredients)
                            db.cartDAO().addIngredient(ingredient)
                    }
                }

            }
            catch (exception: Exception){
                Log.d("_TAG", exception.message.toString())
                exception.printStackTrace()
            }
        }

    }
}
