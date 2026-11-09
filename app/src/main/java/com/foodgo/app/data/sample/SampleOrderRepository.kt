package com.foodgo.app.data.sample

import com.foodgo.app.data.model.Order
import com.foodgo.app.data.model.OrderStatus
import com.foodgo.app.data.repository.OrderRepository

/** Sample orders; status changes occur only via explicit demo steps, without a timer. */
class SampleOrderRepository : OrderRepository {
    private val orders = linkedMapOf<String, Order>()

    private fun snapshot(order: Order) = order.copy(items = order.items.map { it.copy() })

    override fun placeOrder(order: Order): Order {
        require(order.id.isNotBlank() && order.customerId.isNotBlank())
        orders[order.id]?.let {
            require(it.customerId == order.customerId) { "Order ID belongs to another customer" }
            return snapshot(it)
        }
        require(order.status == OrderStatus.PENDING_CONFIRMATION && !order.ecoPointsAwarded)
        val saved = snapshot(order)
        orders[saved.id] = saved
        return snapshot(saved)
    }

    override fun getOrder(id: String) = orders[id]?.let(::snapshot)
    override fun getOrders(customerId: String) = orders.values
        .filter { it.customerId == customerId }.sortedByDescending { it.createdAtMillis }.map(::snapshot)

    override fun cancelOrder(id: String): Order {
        val order = requireNotNull(orders[id]) { "Order not found" }
        if (order.status == OrderStatus.CANCELLED) return snapshot(order)
        require(order.status == OrderStatus.PENDING_CONFIRMATION) { "Only pending orders can be cancelled" }
        return order.copy(status = OrderStatus.CANCELLED).also { orders[id] = it }.let(::snapshot)
    }

    override fun advanceDemoStatus(id: String): Order {
        val order = requireNotNull(orders[id]) { "Order not found" }
        val next = when (order.status) {
            OrderStatus.PENDING_CONFIRMATION -> OrderStatus.PREPARING
            OrderStatus.PREPARING -> OrderStatus.DELIVERING
            OrderStatus.DELIVERING -> OrderStatus.COMPLETED
            else -> order.status
        }
        // Eco ledger/points are intentionally deferred; no points are awarded by this demo step.
        return order.copy(status = next).also { orders[id] = it }.let(::snapshot)
    }
}
