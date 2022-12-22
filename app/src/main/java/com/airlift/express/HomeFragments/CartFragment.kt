package com.airlift.express.HomeFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airlift.express.Base.BaseFragment
import com.airlift.express.R
import com.airlift.express.databinding.FragmentCartBinding
import com.airlift.express.databinding.ProductsItemBinding


class CartFragment : BaseFragment() {

    lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            binding<FragmentCartBinding>(
                inflater,
                R.layout.fragment_cart,
                container
            ).apply {
                lifecycleOwner = viewLifecycleOwner

            }

        // Inflate the layout for this fragment
        return binding.root
    }

}