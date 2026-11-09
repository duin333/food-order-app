package com.foodgo.app.data.model

/** Items and delivery address are snapshots, not live catalog/profile references. */
data class Order(
    val id: String,
    val customerId: String,
    val restaurantId: String,
    val items: List<CartItem>,
    val deliveryAddress: String,
    val createdAtMillis: Long,
    val deliveryFeeVnd: Long,
    val discountVnd: Long = 0,
    val status: OrderStatus = OrderStatus.PENDING_CONFIRMATION,
    val voucherId: String? = null,
    val groupOrderId: String? = null,
    val noPlasticCutlery: Boolean = true,
    val ecoPointsAwarded: Boolean = false,
    val scheduledStartMillis: Long? = null,
    val scheduledEndMillis: Long? = null,
    val deliveryMode: DeliveryMode = DeliveryMode.EXPRESS,
) {
    init {
        require(items.isNotEmpty() && items.all { it.restaurantId == restaurantId })
        require(deliveryFeeVnd >= 0 && discountVnd >= 0)
        require(discountVnd <= items.fold(0L) { sum, item -> Math.addExact(sum, item.subtotalVnd) })
        require((scheduledStartMillis == null) == (scheduledEndMillis == null))
        if (scheduledStartMillis != null && scheduledEndMillis != null) {
            require(scheduledStartMillis > createdAtMillis && scheduledEndMillis > scheduledStartMillis)
        }
    }

    val subtotalVnd: Long get() = items.fold(0L) { sum, item -> Math.addExact(sum, item.subtotalVnd) }
    val totalVnd: Long get() = Math.addExact(subtotalVnd - discountVnd, deliveryFeeVnd)
}
