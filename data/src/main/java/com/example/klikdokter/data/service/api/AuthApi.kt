package com.example.klikdokter.data.service.api

import com.example.klikdokter.data.ApiConstants
import com.example.klikdokter.data.EndpointConstants
import com.example.klikdokter.data.response.auth.TokenResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface AuthApi {

    @FormUrlEncoded
    @POST(EndpointConstants.REGISTER)
    fun register(
        @Field(ApiConstants.EMAIL) email: String,
        @Field(ApiConstants.PASSWORD) password: String,
    ): Single<TokenResponse>

    @FormUrlEncoded
    @POST(EndpointConstants.LOGIN)
    fun login(
        @Field(ApiConstants.EMAIL) email: String,
        @Field(ApiConstants.PASSWORD) password: String,
    ): Single<TokenResponse>
}