package com.vk.recipebook.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.RecipeBookApp.Companion.db
import com.vk.recipebook.R
import com.vk.recipebook.data.Ingredient
import com.vk.recipebook.data.Recipe
import com.vk.recipebook.data.SearchParameters
import com.vk.recipebook.dataSources.RemoteDataSource
import com.vk.recipebook.databinding.FragmentCartBinding
import com.vk.recipebook.utils.filterIngredients
import kotlinx.coroutines.launch

class CartFragment : Fragment() {

    val viewModel: CartViewModel by viewModels()

    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(
            R.layout.fragment_cart, container, false
        )
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)
        lifecycleScope.launch {
            val ingredients = db.cartDAO().getIngredients()
            val adapter = CartAdapter(ingredients)
            binding.cartParentRecyclerView.adapter = adapter

            binding.switcher.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    val filteredIngredients = filterIngredients(ingredients)
                    val adapter = CartAdapter(filteredIngredients)
                    binding.cartParentRecyclerView.adapter = adapter
                } else {
                    lifecycleScope.launch {
                        val ingredients = db.cartDAO().getIngredients()
                        val adapter = CartAdapter(ingredients)
                        binding.cartParentRecyclerView.adapter = adapter
                    }
                }
            }

        }

    }
}