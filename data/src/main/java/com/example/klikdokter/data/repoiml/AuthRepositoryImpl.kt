package com.example.klikdokter.data.repoiml

import com.example.klikdokter.data.mapper.Mapper
import com.example.klikdokter.data.response.auth.TokenResponse
import com.example.klikdokter.data.service.api.AuthApi
import com.example.klikdokter.domain.entity.auth.Token
import com.example.klikdokter.domain.repository.AuthRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val loginMapper: Mapper<TokenResponse, Token>,
) : AuthRepository {

    override fun register(email: String, password: String): Single<Token> =
        authApi.register(email, password).map(loginMapper)

    override fun login(email: String, password: String): Single<Token> =
        authApi.login(email, password).map(loginMapper)
}