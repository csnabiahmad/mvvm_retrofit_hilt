package com.airlift.express.HomeFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.airlift.express.Adapters.ProductsAdapter
import com.airlift.express.Base.BaseFragment
import com.airlift.express.Callback.ProductCallback
import com.airlift.express.Models.Product
import com.airlift.express.R
import com.airlift.express.ViewModel.ProductsViewModel
import com.airlift.express.databinding.FragmentCartBinding
import com.airlift.express.databinding.FragmentProductsBinding


class ProductsFragment : BaseFragment(), ProductCallback {
    val viewModel: ProductsViewModel by viewModels()
    var listing: ArrayList<Product> = ArrayList()
    lateinit var adapterr: ProductsAdapter
    lateinit var binding: FragmentProductsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            binding<FragmentProductsBinding>(
                inflater,
                R.layout.fragment_products,
                container
            ).apply {
                lifecycleOwner = viewLifecycleOwner

            }

        binding.productsSwipeRefresh.setOnRefreshListener {
            viewModel.GetProducts()
            binding.productsSwipeRefresh.isRefreshing = false
        }
        adapterr = ProductsAdapter(context!!, listing, this@ProductsFragment)
        binding.productsRv.apply {
            isNestedScrollingEnabled = false
            layoutManager = GridLayoutManager(context!!, 2)
            adapter = adapterr

        }
        viewModel.apply {
            GetProducts()
            Success.observe(viewLifecycleOwner, {
                it.let {
                    listing.clear()
                    listing.addAll(it)
                    adapterr.notifyDataSetChanged()
                }
            })
            Error.observe(viewLifecycleOwner, {
                Toast.makeText(requireContext(), "" + it.toString(), Toast.LENGTH_SHORT).show()
            })
            Progress.observe(viewLifecycleOwner, {
                if (it) {
                    binding.apply {
                        productsRv.visibility = View.GONE
                        productsShimmer.visibility = View.VISIBLE
                        productsShimmer.startShimmer()
                    }
                } else {
                    binding.apply {
                        productsRv.visibility = View.VISIBLE
                        productsShimmer.visibility = View.GONE
                        productsShimmer.stopShimmer()
                    }
                }
            })

        }


        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        binding.productsShimmer.stopShimmer()
    }

    override fun OnProductClick(data: Product) {
        var bundle = Bundle()
        bundle.putSerializable("data", data)
        findNavController().navigate(
            R.id.action_productsFragment_to_productDetailFragment,
            args = bundle
        )
    }

}