package com.vk.recipebook.utils

import com.vk.recipebook.data.Ingredient
import org.junit.Test

import org.junit.Assert.*

class FilterIngridientsKtTest {

    @Test
    fun filterIngredients() {
        val actualResult = filterIngredients(testIngredients1)
        assertEquals(result1, actualResult)
    }

    val testIngredients1 = listOf<Ingredient>(
        Ingredient("flour", 2f, "cups"),
        Ingredient("flour", 5f, "cups"),
        Ingredient("pecan", 2f, "oz")
    )
    val result1 = listOf<Ingredient>(
        Ingredient("flour", 7f, "cups"),
        Ingredient("pecan", 2f, "oz")
    )
    val testIngredients2 = emptyList<Ingredient>()

    val testIngredients3 = listOf<Ingredient>(
        Ingredient("flour", 2f, "cups"),
        Ingredient("flour", 5f, "cups"),
        Ingredient("water", 2f, "oz")
    )
}