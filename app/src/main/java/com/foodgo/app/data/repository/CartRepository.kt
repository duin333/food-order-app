package com.foodgo.app.data.repository

import com.foodgo.app.data.model.*

/** BC3 synchronous contract. Invalid mutations throw IllegalArgumentException; no UI dependencies. */
interface CartRepository {
    fun getItems(): List<CartItem>
    /** Rejects food from another restaurant; clear explicitly after customer confirmation. */
    fun addItem(foodId: String, quantity: Int, note: String = ""): CartItem
    fun setQuantity(itemId: String, quantity: Int)
    fun removeItem(itemId: String)
    fun clear()
}
