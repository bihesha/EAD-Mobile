package com.example.ead_ecommerce_mobile.ui.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ead_ecommerce_mobile.models.Order
import com.example.ead_ecommerce_mobile.repository.OrderRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersViewModel(private val orderRepository: OrderRepository) : ViewModel() {

    private val _orders = MutableLiveData<List<Order>>()
    val orders: LiveData<List<Order>> get() = _orders

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchOrders(customerId: String) {
        _loading.value = true
        orderRepository.getOrders(customerId).enqueue(object : Callback<List<Order>> {
            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                _loading.value = false
                if (response.isSuccessful) {
                    _orders.value = response.body() ?: emptyList()
                } else {
                    _errorMessage.value = "Error: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                _loading.value = false
                _errorMessage.value = "Failed to fetch orders: ${t.message}"
            }
        })
    }

    fun cancelOrder(orderId: String, cancellationNote: String) {
        orderRepository.cancelOrder(orderId, cancellationNote).enqueue(object : Callback<Order> { // Corrected to Callback<Order>
            override fun onResponse(call: Call<Order>, response: Response<Order>) { // Corrected type
                if (!response.isSuccessful) {
                    _errorMessage.value = "Error canceling order: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<Order>, t: Throwable) { // Corrected type
                _errorMessage.value = "Failed to cancel order: ${t.message}"
            }
        })
    }
}
