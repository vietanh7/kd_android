package com.example.klikdokter.modul.productlist

import com.example.klikdokter.domain.entity.product.Product


interface ProductListener {
    fun onDeleteProduct(sku: String)
    fun onUpdateProduct(product: Product)
}