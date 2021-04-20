package com.vk.recipebook.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.math.roundToInt

@Entity(tableName = "CartList")
data class Ingredient(
    @PrimaryKey
    var name: String = "",
    var amount: Float = 0f,
    var unit: String = ""
): Any() {
    override fun toString(): String {
        return if (amount % 1 == 0f)
            "${amount.roundToInt()} $unit $name"
        else
            "$amount $unit $name"
    }
}