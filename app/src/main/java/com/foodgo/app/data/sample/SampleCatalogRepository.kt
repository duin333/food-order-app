package com.foodgo.app.data.sample

import com.foodgo.app.data.repository.CatalogRepository

/** Sample data only; no network or persistence. */
class SampleCatalogRepository : CatalogRepository {
    override fun getRestaurants() = SampleCatalog.restaurants.toList()
    override fun getRestaurant(id: String) = SampleCatalog.restaurants.find { it.id == id }
    override fun getFoods(restaurantId: String) = SampleCatalog.foods.filter { it.restaurantId == restaurantId }
    override fun getFood(id: String) = SampleCatalog.foods.find { it.id == id }
    override fun searchFoods(query: String) = SampleCatalog.foods.filter {
        it.name.contains(query.trim(), ignoreCase = true)
    }
}
