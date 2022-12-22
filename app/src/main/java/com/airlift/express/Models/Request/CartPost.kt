package com.airlift.express.Models.Request

import java.io.Serializable

data class CartPost(var userId:Int?=0, var date:String?="", var products:ArrayList<CartProduct> ?= arrayListOf()):Serializable
