package com.example.klikdokter.data.service.intercept

import com.google.gson.Gson
import okhttp3.*
import java.net.HttpURLConnection
import javax.inject.Inject

class MyResponseInterceptor @Inject constructor(
    private val gson: Gson,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        when (response.code()) {
            HttpURLConnection.HTTP_OK -> return response
            else -> throw AppRestException(
                response.code(),
                response.message()
            )
        }
    }
}