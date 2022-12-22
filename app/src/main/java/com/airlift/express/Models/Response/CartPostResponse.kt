package com.airlift.express.Models.Response

import com.airlift.express.Models.Request.CartProduct
import java.io.Serializable

data class CartPostResponse(
var _id:String?="",
var id:String?="",
var userId:Int?=12,
var date:String?="",
var products:ArrayList<CartProduct> ?= arrayListOf()
):Serializable
