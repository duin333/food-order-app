package com.foodgo.app.data.sample

import com.foodgo.app.data.model.DiscountType
import com.foodgo.app.data.model.Voucher
import com.foodgo.app.data.repository.VoucherRepository

/** Illustrative vouchers only; no redemption/payment backend. */
class SampleVoucherRepository : VoucherRepository {
    private val vouchers = listOf(
        Voucher("voucher_demo_10k", "DEMO10K", "Mẫu: giảm 10.000đ", DiscountType.FIXED_AMOUNT,
            10_000L, 80_000L, 4_102_444_800_000L),
        Voucher("voucher_demo_10pct", "DEMO10", "Mẫu: giảm 10%, tối đa 20.000đ", DiscountType.PERCENTAGE,
            10L, 50_000L, 4_102_444_800_000L, maxDiscountVnd = 20_000L),
    )

    override fun getVouchers() = vouchers.toList()

    override fun calculateDiscount(code: String, restaurantId: String, subtotalVnd: Long, nowMillis: Long): Long {
        require(subtotalVnd >= 0)
        val voucher = vouchers.find { it.code.equals(code.trim(), ignoreCase = true) } ?: return 0
        if (nowMillis >= voucher.expiresAtMillis || subtotalVnd < voucher.minSubtotalVnd ||
            (voucher.restaurantId != null && voucher.restaurantId != restaurantId)) return 0
        val raw = when (voucher.discountType) {
            DiscountType.FIXED_AMOUNT -> voucher.discountValue
            DiscountType.PERCENTAGE ->
                (subtotalVnd / 100) * voucher.discountValue + (subtotalVnd % 100) * voucher.discountValue / 100
        }
        return minOf(raw, voucher.maxDiscountVnd ?: raw, subtotalVnd)
    }
}
