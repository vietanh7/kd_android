package com.example.klikdokter.data.service.api

import com.example.klikdokter.data.ApiConstants
import com.example.klikdokter.data.EndpointConstants
import com.example.klikdokter.data.response.product.ProductResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface ProductApi {

    @GET(EndpointConstants.PRODUCT_LIST)
    fun getProductList(): Single<List<ProductResponse>>

    @FormUrlEncoded
    @POST(EndpointConstants.DELETE_PRODUCT)
    fun deleteProduct(
        @Field(ApiConstants.SKU) sku: String
    ): Single<ProductResponse>

    @FormUrlEncoded
    @POST(EndpointConstants.ADD_PRODUCT)
    fun addProduct(
        @Field(ApiConstants.SKU) sku: String,
        @Field(ApiConstants.PRODUCT_NAME) productName: String,
        @Field(ApiConstants.QUANTITY) quantity: Int,
        @Field(ApiConstants.PRICE) price: Double,
        @Field(ApiConstants.UNIT) unit: String = "Cartoon",
        @Field(ApiConstants.STATUS) status: Int = 1
    ): Single<ProductResponse>

    @FormUrlEncoded
    @POST(EndpointConstants.UPDATE_PRODUCT)
    fun updateProduct(
        @Field(ApiConstants.SKU) sku: String,
        @Field(ApiConstants.PRODUCT_NAME) productName: String,
        @Field(ApiConstants.QUANTITY) quantity: Int,
        @Field(ApiConstants.PRICE) price: Double,
        @Field(ApiConstants.UNIT) unit: String = "Cartoon",
        @Field(ApiConstants.STATUS) status: Int = 1
    ): Single<ProductResponse>

    @FormUrlEncoded
    @POST(EndpointConstants.SEARCH_PRODUCT)
    fun searchProduct(
        @Field(ApiConstants.SKU) sku: String
    ): Single<ProductResponse>
}