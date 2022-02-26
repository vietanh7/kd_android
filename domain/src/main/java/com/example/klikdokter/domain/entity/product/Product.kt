package com.example.klikdokter.domain.entity.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val sku: String,
    val productName: String,
    val qty: Int,
    val price: Double,
    val status: Int
) : Parcelable