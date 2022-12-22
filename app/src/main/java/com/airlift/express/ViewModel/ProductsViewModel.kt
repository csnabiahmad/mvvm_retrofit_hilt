package com.airlift.express.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airlift.express.Models.Product
import com.airlift.express.Repositories.ProductRepository
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class ProductsViewModel:ViewModel() {

    val repository = ProductRepository()
    val Error: LiveData<String>
    val Progress: LiveData<Boolean>
    val Success: LiveData<ArrayList<Product>>

    init {
        this.Progress = repository.Progress
        this.Error = repository.Error
        this.Success = repository.Success
      }

    fun GetProducts()=viewModelScope.launch{
        repository.GetProducts()

    }

}