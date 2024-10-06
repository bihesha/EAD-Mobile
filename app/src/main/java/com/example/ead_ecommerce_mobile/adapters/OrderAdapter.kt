package com.example.ead_ecommerce_mobile.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ead_ecommerce_mobile.databinding.OrderItemBinding
import com.example.ead_ecommerce_mobile.models.Order

class OrdersAdapter(
    private var ordersList: List<Order> = listOf(),
    private val onCancelClick: (String) -> Unit
) : RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding =
            OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = ordersList[position]
        holder.binding.apply {
            orderId.text = order._id // Accessing the order ID
            orderStatus.text = order.orderStatus

            // Calculate total price from products
            val total = order.products.sumOf { it.totalPrice } // Summing total price from all products
            totalPrice.text = total.toString() // Display the total price

            // Set up the click listener for the cancel button
            cancelButton.setOnClickListener {
                // Pass the order ID to the callback
                onCancelClick(order._id) // Accessing the order ID
            }
        }
    }

    override fun getItemCount(): Int = ordersList.size

    fun setOrders(orders: List<Order>) {
        this.ordersList = orders
        notifyDataSetChanged()
    }
}
