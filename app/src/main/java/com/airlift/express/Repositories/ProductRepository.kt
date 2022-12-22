package com.airlift.express.Repositories

import androidx.lifecycle.MutableLiveData
import com.airlift.express.Models.Product
import com.airlift.express.Retrofit.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository {

    private val client = RetrofitClient()

    var Error: MutableLiveData<String> = MutableLiveData()

    var Progress: MutableLiveData<Boolean> = MutableLiveData()

    var Success: MutableLiveData<ArrayList<Product>> = MutableLiveData()

    fun GetProducts() {


        Progress.postValue(true)

        val call = RetrofitClient.getRetrofitInstance().GetProducts()

        client.makeRequest(call, object : Callback<ArrayList<Product>> {
            override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                Progress.postValue(false)

                Error.postValue("Connection Lost")

            }

            override fun onResponse(
                call: Call<ArrayList<Product>>,
                response: Response<ArrayList<Product>>
            ) {
                Progress.postValue(false)

                if (response.isSuccessful) {

                    Success.postValue(response.body())

                } else {

                    Error.postValue(response.message().toString())

                }


            }

        })


    }
}