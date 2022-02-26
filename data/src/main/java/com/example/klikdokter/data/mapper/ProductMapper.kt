package com.example.klikdokter.data.mapper

import com.example.klikdokter.data.response.product.ProductResponse
import com.example.klikdokter.domain.entity.product.Product
import javax.inject.Inject

class ProductMapper @Inject constructor() : Mapper<ProductResponse, Product>() {

    override fun apply(from: ProductResponse): Product = with(from) {
        Product(id ?:0, sku.orEmpty(), productName.orEmpty(), qty ?:0, price ?: 0.0, status?:0)
    }
}