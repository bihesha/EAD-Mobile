package com.example.ead_ecommerce_mobile.models

data class Order(
    val _id: String, // Changed _id to id for cleaner naming
    val userId: String,
    val products: List<Product>,
    val orderStatus: String,
    val orderDate: String
)

data class Product(
    val id: String, // Changed _id to id for cleaner naming
    val productName: String,
    val productCategory: String,
    val productDescription: String,
    val productQuantity: Int,
    val productVendor: String,
    val productStatus: Boolean,
    val productAvailability: Boolean,
    val productImage: String,
    val productPrice: Double, // Changed to Double for better precision
    val totalPrice: Double, // Changed to Double for better precision
    val deliveryStatus: String,
    val orderStatus: String,
    val orderNumber: String?, // Made nullable to indicate it may not always be present
    val isCancel: Boolean,
    val cancellationNote: String? // Made nullable to indicate it may not always be present
)
