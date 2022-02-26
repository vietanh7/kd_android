package com.example.klikdokter.data.mapper

import com.example.klikdokter.data.response.auth.TokenResponse
import com.example.klikdokter.domain.entity.auth.Token
import javax.inject.Inject

class TokenMapper @Inject constructor() : Mapper<TokenResponse, Token>() {

    override fun apply(from: TokenResponse) = with(from) {
        Token(token.orEmpty())
    }
}