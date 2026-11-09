package com.foodgo.app.data.model

/** Snapshot of the food name and price when added; independent of catalog changes. */
data class CartItem(
    val id: String,
    val foodId: String,
    val restaurantId: String,
    val foodName: String,
    val unitPriceVnd: Long,
    val quantity: Int,
    val note: String = "",
    val imageKey: String = "",
) {
    init {
        require(unitPriceVnd > 0)
        require(quantity > 0)
    }

    val subtotalVnd: Long get() = Math.multiplyExact(unitPriceVnd, quantity.toLong())
}
