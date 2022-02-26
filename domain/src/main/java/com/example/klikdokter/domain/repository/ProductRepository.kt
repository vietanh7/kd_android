package com.example.klikdokter.domain.repository

import com.example.klikdokter.domain.entity.product.Product
import io.reactivex.rxjava3.core.Single

interface ProductRepository {
    fun getProductList(): Single<List<Product>>
    fun deleteProduct(sku: String): Single<Product>
    fun addProduct(product: Product): Single<Product>
    fun updateProduct(product: Product): Single<Product>
    fun searchProduct(sku: String): Single<Product>
}