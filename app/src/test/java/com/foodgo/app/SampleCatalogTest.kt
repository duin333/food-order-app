package com.foodgo.app

import com.foodgo.app.data.sample.SampleCatalog
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SampleCatalogTest {
    @Test
    fun sampleCatalogHasConsistentIdsPricesAndRestaurants() {
        val restaurants = SampleCatalog.restaurants
        val foods = SampleCatalog.foods
        val restaurantIds = restaurants.map { it.id }.toSet()
        val allIds = restaurants.map { it.id } + foods.map { it.id }

        assertEquals(5, restaurants.size)
        assertEquals(17, foods.size)
        assertEquals("IDs must be unique across the catalog", allIds.size, allIds.toSet().size)
        foods.forEach { food ->
            assertTrue("Unknown restaurant for ${food.id}", food.restaurantId in restaurantIds)
            assertTrue("Invalid price for ${food.id}", food.priceVnd > 0L)
        }
        restaurants.forEach { restaurant ->
            assertTrue("At least 3 foods required for ${restaurant.id}",
                foods.count { it.restaurantId == restaurant.id } >= 3)
        }
    }
}
