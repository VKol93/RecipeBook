package com.vk.recipebook.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import kotlin.math.roundToInt

@Entity(tableName = "CartList")
data class Ingredient(
    var name: String = "",
    var amount: Float = 0f,
    var unit: String = "",
    @PrimaryKey
    var myId: String = UUID.randomUUID().toString()
) {
    override fun toString(): String {
        return if (amount % 1 == 0f)
            "${amount.roundToInt()} $unit $name"
        else
            "$amount $unit $name"
    }
}