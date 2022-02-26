package com.example.klikdokter.domain.section

interface SessionPreference {
    fun setToken(token: String)
    fun getToken(): String
}