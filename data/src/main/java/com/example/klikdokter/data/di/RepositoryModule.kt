package com.example.klikdokter.data.di

import com.example.klikdokter.data.mapper.ProductListMapper
import com.example.klikdokter.data.mapper.ProductMapper
import com.example.klikdokter.data.mapper.TokenMapper
import com.example.klikdokter.data.repoiml.AuthRepositoryImpl
import com.example.klikdokter.data.repoiml.ProductRepositoryImpl
import com.example.klikdokter.data.service.api.AuthApi
import com.example.klikdokter.data.service.api.ProductApi
import com.example.klikdokter.domain.repository.AuthRepository
import com.example.klikdokter.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideAuthRepository(
        authApi: AuthApi,
        tokenMapper: TokenMapper,
    ): AuthRepository = AuthRepositoryImpl(
        authApi,
        tokenMapper
    )

    @Provides
    fun provideProductRepository(
        productApi: ProductApi,
        productMapper: ProductMapper,
        productListMapper: ProductListMapper,
    ): ProductRepository = ProductRepositoryImpl(
        productApi,
        productMapper,
        productListMapper
    )
}
