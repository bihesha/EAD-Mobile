package com.example.ead_ecommerce_mobile.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ead_ecommerce_mobile.adapters.OrdersAdapter
import com.example.ead_ecommerce_mobile.databinding.FragmentOrderListBinding
import com.example.ead_ecommerce_mobile.models.Order

class OrderListFragment : Fragment() {

    private var _binding: FragmentOrderListBinding? = null
    private val binding get() = _binding!!

    private lateinit var ordersAdapter: OrdersAdapter
    private val ordersViewModel: OrdersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        ordersAdapter = OrdersAdapter { orderId ->
            cancelOrder(orderId)
        }

        binding.recyclerViewOrders.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ordersAdapter
        }

        // Observe orders
        ordersViewModel.orders.observe(viewLifecycleOwner) { orders ->
            ordersAdapter.setOrders(orders)
        }

        // Observe loading state
        ordersViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.loadingIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Observe error messages
        ordersViewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            // Display the error message (use a Toast, Snackbar, or TextView)
            if (error != null) {
                // Show error message
            }
        }

        // Fetch orders
        val customerId = "YOUR_CUSTOMER_ID" // Pass the actual customer ID here
        ordersViewModel.fetchOrders(customerId)

        return root
    }

    private fun cancelOrder(orderId: String) {
        val cancellationNote = "Your cancellation note here" // Get cancellation note from user input
        ordersViewModel.cancelOrder(orderId, cancellationNote)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
