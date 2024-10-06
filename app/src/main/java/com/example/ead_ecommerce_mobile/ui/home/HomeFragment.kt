package com.example.ead_ecommerce_mobile.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ead_ecommerce_mobile.adapters.OrdersAdapter
import com.example.ead_ecommerce_mobile.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Declare the adapter and ViewModel at the class level
    private lateinit var ordersAdapter: OrdersAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        setupRecyclerView()

        // Observe the orders list from ViewModel
        homeViewModel.orders.observe(viewLifecycleOwner) { ordersList ->
            ordersAdapter.setOrders(ordersList)
        }

        return root
    }

    private fun setupRecyclerView() {
        // Initialize OrdersAdapter with a callback for cancelling orders
        ordersAdapter = OrdersAdapter(emptyList()) { orderId ->
            // Handle the cancel order action
            homeViewModel.cancelOrder(orderId)
        }

        // Set up the RecyclerView
        binding.recyclerViewOrders.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ordersAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
