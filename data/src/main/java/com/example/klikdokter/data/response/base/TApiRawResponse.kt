package com.example.klikdokter.data.response.base

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class TApiRawResponse(

        @SerializedName("message")
        var message: String? = null,

        @SerializedName("data")
        var data: JsonElement? = null
)