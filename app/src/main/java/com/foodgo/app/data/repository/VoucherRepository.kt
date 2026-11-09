package com.foodgo.app.data.repository

import com.foodgo.app.data.model.*

/** BC3 synchronous contract. Invalid mutations throw IllegalArgumentException; no UI dependencies. */
interface VoucherRepository {
    fun getVouchers(): List<Voucher>
    /** Returns zero for expired, unknown or ineligible vouchers. */
    fun calculateDiscount(code: String, restaurantId: String, subtotalVnd: Long, nowMillis: Long): Long
}
