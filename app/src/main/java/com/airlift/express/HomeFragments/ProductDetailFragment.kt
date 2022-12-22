package com.airlift.express.HomeFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airlift.express.Base.BaseFragment
import com.airlift.express.Models.Product
import com.airlift.express.R
import com.airlift.express.databinding.FragmentCartBinding
import com.airlift.express.databinding.FragmentProductDetailBinding
import com.airlift.express.databinding.FragmentProductsBinding
import com.bumptech.glide.Glide


class ProductDetailFragment : BaseFragment() {

    lateinit var binding: FragmentProductDetailBinding

    var data = Product()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            data = it!!.getSerializable("data") as Product
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            binding<FragmentProductDetailBinding>(
                inflater,
                R.layout.fragment_product_detail,
                container
            ).apply {
                lifecycleOwner = viewLifecycleOwner

            }

        binding.apply {

            backBtn.setOnClickListener {
                requireActivity().onBackPressed()
            }
            Glide.with(requireContext()).load(data.image).into(pDImg)
            pDTitle.setText(data.title)
            pDDesc.setText(data.description)
            pDCount.setText("( " + data.rating!!.count.toString() + " ) ")
            pDRating.setText(" " + data.rating!!.rate + " ")
        }

        // Inflate the layout for this fragment
        return binding.root

    }


}