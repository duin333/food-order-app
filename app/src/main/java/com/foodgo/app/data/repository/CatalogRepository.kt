package com.foodgo.app.data.repository

import com.foodgo.app.data.model.*

/** BC3 synchronous contract. Invalid mutations throw IllegalArgumentException; no UI dependencies. */
interface CatalogRepository {
    fun getRestaurants(): List<Restaurant>
    fun getRestaurant(id: String): Restaurant?
    fun getFoods(restaurantId: String): List<FoodItem>
    fun getFood(id: String): FoodItem?
    fun searchFoods(query: String): List<FoodItem>
}
