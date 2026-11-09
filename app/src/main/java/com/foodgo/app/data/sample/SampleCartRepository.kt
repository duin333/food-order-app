package com.foodgo.app.data.sample

import com.foodgo.app.data.model.CartItem
import com.foodgo.app.data.repository.CartRepository
import com.foodgo.app.data.repository.CatalogRepository
import java.util.UUID

/** Single-process sample cart; resets when the process exits. */
class SampleCartRepository(private val catalog: CatalogRepository) : CartRepository {
    private val items = linkedMapOf<String, CartItem>()

    override fun getItems() = items.values.toList()

    override fun addItem(foodId: String, quantity: Int, note: String): CartItem {
        require(quantity > 0) { "Quantity must be positive" }
        val food = requireNotNull(catalog.getFood(foodId)) { "Food not found" }
        require(food.isAvailable) { "Food unavailable" }
        require(items.values.all { it.restaurantId == food.restaurantId }) { "Cart supports one restaurant only" }
        val existing = items.values.find { it.foodId == foodId && it.note == note }
        val item = existing?.copy(quantity = Math.addExact(existing.quantity, quantity))
            ?: CartItem(UUID.randomUUID().toString(), food.id, food.restaurantId,
                food.name, food.priceVnd, quantity, note, food.imageKey)
        items[item.id] = item
        return item
    }

    override fun setQuantity(itemId: String, quantity: Int) {
        require(quantity > 0) { "Quantity must be positive; use removeItem to delete" }
        val item = requireNotNull(items[itemId]) { "Cart item not found" }
        items[itemId] = item.copy(quantity = quantity)
    }

    override fun removeItem(itemId: String) { items.remove(itemId) }
    override fun clear() { items.clear() }
}
