package com.example.klikdokter.data.mapper

import com.example.klikdokter.data.response.product.ProductResponse
import com.example.klikdokter.domain.entity.product.Product
import javax.inject.Inject

class ProductListMapper @Inject constructor() : Mapper<List<ProductResponse>, List<Product>>() {

    override fun apply(from: List<ProductResponse>): List<Product> =
        from.map {
            Product(it.id ?:0, it.sku.orEmpty(), it.productName.orEmpty(), it.qty ?:0, it.price ?: 0.0, it.status?:0)
        }
}