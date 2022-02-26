package com.example.klikdokter.data.service.intercept

import java.io.IOException

class AppRestException(
        val code: Int,
        message: String? = null
) : IOException(message)