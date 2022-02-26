package com.example.klikdokter.domain.repository

import com.example.klikdokter.domain.entity.auth.Token
import io.reactivex.rxjava3.core.Single

interface AuthRepository {
    fun register(email: String, password: String): Single<Token>
    fun login(email: String, password: String): Single<Token>
}