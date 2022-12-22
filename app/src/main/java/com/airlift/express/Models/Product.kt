package com.airlift.express.Models

import android.os.Parcelable
import java.io.Serializable

data class Product(
    var id: Int? = 0,
    var title: String? = "",
    var price: String? = "",
    var description: String? = "",
    var category: String?="",
    var image: String? = "",
    var rating:Rating?=Rating(),
):Serializable
