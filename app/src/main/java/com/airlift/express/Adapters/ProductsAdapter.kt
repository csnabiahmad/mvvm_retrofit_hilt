package com.airlift.express.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.airlift.express.Callback.ProductCallback
import com.airlift.express.Models.Product
import com.airlift.express.R
import com.airlift.express.databinding.ProductsItemBinding
import com.bumptech.glide.Glide

class ProductsAdapter(
    var context: Context,
    var list: ArrayList<Product>,
    var listener: ProductCallback
) :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    lateinit var binding: ProductsItemBinding

    inner class ProductViewHolder(val binding: ProductsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        binding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.products_item,
                parent,
                false
            )

        return ProductViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.binding.apply {
            list[position].apply {

                Glide.with(context).load(image).into(pImg)
                pTitle.setText(title)
                pPrice.setText("Rs. " + price)
            }
        }.also {
            it.root.setOnClickListener {
                listener.OnProductClick(list[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}