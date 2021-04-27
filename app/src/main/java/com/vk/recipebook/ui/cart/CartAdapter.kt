package com.vk.recipebook.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vk.recipebook.R
import com.vk.recipebook.data.Ingredient
import com.vk.recipebook.data.Recipe
import com.vk.recipebook.databinding.CartItemBinding

class CartAdapter(val ingredients: List<Ingredient>):RecyclerView.Adapter<CartViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false))
    }

    override fun getItemCount(): Int = ingredients.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val ingredient = ingredients[position]

        holder.bind(ingredient)
    }

    interface onClickListener {
        fun onRegisterItemClick(id:Int)
    }
}

class CartViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(ingredient: Ingredient){
        val binding = CartItemBinding.bind(itemView)

        binding.amountTextView.text = ingredient.toString()
/*        binding.nameTextView.text = ingredient.name
        binding.unitTextView.text = ingredient.unit*/
    }
}

