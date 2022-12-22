package com.airlift.express.Retrofit



import com.airlift.express.Hilt.NetworkModule

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitClient {

    companion object {
        val retrofit = NetworkModule.getRetrofit(NetworkModule.getOkHttpClient())

        fun getRetrofitInstance(): WebService {
            return NetworkModule.getApiInterface(retroFit = retrofit)
        }

    }

    fun <T> makeRequest(responseCall: Call<T>, requestCallback: Callback<T>) {

        responseCall.enqueue(object : Callback<T> {

            override fun onFailure(call: Call<T>, t: Throwable) {

                requestCallback.onFailure(call, t)

            }

            override fun onResponse(call: Call<T>, response: Response<T>) {


                    requestCallback.onResponse(call, response)


            }

        })
    }

}