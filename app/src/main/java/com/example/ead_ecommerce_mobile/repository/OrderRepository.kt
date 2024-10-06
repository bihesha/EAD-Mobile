package com.example.ead_ecommerce_mobile.repository

import com.example.ead_ecommerce_mobile.api.OrderApiService
import com.example.ead_ecommerce_mobile.models.Order
import retrofit2.Call

class OrderRepository(private val apiService: OrderApiService) {

    fun getOrders(customerId: String): Call<List<Order>> {
        return apiService.getOrders(customerId)
    }

    fun cancelOrder(orderId: String, cancellationNote: String): Call<Order> {
        return apiService.cancelOrder(orderId, cancellationNote) // Use the apiService instance
    }
}
