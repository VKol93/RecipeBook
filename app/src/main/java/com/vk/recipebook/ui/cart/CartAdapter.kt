package com.vk.recipebook.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vk.recipebook.R
import com.vk.recipebook.data.Recipe
import com.vk.recipebook.databinding.RecipeItemBinding
import kotlinx.android.synthetic.main.recipe_item.view.*


class RecipesAdapter(val recipes: List<Recipe>, val listener: OnClickListener): RecyclerView.Adapter<RecipeViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val viewHolder = RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false))
        return viewHolder
    }
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.itemView.setOnClickListener {
            listener.onRegisterItemClick(recipe.id)
        }
        holder.bind(recipe)

    }
    override fun getItemCount(): Int = recipes.size

    interface OnClickListener{
        fun onRegisterItemClick(id: Int)
    }
}
class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(recipe: Recipe){
        val binding = RecipeItemBinding.bind(itemView)
        binding.recipeTitleTextView.text = recipe.title
        Picasso.with(itemView.context)
            .load("https://spoonacular.com/recipeImages/"+recipe.image)
            .into(itemView.recipeImageView)
    }
}