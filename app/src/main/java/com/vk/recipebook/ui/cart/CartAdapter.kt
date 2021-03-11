package com.vk.recipebook.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vk.recipebook.R
import com.vk.recipebook.data.Recipe
import com.vk.recipebook.databinding.RecipeItemBinding


class RecipesAdapter(val recipes: List<Recipe>): RecyclerView.Adapter<RecipeViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val viewHolder = RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false))
        return viewHolder
    }
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        holder.bind(recipes[position])

    }
    override fun getItemCount(): Int = recipes.size
}
class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(recipe: Recipe){
        val binding = RecipeItemBinding.bind(itemView)
        binding.recipeTitleTextView.text = recipe.title





    //countryData: CountryData) {
        /*itemView.country_name_textView.text = countryData.name
        Picasso.with(itemView.context)
            .load(countryData.info.flag)
            .into(itemView.country_flag_imageView)
        itemView.setOnClickListener{
            listener.onItemClick(countryData, adapterPosition )*/

    }
}