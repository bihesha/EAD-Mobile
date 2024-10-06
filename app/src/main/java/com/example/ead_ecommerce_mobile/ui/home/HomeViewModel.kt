package com.example.ead_ecommerce_mobile.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ead_ecommerce_mobile.models.Order

class HomeViewModel : ViewModel() {

    // LiveData for text in the fragment
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    // LiveData for orders
    private val _orders = MutableLiveData<List<Order>>().apply {
        value = emptyList() // Initial value as an empty list
    }
    val orders: LiveData<List<Order>> = _orders

    // Method to cancel an order
    fun cancelOrder(orderId: String) {
        // Logic to cancel the order
        // Here you could remove the order from the list and update _orders
        _orders.value = _orders.value?.filter { it._id != orderId }
    }

    // Method to set orders (this might be called when fetching orders from a repository)
    fun setOrders(orders: List<Order>) {
        _orders.value = orders
    }
}
