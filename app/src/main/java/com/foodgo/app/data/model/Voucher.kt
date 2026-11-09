package com.foodgo.app.data.model

data class Voucher(
    val id: String,
    val code: String,
    val title: String,
    val discountType: DiscountType,
    /** VND for FIXED_AMOUNT; integer percent (1..100) for PERCENTAGE. */
    val discountValue: Long,
    val minSubtotalVnd: Long,
    val expiresAtMillis: Long,
    val maxDiscountVnd: Long? = null,
    val restaurantId: String? = null,
) {
    init {
        require(discountValue > 0 && minSubtotalVnd >= 0)
        require(discountType != DiscountType.PERCENTAGE || discountValue <= 100)
        require(maxDiscountVnd == null || maxDiscountVnd >= 0)
    }
}
