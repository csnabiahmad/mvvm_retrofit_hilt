package com.airlift.express.Callback

import com.airlift.express.Models.Product

interface ProductCallback {

    fun OnProductClick(data:Product)
}