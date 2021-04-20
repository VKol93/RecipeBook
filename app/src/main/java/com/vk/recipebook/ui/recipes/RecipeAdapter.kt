package com.vk.recipebook.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vk.recipebook.R
import com.vk.recipebook.data.Recipe

import com.vk.recipebook.databinding.RecipeItemBinding
import kotlinx.android.synthetic.main.recipe_item.view.*


class RecipesAdapter(val recipes: List<Recipe>, val listener: OnClickListener?): RecyclerView.Adapter<RecipeViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false))
    }
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.itemView.setOnClickListener {
            listener?.onRegisterItemClick(recipe.id)
        }
        holder.itemView.favoriteImageView.setOnClickListener {
            listener?.onRegisterFavoriteButtonClick(recipe)
            holder.bind(recipe)
        }
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipes.size

    interface OnClickListener{
        fun onRegisterItemClick(id: Int)
        fun onRegisterFavoriteButtonClick(recipe: Recipe)
    }
}
class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(recipe: Recipe){
        val binding = RecipeItemBinding.bind(itemView)
        binding.recipeTitleTextView.text = recipe.title
        if(recipe.image != null)
            Picasso.with(itemView.context)
            .load("https://spoonacular.com/recipeImages/"+recipe.image)
            .into(itemView.recipeImageView)
        else
            binding.recipeImageView.setImageResource(R.drawable.ic_baseline_cancel_24)

        if(recipe.isInFavorite)
            binding.favoriteImageView.setImageResource(R.drawable.ic_baseline_favorite_24)
    }
}


