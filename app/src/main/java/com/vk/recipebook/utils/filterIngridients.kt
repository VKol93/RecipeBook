package com.vk.recipebook.utils

import com.vk.recipebook.data.Ingredient

fun filterIngredients(ingredients: List<Ingredient>): List<Ingredient>{
    val filteredIngredients = mutableListOf<Ingredient>()
    for (ingredient in ingredients) { //ingredient = 2 apples
        var find = false
        for (filteredIngredient in filteredIngredients) // 2oz flour
            if (ingredient.name == filteredIngredient.name && ingredient.unit == filteredIngredient.unit) {
                find = true
                filteredIngredient.amount += ingredient.amount
            }
        if (!find)
            filteredIngredients.add(ingredient)
    }
    return filteredIngredients
}
/*

fun filterIngredients2(ingredients: List<Ingredient>): List<Ingredient>{
    val filteredIngredients = mutableListOf<Ingredient>()
    for (ingredient in ingredients) { //ingredient = 2 apples
        val matchedIngredient = filteredIngredients.find { it.name==ingredient.name&&it.unit==ingredient.unit }
        if (matchedIngredient != null)
            matchedIngredient.amount += ingredient.amount
        else
            filteredIngredients.add(ingredient)
    }
    return filteredIngredients
}*/
