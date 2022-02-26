package com.example.klikdokter.data.repoiml

import com.example.klikdokter.data.mapper.Mapper
import com.example.klikdokter.data.response.product.ProductResponse
import com.example.klikdokter.data.service.api.ProductApi
import com.example.klikdokter.domain.entity.product.Product
import com.example.klikdokter.domain.repository.ProductRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val productMapper: Mapper<ProductResponse, Product>,
    private val productListMapper: Mapper<List<ProductResponse>, List<Product>>,
) : ProductRepository {

    override fun getProductList(): Single<List<Product>> =
        productApi.getProductList().map(productListMapper)

    override fun deleteProduct(sku: String): Single<Product> =
        productApi.deleteProduct(sku).map(productMapper)

    override fun addProduct(product: Product): Single<Product> =
        productApi.addProduct(
            product.sku,
            product.productName,
            product.qty,
            product.price,
        ).map(productMapper)

    override fun updateProduct(product: Product): Single<Product> =
        productApi.updateProduct(
            product.sku,
            product.productName,
            product.qty,
            product.price,
        ).map(productMapper)

    override fun searchProduct(sku: String): Single<Product> =
        productApi.searchProduct(sku).map(productMapper)
}