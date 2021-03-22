package com.vk.recipebook.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ingredient(
    @PrimaryKey
    var name: String,
    var amount: Float,
    var unit: String
)