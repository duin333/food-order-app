package com.foodgo.app.data.model

data class FoodItem(
    val id: String,
    val restaurantId: String,
    val name: String,
    val description: String,
    val priceVnd: Long,
    val imageKey: String,
    val categoryId: String,
    val ingredients: List<String>,
    val allergenCodes: List<String>,
    val isAvailable: Boolean = true,
)
