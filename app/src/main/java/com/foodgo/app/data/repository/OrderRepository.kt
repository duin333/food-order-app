package com.foodgo.app.data.repository

import com.foodgo.app.data.model.*

/** BC3 synchronous contract. Invalid mutations throw IllegalArgumentException; no UI dependencies. */
interface OrderRepository {
    /** Same ID returns the existing order, preventing duplicate submissions. */
    fun placeOrder(order: Order): Order
    fun getOrder(id: String): Order?
    fun getOrders(customerId: String): List<Order>
    /** Only pending orders can be cancelled in this demo. */
    fun cancelOrder(id: String): Order
    /** Explicit local simulation step; terminal states never advance. */
    fun advanceDemoStatus(id: String): Order
}
