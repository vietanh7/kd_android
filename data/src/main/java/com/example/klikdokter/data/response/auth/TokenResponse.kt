package com.example.klikdokter.data.response.auth

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("token")
    val token: String? = null
)