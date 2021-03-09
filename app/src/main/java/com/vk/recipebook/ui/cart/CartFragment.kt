package com.vk.recipebook.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vk.recipebook.R
import com.vk.recipebook.data.SearchParameters
import com.vk.recipebook.dataSources.RemoteDataSource
import com.vk.recipebook.databinding.FragmentCartBinding
import kotlinx.coroutines.launch

class CartFragment : Fragment(R.layout.fragment_cart) {


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
        /*val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)
        val search = binding.testSearch
        val searchButton = binding.testSearchButton
        val searchResults = binding.testViewResults

        searchButton.setOnClickListener {
            lifecycleScope.launch{
                val parameters = SearchParameters(search.text.toString())
                try {
                    val response = RemoteDataSource.searchRecipes(parameters)
                    if (response.isEmpty())
                        searchResults.text = "Sorry, no results"
                    else
                        searchResults.text = response.toString()
                } catch (e: Exception){
                    searchResults.text = "Error"
                }




            }
        }
    }

}