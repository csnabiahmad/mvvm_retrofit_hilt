package com.airlift.express.Retrofit



import com.airlift.express.Models.Product
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import kotlin.collections.ArrayList


interface WebService {

    @GET("/products")
    fun GetProducts() : Call<ArrayList<Product>>

    @POST("carts")
    fun AddToCart() : Call<ArrayList<Product>>

}

