package com.foodgo.app.data.model

data class Restaurant(
    val id: String,
    val name: String,
    val description: String,
    val address: String,
    val imageKey: String,
    val rating: Double,
    val estimatedDeliveryMinutes: Int,
    val deliveryFeeVnd: Long,
)
