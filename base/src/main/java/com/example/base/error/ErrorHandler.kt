package com.example.base.error

import com.example.base.view.CanShowError


interface ErrorHandler {
    fun proceed(error: Throwable)
    fun attachView(view: CanShowError)
    fun detachView()
}