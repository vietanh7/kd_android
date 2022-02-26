package com.example.klikdokter.data.service.intercept

import com.example.klikdokter.domain.section.SessionPreference
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class MyRequestInterceptor @Inject constructor(
        private val sessionPreference: SessionPreference
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = getAuthorizedRequest(chain.request())
        return chain.proceed(request)
    }

    private fun getAuthorizedRequest(request: Request): Request {
        return with(getBearerToken(sessionPreference.getToken())) {
            request.newBuilder()
                    .addHeader(HEADER_AUTHORIZATION, this)
                    .build()
        }
    }

    private fun getBearerToken(token: String): String {
        if (token.isEmpty()) {
            return token
        }
        return "Bearer ".plus(token)
    }

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
    }
}
