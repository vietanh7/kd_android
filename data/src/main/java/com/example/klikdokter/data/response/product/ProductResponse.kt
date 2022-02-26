package com.example.klikdokter.data.response.product

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("sku")
    val sku: String? = null,
    @SerializedName("product_name")
    val productName: String? = null,
    @SerializedName("qty")
    val qty: Int? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("status")
    val status: Int? = null
)