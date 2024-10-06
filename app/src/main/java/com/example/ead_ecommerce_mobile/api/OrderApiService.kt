package com.example.ead_ecommerce_mobile.api

import com.example.ead_ecommerce_mobile.models.Order
import retrofit2.Call
import retrofit2.http.*

interface OrderApiService {

    @GET("/api/Order/getOrdersByUser/{userId}")
    fun getOrders(@Path("userId") userId: String): Call<List<Order>>
    // https://c22b-175-157-29-101.ngrok-free.app/api/Order/getOrdersByUser/66f8f5cbcd559c19c8437fb6
    @PUT("/orders/{orderId}/cancel")
    fun cancelOrder(
        @Path("orderId") orderId: String,
        @Body cancellationNote: String // This should be the cancellation note
    ): Call<Order> // Ensure to return the type that expecting
}
