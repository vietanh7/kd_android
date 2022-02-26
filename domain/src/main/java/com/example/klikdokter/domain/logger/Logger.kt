package com.example.klikdokter.domain.logger

interface Logger {
    fun log(message: () -> String)
    fun logError(throwable: () -> Throwable)
}