package com.example.ead_ecommerce_mobile.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://c22b-175-157-29-101.ngrok-free.app/"  // For emulator, replace with your machine's IP if on physical device

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: OrderApiService by lazy {
        retrofit.create(OrderApiService::class.java)
    }
}
